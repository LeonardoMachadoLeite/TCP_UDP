package model.server;

import model.Protocol;

import java.io.IOException;
import java.net.Socket;

public class ServerConnection {

    private Socket socket;
    private Protocol protocol;
    private InputThread input;
    private OutputThread output;

    public ServerConnection(Socket socket, Protocol protocol) throws IOException {
        this.socket = socket;
        this.protocol = protocol;
        input = new InputThread(getInstance(), socket.getInputStream());
        output = new OutputThread(getInstance(), socket.getOutputStream());
    }

    private ServerConnection getInstance() {
        return this;
    }

    public void start() {
        input.start();
        output.start();
    }

    //Getters
    public Socket getSocket() {
        return socket;
    }
    public Protocol getProtocol() {
        return protocol;
    }
    public InputThread getInput() {
        return input;
    }
    public OutputThread getOutput() {
        return output;
    }
}
