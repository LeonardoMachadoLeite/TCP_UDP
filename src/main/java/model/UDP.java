package model;

import java.io.IOException;

public class UDP implements Protocol{

    private CabecalhoUDP cabecalho;
    private Object data;


    public void startConnection(String sourceIp, int sourcePort, String destinationIp, int destinationPort,
                                int tcpLength) {
        this.cabecalho = new CabecalhoUDP(sourceIp, sourcePort, destinationIp, destinationPort, tcpLength, null);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data, String options) throws IOException {

        this.data = data;
        cabecalho.setHash(this.toString());

    }

    public String toString() {
        return String.format("{" +
                "cabecalho : %s, " +
                "data : %s" +
                "}",
                cabecalho.toString(),
                toJson(data));
    }

    private String toJson(Object o) {
        return o == null ? "null" : o.toString();
    }

    public void close() {

    }
}
