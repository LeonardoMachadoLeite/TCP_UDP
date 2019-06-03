package model.server;

public interface ServerController {

    public void receiveJson(String jsonString, Connection connection);

}
