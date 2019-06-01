package model.server;

import java.io.*;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class InputThread extends Thread {

    private Connection connection;
    private BufferedReader in;
    private AtomicBoolean open = new AtomicBoolean(true);
    Queue<String> receivedMsgs;

    public InputThread(Connection connection, InputStream inputStream) {
        this.connection = connection;
        this.in = new BufferedReader(new InputStreamReader(inputStream));
        this.receivedMsgs = new ConcurrentLinkedQueue<String>();
    }

    public void run() {

        String msg;

        while (open.get()) {

            try {

                if (in.ready()) {

                    msg = in.readLine();
                    receivedMsgs.add(msg);

                }

            } catch (IOException e) {
                System.err.println("Error receiving a message");
                e.printStackTrace();
            }
        }

    }

    public boolean isOpen() {
        return open.get();
    }

    public boolean setOpen(boolean open) {
        return this.open.getAndSet(open);
    }

}
