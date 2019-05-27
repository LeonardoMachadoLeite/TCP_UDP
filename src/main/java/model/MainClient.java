package model;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class MainClient {

    public static void main(String[] args) throws IOException, InterruptedException {

        int serverPort = 40000;
        int clientPort = 16000;
        String ip = "10.144.0.150";
        
        InetSocketAddress serverAddress = new InetSocketAddress(ip,serverPort);

        Thread client = new Thread(new Client(
                serverAddress.getAddress(),serverPort,
                InetAddress.getLocalHost(),clientPort, new TCP()));

        client.start();
    }

}
