import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client extends Thread {
    private static final int port = 5678;
    private String hostAddress;
    private Socket socket;
    private Game game;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;


    public Client(String hostAddress, Game game) throws IOException {
        this.game = game;
        this.hostAddress = hostAddress;
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
                game.setGame((Game) inputStream.readObject());
                System.out.println("After setting game");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void pushGameChange() throws IOException {
        System.out.println("Before writing game");
        outputStream.writeObject(game);
        System.out.println("After writing game");
    }

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

}
