package model.server;

import model.protocol.UDP;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class ServerControllerUDP implements ServerController{

    private ArrayList<String> requestList;

    public ServerControllerUDP() {
        this.requestList = new ArrayList<String>(Arrays.asList(
                "get", "post", "put", "delete"));
    }

    public void receiveJson(String jsonString, Connection connection) {
        UDP udp = new UDP(jsonString);
        int request = requestList.indexOf(udp.getData());
        switch (request) {
            case 0:
                udp.setData("get");
                udp.setHeader(connection.getHeader());
                connection.sendMessage(udp.toString());
                return;
            case 1:
                udp.setData("post");
                udp.setHeader(connection.getHeader());
                connection.sendMessage(udp.toString());
                return;
            case 2:
                udp.setData("put");
                udp.setHeader(connection.getHeader());
                connection.sendMessage(udp.toString());
                return;
            case 3:
                udp.setData("delete");
                udp.setHeader(connection.getHeader());
                connection.sendMessage(udp.toString());
                return;
            default:
        }
    }

}
