import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class Server extends Thread {
    private final Game game;
    private int port = 5678;
    private ServerSocket server;
    private ArrayList<Socket> clients;
    private LinkedBlockingQueue<DataListener> dataListeners;
    private LinkedBlockingQueue<Game> games;

    public Server(Game game) throws IOException {
        this.server = new ServerSocket(port);
        this.clients = new ArrayList<>();
        this.dataListeners = new LinkedBlockingQueue<>();
        this.games = new LinkedBlockingQueue<>();
        this.game = game;
        start();
    }



    @Override
    public void run() {
        System.out.println("in run");
        ConnectListener connectListener = new ConnectListener();
        new Thread(connectListener).start();
    }

    private class ConnectListener extends Thread {
        @Override
        public void run() {
            Socket client;
            for (;;) {
                client = null;
                try {
                    client = server.accept();
                    if (client != null) {
                        clients.add(client);
                        System.out.println(client);
                        DataListener dL = new DataListener(client);
                        dataListeners.add(dL);
                        dL.start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private class DataListener extends Thread {
        private ObjectOutputStream outputStream;
        private ObjectInputStream inputStream;
        private Socket client;

        public DataListener(Socket client) throws IOException {
            this.client = client;
            outputStream = new ObjectOutputStream(client.getOutputStream());
            inputStream = new ObjectInputStream(client.getInputStream());
        }

        @Override
        public void run() {
            for (;;) {
                try {
                    System.out.println("In DataListener: before read object");
                    games.put((Game) inputStream.readObject());
                    System.out.println("In DataListener: after read object");
                    DataWriter dataWriter = new DataWriter(client);
                    dataWriter.start();

                } catch (IOException | ClassNotFoundException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public ObjectOutputStream getOutputStream() {
            return outputStream;
        }

        public void setOutputStream(ObjectOutputStream outputStream) {
            this.outputStream = outputStream;
        }

        public ObjectInputStream getInputStream() {
            return inputStream;
        }

        public void setInputStream(ObjectInputStream inputStream) {
            this.inputStream = inputStream;
        }

        public Socket getClient() {
            return client;
        }

        public void setClient(Socket client) {
            this.client = client;
        }
    }

    private class DataWriter extends Thread {
        private Socket client;

        public DataWriter(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                System.out.println("In DataWriter: before taking game from queue");
                Game game = games.take();
                System.out.println("In DataWriter: after taking game from queue");
                System.out.println(dataListeners.size());
                for (DataListener dataListener : dataListeners) {
                    System.out.println("In DataWriter: before write object");
                    if (dataListener.getClient() != client)
                        dataListener.getOutputStream().writeObject(game);
                    System.out.println("In DataWriter: after write object");
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }

        }
    }

}
