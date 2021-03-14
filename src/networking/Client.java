package networking;

import java.io.IOException;
import java.net.Socket;

public class Client extends Thread {
    private static int port = 5678;


    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", port);
    }
}
