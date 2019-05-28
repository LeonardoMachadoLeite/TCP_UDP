package model.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class OutputThread extends Thread{

    private TCPServerConnection connection;
    private DataOutputStream out;
    private AtomicBoolean open = new AtomicBoolean(true);
    private Queue<String> sendQueue;

    public OutputThread(TCPServerConnection connection, OutputStream outputStream) {
        this.connection = connection;
        this.out = new DataOutputStream(outputStream);
        this.sendQueue = new ConcurrentLinkedQueue<String>();
    }

    public void run() {

        String msg;

        while (open.get()) {

            if (!sendQueue.isEmpty()) {

                msg = sendQueue.poll();

                try {

                    out.writeChars(msg);

                } catch (IOException e) {
                    System.err.println("Error sending: " + msg);
                    System.err.println("Will try to send again");
                    sendQueue.add(msg);
                }

            }
        }

    }

    public void sendMsg(String msg) {
        sendQueue.add(msg);
    }

    public AtomicBoolean getOpen() {
        return open;
    }

    public boolean setOpen(boolean open) {
        return this.open.getAndSet(open);
    }
}
