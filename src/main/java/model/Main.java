package model;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        int serverPort = 40000;
        String ip = InetAddress.getLocalHost().getHostAddress();
        InetSocketAddress serverAddress = new InetSocketAddress(ip,serverPort);

        Thread server = new Thread(new Server(serverPort, serverAddress.getAddress(), new TCP()));

        server.start();

        server.join();

    }

}
