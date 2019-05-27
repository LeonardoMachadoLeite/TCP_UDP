package model;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        int serverPort = 40000;
        int clientPort = 16000;
        String ip = "127.0.0.1";
        InetSocketAddress serverAddress = new InetSocketAddress("127.0.0.1",serverPort),
                clientAddress = new InetSocketAddress("127.0.0.1",clientPort);

        Thread server = new Thread(new Server(serverPort, serverAddress.getAddress(), new TCP()));

        server.start();

        Thread client = new Thread(new Client(
                serverAddress.getAddress(),serverPort,
                clientAddress.getAddress(),clientPort, new TCP()));

        client.start();

        server.join();

    }

}
