package org.maxmcold.models;

public class Sensor {

    private ReadableImpl readable;
    public Sensor(){


    }

    public ReadableImpl getReadable() {
        return readable;
    }

    public void setReadable(ReadableImpl readable) {
        this.readable = readable;
    }



    public String getFullDescription(){

        String out = new String();
        out = "Readable object -  Code: " + readable.getCode() + " | Description: " + readable.getDescription();
        return out;
    }

}
