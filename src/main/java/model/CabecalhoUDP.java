package model;

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
}
