package model;

import java.io.IOException;
import java.io.Serializable;

public interface Protocol extends Serializable {

    public void startConnection(String sourceIp, int sourcePort, String destinationIp, int destinationPort,
                                int tcpLength, Byte[] checkSum);
    public Object getData();
    public void setData(String options, Object data) throws IOException ;
    public void close();

}
