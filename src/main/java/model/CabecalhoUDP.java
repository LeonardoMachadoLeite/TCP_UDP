package model;

import java.lang.reflect.Array;
import java.util.Vector;

public class CabecalhoUDP extends Cabecalho {

    private Byte[] checkSum;

    public CabecalhoUDP(String sourceIp, int sourcePort, String destinationIp, int destinationPort,
                        int tcpLength, Byte[] checkSum) {
        super(sourceIp, sourcePort, destinationIp, destinationPort, tcpLength);
        this.checkSum = checkSum;
    }

    public Byte[] getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(Byte[] checkSum) {
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
