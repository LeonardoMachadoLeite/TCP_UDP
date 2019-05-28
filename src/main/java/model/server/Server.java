package model.server;

import model.Protocol;
import model.protocol.ProtocolBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.TreeMap;

public class Server {

    private ServerSocket serverSocket;
    private TreeMap<String,ServerConnection> connections;
    private ProtocolBuilder builder;

    public Server(int localPort, InetAddress address, ProtocolBuilder builder) throws IOException {
        serverSocket = new ServerSocket(localPort, 10, address);
        connections = new TreeMap<String, ServerConnection>();
        this.builder = builder;
    }

    public void setIDLE() throws IOException {

        Socket socket = serverSocket.accept();
        connections.put(socket.getInetAddress().getHostAddress(),
                new ServerConnection(socket, builder.build()));

    }

}
