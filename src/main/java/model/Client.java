package model;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable{

    Socket socket;
    Protocol protocol;

    public Client(InetAddress address, int port, InetAddress localAddr, int localPort, Protocol protocol) throws IOException {

        socket = new Socket(address, port, localAddr, localPort);

        this.protocol = protocol;
    }

    public void run() {

        try {

            System.out.println("The client is running");

            Scanner scan = new Scanner(socket.getInputStream());

            while (scan.hasNextLine()) {
                System.out.println(scan.nextLine());
            }

            /*synchronized (Thread.currentThread()) {
                Thread.currentThread().wait(1000);
            }*/


            //protocol = protocol.deserialize(input);

            //System.out.println("Client : " + protocol);

            close();
        } catch (IOException e) {
            System.err.println("Error on closing client connection");
            e.printStackTrace();
        } /*catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    public void close() throws IOException {

/*        input.close();
        out.close();*/
        socket.close();

        System.out.println("The client connection has closed");
    }

}
