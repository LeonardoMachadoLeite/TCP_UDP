package model.server;

import org.json.JSONObject;

import java.io.IOException;
import java.net.Socket;

public class Connection extends Thread{

    ServerController controller;
    private Socket socket;
    private InputThread input;
    private OutputThread output;

    public Connection(Socket socket,ServerController controller) throws IOException {
        this.socket = socket;
        this.controller = controller;
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
                controller.receiveJson(response, this);

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

    public void sendMessage(String msg) {
        output.sendMsg(msg);
    }

    public JSONObject getHeader() {
        JSONObject header = new JSONObject();
        header.put("source_id",socket.getLocalAddress().getHostName());
        header.put("source_port",socket.getLocalPort());
        header.put("dest_ip",socket.getInetAddress().getHostName());
        header.put("dest_port",socket.getPort());
        return header;
    }

}
