package model.server;

import java.io.*;

public class InputThread extends Thread {

    ServerConnection serverConnection;
    BufferedReader in;
    boolean open = true;

    public InputThread(ServerConnection serverConnection, InputStream inputStream) {
        this.serverConnection = serverConnection;
        this.in = new BufferedReader(new InputStreamReader(inputStream));
    }

    public void run() {

        while (open) {
            try {

                in.readLine();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
