package model.server;

import model.Protocol;
import model.protocol.TCP;

import java.io.IOException;
import java.net.Socket;

public class TCPServerConnection extends Thread{

    private Socket socket;
    private TCP protocol;
    private InputThread input;
    private OutputThread output;

    public TCPServerConnection(Socket socket, TCP protocol) throws IOException {
        this.socket = socket;
        this.protocol = protocol;
        input = new InputThread(getInstance(), socket.getInputStream());
        output = new OutputThread(getInstance(), socket.getOutputStream());
    }

    private TCPServerConnection getInstance() {
        return this;
    }

    public void start() {
        input.start();
        output.start();
        super.start();
        sendACK();
    }

    public void run() {

        String response;

        while (!socket.isClosed()) {

            verifyStreams();

            if (!input.receivedMsgs.isEmpty()) {

                response = input.receivedMsgs.poll();

                System.out.println(response);

            }

        }

    }

    void sendACK() {
        protocol.addAck();
    }

    public void verifyStreams() {
        input.setOpen(!socket.isInputShutdown());
        output.setOpen(!socket.isOutputShutdown());
    }

    //Getters
    public Socket getSocket() {
        return socket;
    }
    public TCP getProtocol() {
        return protocol;
    }
    public InputThread getInput() {
        return input;
    }
    public OutputThread getOutput() {
        return output;
    }
}
