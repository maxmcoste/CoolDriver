package org.maxmcold.models;

import org.maxmcold.readable.Readable;
import org.maxmcold.utils.CoolProperties;

import java.util.HashMap;

public class Area {

    public HashMap<String,Sensor> sensors;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;
    public Area(String n){
        this.name = n;
        this.init();

    }
    private void init(){
        String sensorList[] = CoolProperties.activeSensor.split(",");
        //initialize the sensors Hashmap
        sensors = new HashMap<>();

        for (int i =0; i<sensorList.length; i++){
            Sensor s = new Sensor(sensorList[i]);
            Readable r = s.getReadable();
            if (r == null) throw new ArrayIndexOutOfBoundsException("No inputreader initiated for sensor");
            this.sensors.put(sensorList[i],s);

        }
    }
    public Area() {
        this.init();

    }

    public HashMap<String, Sensor> getSensors() {
        return sensors;
    }
}
