package model.server;

import org.json.JSONObject;

public class ServerController {

    public void request(String jsonString, Connection connection) {
        JSONObject json = new JSONObject(jsonString);
        int request = Integer.parseInt(json.getString("data"));
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

}
