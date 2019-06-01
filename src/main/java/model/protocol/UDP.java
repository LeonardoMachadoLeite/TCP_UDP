package model.protocol;

import org.json.JSONObject;

public class UDP {

    ////Atributos
    //Cabecalho
    private String sourceIp;
    private int sourcePort;
    private String destinationIp;
    private int destinationPort;
    //Resto do pacote
    private int checksum;
    private String data;

    public UDP(String sourceIp, int sourcePort, String destinationIp, int destinationPort){
        this.sourceIp = sourceIp;
        this.sourcePort = sourcePort;
        this.destinationIp = destinationIp;
        this.destinationPort = destinationPort;
    }

    public UDP(String toStringJson){
        JSONObject json = new JSONObject(toStringJson);
        JSONObject cabecalho = new JSONObject(json.getString("cabecalho"));

        sourceIp = cabecalho.getString("source_ip");
        sourcePort = cabecalho.getInt("source_port");
        destinationIp = cabecalho.getString("dest_ip");
        destinationPort = cabecalho.getInt("dest_port");

        checksum = json.getInt("checksum");
        data = json.getString("data");
    }

    public String toStringJson(){
        JSONObject json = new JSONObject();
        JSONObject cabecalho = new JSONObject();

        cabecalho.put("source_ip", sourceIp);
        cabecalho.put("source_port", sourcePort);
        cabecalho.put("dest_ip", destinationIp);
        cabecalho.put("dest_port", destinationPort);

        json.put("cabecalho", cabecalho.toString());
        json.put("checksum", checksum);
        json.put("data", data);


        return json.toString();
    }

    //Getters e Setters
    public String getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

    public int getSourcePort() {
        return sourcePort;
    }

    public void setSourcePort(int sourcePort) {
        this.sourcePort = sourcePort;
    }

    public String getDestinationIp() {
        return destinationIp;
    }

    public void setDestinationIp(String destinationIp) {
        this.destinationIp = destinationIp;
    }

    public int getDestinationPort() {
        return destinationPort;
    }

    public void setDestinationPort(int destinationPort) {
        this.destinationPort = destinationPort;
    }

    public int getChecksum() {
        return checksum;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
