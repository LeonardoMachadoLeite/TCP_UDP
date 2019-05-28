package model;

import java.io.*;

import com.twmacinta.util.MD5;

public class TCP implements Protocol{

    private Cabecalho cabecalho;
    private int seqNumber;
    private int ack = 1;
    private int maxLength;
    private byte[] checksum = new byte[32];
    private String options;
    private Object data;

    public TCP() {}

    public void startConnection(String sourceIp, int sourcePort, String destinationIp, int destinationPort,
                                int tcpLength){
        this.cabecalho = new Cabecalho(sourceIp, sourcePort, destinationIp, destinationPort, tcpLength);
    }

    public Object getData() {
        return options;
    }

    public void close() {


    }

    public void setData(Object data, String options) throws IOException {
        this.options = options;
        this.data = data;
        setHash();
    }

    public String checksumToString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        boolean first = true;
        for (byte b : checksum) {
            if (first) {
                builder.append(b);
                first = false;
            } else {
                builder.append(", ");
                builder.append(b);
            }
        }
        builder.append("]");
        return builder.toString();
    }


    public String toString() {
        return String.format("{ " +
                "cabecalho : %s, " +
                "seq_number : %d, " +
                "ack : %d, " +
                "checksum : %s, " +
                "max_len : %d, " +
                "options : %s, " +
                "data : %s" +
                " }",
                toJson(cabecalho),
                seqNumber,
                ack,
                checksumToString(),
                maxLength,
                jsonString(options),
                toJson(data));
    }

    private String jsonString(String o) {
        return o == null ? "null" : String.format("\"%s\"", o);
    }

    private String toJson(Object o) {
        return o == null ? "null" : o.toString();
    }

    private void setHash() throws IOException {

        File f = new File("msg.txt");
        FileWriter writer = new FileWriter(f);
        writer.append(this.toString());
        writer.flush();
        writer.close();
        checksum = MD5.getHash(f);

    }

}
