package model.server;

import java.io.IOException;
import java.net.Socket;

public class Connection extends Thread{

    private Socket socket;
    private InputThread input;
    private OutputThread output;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        input = new InputThread(getInstance(), socket.getInputStream());
        output = new OutputThread(getInstance(), socket.getOutputStream());
    }

    private Connection getInstance() {
        return this;
    }

    public void start() {
        input.start();
        output.start();
        super.start();
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

    public void verifyStreams() {
        input.setOpen(!socket.isInputShutdown());
        output.setOpen(!socket.isOutputShutdown());
    }

    //Getters
    public Socket getSocket() {
        return socket;
    }



}
