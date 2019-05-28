package model.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class OutputThread extends Thread{

    private ServerConnection serverConnection;
    private DataOutputStream out;
    private boolean open = true;

    public OutputThread(ServerConnection serverConnection, OutputStream outputStream) {
        this.serverConnection = serverConnection;
        this.out = new DataOutputStream(outputStream);
    }

    public void run() {

        while (open) {

            try {

                out.writeChars("");

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
