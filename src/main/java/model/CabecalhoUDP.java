package model;

import com.twmacinta.util.MD5;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Vector;

public class CabecalhoUDP extends Cabecalho {

    private byte[] checkSum;

    public CabecalhoUDP(String sourceIp, int sourcePort, String destinationIp, int destinationPort,
                        int tcpLength, byte[] checkSum) {
        super(sourceIp, sourcePort, destinationIp, destinationPort, tcpLength);
        this.checkSum = checkSum;
    }

    public byte[] getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(byte[] checkSum) {
        this.checkSum = checkSum;
    }

    public String toString() {
        return String.format("{ " +
                        "source : %s, " +
                        "destination : %s, " +
                        "length : %d, " +
                        "check_sum : %s" +
                        " }",
                toStringAddress(sourceIp,sourcePort),
                toStringAddress(destinationIp, destinationPort),
                tcpLength,
                checksumToString());
    }

    void setHash(String udp) throws IOException {

        File f = new File("msg.txt");
        FileWriter writer = new FileWriter(f);
        writer.append(this.toString());
        writer.flush();
        writer.close();
        checkSum = MD5.getHash(f);

    }

    public String checksumToString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        boolean first = true;
        for (byte b : checkSum) {
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

}
