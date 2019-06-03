package model.server;

import model.protocol.TCP;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServerControllerTCP implements ServerController{

    private ArrayList<String> requestList;
    private TreeMap<Integer, ACKTimer> ackController;

    public ServerControllerTCP() {
        this.ackController = new TreeMap<Integer, ACKTimer>();
        this.requestList = new ArrayList<String>(Arrays.asList(
                "get", "post", "put", "delete"));
    }

    private void request(String jsonString, Connection connection) {
        TCP tcp = new TCP(jsonString);
        int request = requestList.indexOf(tcp.getOptions());
        switch (request) {
            case 0:
                tcp.setData("get");
                tcp.addAck();
                tcp.addSeq();
                tcp.setHeader(connection.getHeader());
                connection.sendMessage(tcp.toString());
                ackController.put(connection.getHeader().toString().hashCode(),
                        new ACKTimer(tcp.toString(), connection));
                return;
            case 1:
                tcp.setData("post");
                tcp.addAck();
                tcp.setHeader(connection.getHeader());
                connection.sendMessage(tcp.toString());
                ackController.put(connection.getHeader().toString().hashCode(),
                        new ACKTimer(tcp.toString(), connection));
                return;
            case 2:
                tcp.setData("put");
                tcp.addAck();
                tcp.setHeader(connection.getHeader());
                connection.sendMessage(tcp.toString());
                ackController.put(connection.getHeader().toString().hashCode(),
                        new ACKTimer(tcp.toString(), connection));
                return;
            case 3:
                tcp.setData("delete");
                tcp.addAck();
                tcp.setHeader(connection.getHeader());
                connection.sendMessage(tcp.toString());
                ackController.put(connection.getHeader().toString().hashCode(),
                        new ACKTimer(tcp.toString(), connection));
                return;
            default:
        }
    }

    public void receiveJson(String jsonString, Connection connection) {
        JSONObject json = new JSONObject(jsonString);
        String options = json.getString("options");
        if (options.equals("ack")) {
            ACKTimer aux = ackController.remove(connection.getHeader().toString().hashCode());
            if (aux != null) {
                aux.ackReceived.set(false);
            }
        } else {
            request(jsonString, connection);
        }
    }

    private class ACKTimer extends Thread{

        Connection connection;
        AtomicBoolean ackReceived;
        String jsonSent;

        public ACKTimer(String jsonSent, Connection connection) {
            this.jsonSent = jsonSent;
            this.ackReceived = new AtomicBoolean(false);
            this.connection = connection;
        }

        public void run() {

            while (!ackReceived.get()) {
                try {
                    wait(1000);
                    connection.sendMessage(jsonSent);
                } catch (InterruptedException e) {
                    System.err.println("Error waiting for ack of " + jsonSent);
                    e.printStackTrace();
                }
            }
        }
    }

}
