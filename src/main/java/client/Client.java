package client;

import messages.Message;
import net.ConnectionHandler;
import net.ProtocolException;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client  implements ConnectionHandler {

    private int port;
    private  BufferedReader reader;
    private PrintWriter writer;
    private Scanner scanner;

    public Client(int port) {
        this.port = port;
        scanner = new Scanner(System.in);

    }

    //Создает сокет, ридер райтер и запускает логику
    public void start() {

        System.out.println("IP Adresse (Localhost 127.0.0.1).");
        System.out.println("Format: xxx.xxx.xxx.xxx");

        String ip = scanner.nextLine();
        //Создаем клиентский сокет
        try (Socket socket = new Socket(ip, this.port)) {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
            System.out.println("Имя в чате");
            writer.println(scanner.nextLine());
            //Получаем ответ
          //  String str = reader.readLine();
          //  System.out.println(str);

            EchoThread echoThread = new EchoThread();
            echoThread.start();
            String str = "";
            while(!str.equals("exit")) {
                str = scanner.nextLine();
                writer.println(str);
            }
            //Запуск логики приложения
      //      this.logicStart();
            echoThread.setStoped(true);
            socket.close();
            this.reader.close();
            this.writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private class EchoThread extends Thread {

        private boolean isStoped = false;

        @Override
        public void run() {
            try {
                while(!isStoped) {
                    String str = reader.readLine();
                    System.out.println(str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        public void setStoped(boolean stoped) {
            this.isStoped = stoped;
        }


    }
    public void logicStart() {
        //Логика приложения
        while(true) {

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
