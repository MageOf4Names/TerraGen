import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Handles updating game objects among multiple clients
 */
public class Server extends Thread {
    private final Game game;
    private int port = 5678;
    private ServerSocket server;
    private ArrayList<Socket> clients;
    private LinkedBlockingQueue<DataListener> dataListeners;
    //private LinkedBlockingQueue<NetworkContainer> networkContainers;

    public Server(Game game) throws IOException {
        this.server = new ServerSocket(port);
        this.clients = new ArrayList<>();
        this.dataListeners = new LinkedBlockingQueue<>();
        //this.networkContainers = new LinkedBlockingQueue<>();
        this.game = game;
        start();
    }



    @Override
    public void run() {
        System.out.println("in run");
        ConnectListener connectListener = new ConnectListener();
        new Thread(connectListener).start();
    }

    /**
     * Listens for new client connections
     */
    private class ConnectListener extends Thread {
        @Override
        public void run() {
            Socket client;
            /**
             * This thread will keep looping to check for new connections
             */
            for (;;) {
                client = null;
                try {
                    client = server.accept();
                    if (client != null) {
                        clients.add(client);
                        /**
                         * Once a new connection is added the server will send a request to the first client to
                         * ask for the current game state.
                         */
                        if (!dataListeners.isEmpty()) {
                            NetworkContainer networkContainer =
                                    new NetworkContainer(NetworkType.NEWPLAYERREQUEST, client.getInetAddress());
                            ExclusiveDataWriter edw =
                                    new ExclusiveDataWriter(
                                            dataListeners.peek().getClient().getInetAddress(), networkContainer);
                            edw.start();
                        }

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


    /**
     * A Datalistener is spawned for each client to listen for incoming objects
     */
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
                    NetworkContainer networkContainer = (NetworkContainer) inputStream.readObject();
                        if (networkContainer != null) {
                            System.out.println("In DataListener: after read object");
                            /**
                             * This if will check if the read object contains the whole game state, and send it to the
                             * new client.
                             */
                            if (networkContainer.getType() == NetworkType.NEWPLAYERREQUEST) {
                                ExclusiveDataWriter exclusiveDataWriter =
                                        new ExclusiveDataWriter(networkContainer.getTarget(), networkContainer);
                                exclusiveDataWriter.start();
                            } else {
                                DataWriter dataWriter = new DataWriter(client, networkContainer);
                                dataWriter.start();
                            }

                        }

                } catch (IOException | ClassNotFoundException e) {
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

    /**
     * This thread is spawned whenever most networked objects are received by the server which will then send it
     * to all the other clients, except the one that sent it
     */
    private class DataWriter extends Thread {
        private Socket client;
        private NetworkContainer networkContainer;

        public DataWriter(Socket client, NetworkContainer networkContainer) {
            this.client = client;
            this.networkContainer = networkContainer;
        }

        @Override
        public void run() {
            try {
                System.out.println("In DataWriter: before taking game from queue");
                //NetworkContainer networkContainer = networkContainers.take();
                System.out.println("In DataWriter: after taking game from queue");
                System.out.println(dataListeners.size());
                for (DataListener dataListener : dataListeners) {
                    System.out.println("In DataWriter: before write object");
                    if (dataListener.getClient() != client)
                        dataListener.getOutputStream().writeObject(networkContainer);
                    System.out.println("In DataWriter: after write object");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * ExclusiveDataWriter will write an object to one specific client, it keeps track of the client by their
     * InetAddress instead of Socket since Socket is not serializable.
     */
    private class ExclusiveDataWriter extends Thread {
        private InetAddress client;
        private NetworkContainer networkContainer;

        public ExclusiveDataWriter(InetAddress client, NetworkContainer networkContainer) {
            this.client = client;
            this.networkContainer = networkContainer;
        }

        @Override
        public void run() {
            try {
                for (DataListener dataListener : dataListeners) {
                    if (dataListener.getClient().getInetAddress().equals(client))
                        dataListener.getOutputStream().writeObject(networkContainer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
