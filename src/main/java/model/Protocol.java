package model;

import java.io.IOException;
import java.io.Serializable;

public interface Protocol extends Serializable {

    public void startConnection(String sourceIp, int sourcePort, String destinationIp, int destinationPort,
                                int tcpLength);
    public Object getData();
    public void setData(Object data, String options) throws IOException ;
    public void close();

}
