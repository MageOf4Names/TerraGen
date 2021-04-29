import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

public class Client extends Thread {
    private static final int port = 5678;
    private String hostAddress;
    private Socket socket;
    private NetworkContainer networkContainer;
    private LinkedBlockingQueue<Game> gameQueue;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;


    public Client(String hostAddress) throws IOException {
        this.hostAddress = hostAddress;
        //gameQueue = new LinkedBlockingQueue<>();
        this.start();

    }

    @Override
    public void run() {
        try {
            System.out.println("In client thread");
            socket = new Socket(hostAddress, port);
            System.out.println("passed socket");
            inputStream = new ObjectInputStream(socket.getInputStream());
            System.out.println("input stream");
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("passed outputstream");

        } catch (IOException e) {
            e.printStackTrace();
        }

        while(true) {
            try {
                System.out.println("Before setting game");
                //game.setGame((Game) inputStream.readObject());
                networkContainer = (NetworkContainer) inputStream.readObject();
                SwingUtilities.invokeAndWait(updateGame);
                //gameQueue.put(newGame);
                //gameQueue.take();
                //TerraGen.window.repaint();
                System.out.println("After setting game");
            } catch (IOException | ClassNotFoundException | InterruptedException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public void pushGameChange(int index, NetworkType type, Serializable data) throws IOException {
        System.out.println("Before writing game");
        outputStream.writeObject( new NetworkContainer(type, index, data));
        outputStream.reset();
        System.out.println("After writing game");
    }

    public void pushGameChange(NetworkContainer networkContainer) throws IOException {
        System.out.println("Before writing game");
        outputStream.writeObject( networkContainer);
        outputStream.reset();
        System.out.println("After writing game");
    }

    public NetworkContainer getNetworkContainer() {
        return networkContainer;
    }

    public void setNetworkContainer(NetworkContainer networkContainer) {
        this.networkContainer = networkContainer;
    }

    /*
    public LinkedBlockingQueue<Game> getGameQueue() {
        return gameQueue;
    }
     */
    /*
    private class DataWriter extends Thread {

        @Override
        public void run() {
            try {

            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            while(true) {

            }

        }


    }

    private class DataReader extends Thread {


        @Override
        public void run() {

        }
    }
     */

    final Runnable updateGame = new Runnable() {
        @Override
        public void run() {
            switch (networkContainer.getType()) {
                case TOKEN:
                    TerraGen.window.game.getMap().tokens.get(networkContainer.getKey()).update();
                    break;
                case NEWPLAYERREQUEST:
                    networkContainer.setData(
                            new GameContainer(TerraGen.window.game, TerraGen.window.gameRenderer));
                    networkContainer.setType(NetworkType.NEWPLAYERSEND);
                    try {
                        pushGameChange(networkContainer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case NEWPLAYERSEND:
                    TerraGen.window.loadClientGame(networkContainer);
            }

            //TerraGen.window.repaint();
        }
    };
}
