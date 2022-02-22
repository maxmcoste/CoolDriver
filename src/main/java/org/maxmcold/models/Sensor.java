package org.maxmcold.models;

public class Sensor {

    private Readable readable;
    public Sensor(){

    }
    public Readable getReadable() {
        return readable;
    }

    public void setReadable(Readable readable) {
        this.readable = readable;
    }



    public String getFullDescription(){

        String out = new String();
        out = "Readable object -  Code: " + readable.getCode() + " | Description: " + readable.getDescription();
        return out;
    }

}
