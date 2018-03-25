package client;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClientLauncher {

    public static void main(String[] args) {
            int port = 5000;
            Client client = new Client(port);
            client.start();

    }
}
