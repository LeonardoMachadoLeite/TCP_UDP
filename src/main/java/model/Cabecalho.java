package model;

import java.io.*;

public class Cabecalho implements Serializable {

    protected String sourceIp;
    protected int sourcePort;
    protected String destinationIp;
    protected int destinationPort;
    protected int tcpLength;

    public Cabecalho(String sourceIp, int sourcePort, String destinationIp, int destinationPort, int tcpLength) {
        this.sourceIp = sourceIp;
        this.sourcePort = sourcePort;
        this.destinationIp = destinationIp;
        this.destinationPort = destinationPort;
        this.tcpLength = tcpLength;
    }

    public int getTcpLength() {
        return tcpLength;
    }

    public void setTcpLength(int tcpLength) {
        this.tcpLength = tcpLength;
    }

    public String toString() {
        return String.format("{ " +
                "source : %s, " +
                "destination : %s, " +
                "length : %d" +
                " }",
                toStringAddress(sourceIp,sourcePort),
                toStringAddress(destinationIp, destinationPort),
                tcpLength);
    }

    protected String toStringAddress(String ip, int port) {
        return String.format("{ " +
                "ip : \"%s\", " +
                "port : %d" +
                " }",
                ip, port);
    }

    /*    public OutputStream serializeCabecalho(OutputStream outStream) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(outStream);
        out.writeObject(source);
        out.writeObject(destination);
        out.write(tcpLength);
        return out;
    }

    public InputStream deserializeCabecalho() {

    }*/

}
