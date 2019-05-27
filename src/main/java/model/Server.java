package model;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server implements Closeable, Runnable {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private Protocol protocol;

    public Server(int portNumber, InetAddress address, Protocol protocol) throws IOException {

        this.serverSocket = new ServerSocket(portNumber, 1, address);

        InetAddress inet = serverSocket.getInetAddress();
        System.out.println("HostAddress = " + inet.getHostAddress());
        System.out.println("HostName = " + inet.getHostName());

        this.protocol = protocol;
    }

    public Server(int portNumber, Protocol protocol) throws IOException {

        this.serverSocket = new ServerSocket(portNumber);
        this.protocol = protocol;

        this.serverSocket = new ServerSocket(portNumber, 1);

        InetAddress inet = serverSocket.getInetAddress();
        System.out.println("HostAddress = " + inet.getHostAddress());
        System.out.println("HostName = " + inet.getHostName());
    }

    public void setIDLE() throws IOException {

        System.out.println("The server is waiting for a client");

        clientSocket = this.serverSocket.accept();

        protocol.startConnection(
                serverSocket.getInetAddress().getHostAddress(),
                serverSocket.getLocalPort(),
                clientSocket.getInetAddress().getHostAddress(),
                clientSocket.getPort(),
                0, null);

        System.out.println("The server has connected to client");
    }

    public void sendData() throws IOException {

        protocol.setData("Options", "Hello World!");

        PrintStream printStream = new PrintStream(clientSocket.getOutputStream());
        //System.out.println("Server : " + protocol);
        printStream.println("Server(printStream): " + protocol);

    }

    public void closeCurrentConnection() throws IOException {

/*        input.close();
        output.close();*/
        clientSocket.close();

        System.out.println("The server has closed it's connection with the client");
    }

    public void close() throws IOException {

        closeCurrentConnection();
        serverSocket.close();

        System.out.println("The server has closed");
    }

    public void run() {
        try {

            this.setIDLE();

            this.sendData();

/*            Scanner s = new Scanner(clientSocket.getInputStream());
            while (s.hasNextLine()) {
                String line = s.nextLine();
                System.out.println("Server(echo): " + line);
                if (line.equalsIgnoreCase("quit")) {
                    break;
                }
            }*/

            this.close();
        } catch (IOException e) {
            System.err.println("Error on the server side");
            e.printStackTrace();
        }
    }
}
