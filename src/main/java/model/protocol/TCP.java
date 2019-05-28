package model.protocol;

import org.json.JSONObject;

public class TCP {

    ////Atributos
    //Cabecalho
    private String sourceIp;
    private int sourcePort;
    private String destinationIp;
    private int destinationPort;
    //Resto do pacote
    private int seqNumber;
    private int ack;
    private int checksum;
    private String options;
    private String data;


    //Construtores
    public TCP(String sourceIp, int sourcePort, String destinationIp, int destinationPort) {
        this.sourceIp = sourceIp;
        this.sourcePort = sourcePort;
        this.destinationIp = destinationIp;
        this.destinationPort = destinationPort;
    }
    public TCP(String jsonString) {
        JSONObject json = new JSONObject(jsonString);
        JSONObject cabecalho = new JSONObject(json.getString("cabecalho"));

        sourceIp = cabecalho.getString("source_ip");
        sourcePort = cabecalho.getInt("source_port");
        destinationIp = cabecalho.getString("dest_ip");
        destinationPort = cabecalho.getInt("dest_port");

        seqNumber = json.getInt("seq");
        ack = json.getInt("ack");
        checksum = json.getInt("checksum");
        options = json.getString("options");
        data = json.getString("data");
    }


    //Getters/Setters
    public String getSourceIp() {
        return sourceIp;
    }
    public int getSourcePort() {
        return sourcePort;
    }
    public String getDestinationIp() {
        return destinationIp;
    }
    public int getDestinationPort() {
        return destinationPort;
    }
    public int getSeqNumber() {
        return seqNumber;
    }
    public void setSeqNumber(int seqNumber) {
        this.seqNumber = seqNumber;
    }
    public int getAck() {
        return ack;
    }
    public void setAck(int ack) {
        this.ack = ack;
    }
    public int getChecksum() {
        return checksum;
    }
    public String getOptions() {
        return options;
    }
    public void setOptions(String options) {
        this.options = options;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
        this.checksum = data.hashCode();
    }


    //Metodos
    public String toString() {
        JSONObject json = new JSONObject();
        JSONObject cabecalho = new JSONObject();

        cabecalho.put("source_ip", sourceIp);
        cabecalho.put("source_port", sourcePort);
        cabecalho.put("dest_ip", destinationIp);
        cabecalho.put("dest_port", destinationPort);

        json.put("cabecalho", cabecalho.toString());
        json.put("seq", seqNumber);
        json.put("ack", ack);
        json.put("checksum", checksum);
        json.put("options", options);
        json.put("data", data);

        return json.toString();
    }
}
