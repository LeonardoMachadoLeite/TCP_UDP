package model.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.TreeMap;

public class Server {

    private ServerSocket serverSocket;
    private ServerController controller;
    private TreeMap<String, Connection> connections;

    public Server(int localPort, InetAddress address) throws IOException {
        serverSocket = new ServerSocket(localPort, 10, address);
        connections = new TreeMap<String, Connection>();
    }

    public void setIDLE() throws IOException {

        Socket socket = serverSocket.accept();
        connections.put(socket.getInetAddress().getHostAddress(),
                new Connection(socket, controller));

    }

}
