package model.server;

import model.protocol.TCP;
import org.json.JSONObject;

import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServerController {


    private TreeMap<Integer, Connection> clientConnections;
    private TreeMap<Integer, ACKTimer> ackController;

    private void request(String jsonString, Connection connection) {
        JSONObject json = new JSONObject(jsonString);
        int request = Integer.parseInt(json.getString("options"));
        switch (request) {
            case 0:

                return;
            case 1:

                return;
            case 2:

                return;
            case 3:

                return;
            case 4:

                return;
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
