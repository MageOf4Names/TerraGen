package networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {
    private int port = 5678;
    private ServerSocket server = null;
    private ArrayList<Socket> clients = null;

    public Server() throws IOException {
        this.server = new ServerSocket(port);
        this.clients = new ArrayList<>();
    }

    /**
     * A wrapper for getting a client connection. This method handles the
     * possible exceptions by printing a message and exiting.
     *
     * @param server the SeverSocket that is in the listen state
     * @return a Socket connected to the client
     */
    private static Socket getNextClient(ServerSocket server) {
        Socket client = null;
        try {
            client = server.accept();
        } catch (IOException e) {
            System.err.println("Received an I/O exception while accepting "
                    + "a client connection: " + e.getMessage());
            System.exit(1);
        }

        // Log information about the client.
        InetAddress clientAddr = client.getInetAddress();
        System.out.println("Received a connection from client '"
                + ((InetAddress) clientAddr).getHostName() + "' IP: "
                + clientAddr.getHostAddress() + " on port "
                + ((Socket) client).getPort());
        return client;
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
            Socket client = null;
            for (;;) {
                client = null;
                try {
                    client = server.accept();
                    if (client != null) {
                        clients.add(client);
                        System.out.println(client);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private class DataListener extends Thread {
        private ObjectOutputStream outputStream = null;
        private ObjectInputStream inputStream = null;
        private Socket client = null;

        public DataListener(Socket client) throws IOException {
            this.client = client;
            outputStream = new ObjectOutputStream(client.getOutputStream());
        }

        @Override
        public void run() {
            Socket client = null;
            for (;;) {
                client = null;
                try {
                    client = server.accept();
                    if (client != null) {
                        clients.add(client);
                        System.out.println(client);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
