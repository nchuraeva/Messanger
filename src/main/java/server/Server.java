package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server  {

    private final int port;

    public Server() {
        this.port = 5000;
    }

    private List<Connection> connectionList = Collections.synchronizedList(new ArrayList<Connection>());



    public  List<Connection> getConnectionList() {
        List<Connection> connections = new ArrayList<>();
        connections.addAll(connectionList);
        return connections;
    }


    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(this.port);

            //Цикл ожидания подключений
            while (true) {
                System.out.println("Started, waiting for connection");

                Socket socket = serverSocket.accept();
                System.out.println("New client connected to server");

                Connection connection = new Connection(socket, this);
                connectionList.add(connection);

                System.out.println(connection.getName());
                System.out.println(Thread.currentThread());

                connection.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
