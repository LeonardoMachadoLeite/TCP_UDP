package model;

import java.io.IOException;

public class UDP implements Protocol{

    private CabecalhoUDP cabecalho;
    private Object data;


    public void startConnection(String sourceIp, int sourcePort, String destinationIp, int destinationPort,
                                int tcpLength, Byte[] checkSum) {
        this.cabecalho = new CabecalhoUDP(sourceIp, sourcePort, destinationIp, destinationPort, tcpLength, checkSum);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data, String options) throws IOException {

        this.data = data;

    }

    public void close() {

    }
}
