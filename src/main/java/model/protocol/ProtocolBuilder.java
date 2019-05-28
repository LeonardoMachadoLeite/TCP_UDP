package model.protocol;

import model.Protocol;

public class ProtocolBuilder {

    private int protocol;

    public ProtocolBuilder(int protocolType) {
        this.protocol = protocolType;
    }

    public Protocol build() {
        switch (protocol){
            case 1:
                return null;
            case 2:
                return null;
            default:
                return null;
        }
    }

}
