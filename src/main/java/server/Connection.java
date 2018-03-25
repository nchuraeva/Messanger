package server;

import messages.Message;
import net.ConnectionHandler;

import java.io.*;
import java.net.ProtocolException;
import java.net.Socket;
import java.util.Iterator;

/**
 * Здесь храним всю информацию, связанную с отдельным клиентом.
 * - объект User - описание пользователя
 * - сокеты на чтение/запись данных в канал пользователя
 */
public class Connection extends Thread implements ConnectionHandler {

    private Socket socket;
    private  BufferedReader reader;
    private PrintWriter writer;
    private Server server;
    private String name = "";

    public Connection(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
        try {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        try {
            name = reader.readLine();
            synchronized (server.getConnectionList()) {
                Iterator<Connection> iter = server.getConnectionList().iterator();
                while(iter.hasNext()) {
                    ((Connection) iter.next()).writer.println(name + " cames now");
                }
            }

            doWork();
            socket.close();
            writer.close();
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void doWork() throws IOException {
        String str="";
        while (true) {
            str = this.reader.readLine();
            if(str.equals("exit")) break;
            synchronized (server.getConnectionList()) {
                Iterator<Connection> iter = server.getConnectionList().iterator();
                while(iter.hasNext()) {
                    ((Connection) iter.next()).writer.println(name + ":" + str);
                }
            }
        }
        synchronized(server.getConnectionList()) {
            Iterator<Connection> iter = server.getConnectionList().iterator();
            while(iter.hasNext()) {
                ((Connection) iter.next()).writer.println(name + " has left");
            }
        }

    }


    @Override
    public void send(Message msg) throws ProtocolException, IOException {

    }

    @Override
    public void onMessage(Message msg) {

    }

    @Override
    public void close() {

    }


}
