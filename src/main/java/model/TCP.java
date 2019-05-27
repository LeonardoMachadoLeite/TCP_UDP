package model;

import java.io.*;

import com.twmacinta.util.MD5;
import com.twmacinta.util.MD5InputStream;

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
                                int tcpLength, Byte[] checkSum){
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
        File f = new File("data.txt");
        FileOutputStream fOut = new FileOutputStream(f);
        ObjectOutputStream out = new ObjectOutputStream(fOut);
        out.writeObject(options);
        out.writeObject(data);
        out.flush();
        checksum = MD5.getHash(f);
    }

    public String toString() {
        return String.format("{ " +
                "cabecalho : %s, " +
                "seq_number : %d, " +
                "ack : %d, " +
                "max_len : %d, " +
                "options : %s, " +
                "data : %s" +
                " }",
                toJson(cabecalho),
                seqNumber,
                ack,
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

}
