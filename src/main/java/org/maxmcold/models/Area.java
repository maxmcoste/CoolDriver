package org.maxmcold.models;

import org.maxmcold.utils.CoolProperties;

import java.util.HashMap;

public class Area {

    public HashMap<String,Sensor> sensors;

    public Area() {

        String sensorList[] = CoolProperties.activeSensor.split(",");
        //initialize the sensors Hashmap
        sensors = new HashMap<>();

        for (int i =0; i<sensorList.length; i++){
            Sensor s = new Sensor();
            Readable r = null;
            if (sensorList[i].equals(CoolProperties.temperatureCodeName)){
                r = new Readable(Readable.Type.TEMPERATURE);
                s.setReadable(r);
            }
            if (sensorList[i].equals(CoolProperties.windowstatusCodeName)){
                r = new Readable(Readable.Type.WINDOWPOSITION);
                s.setReadable(r);
            }
            if (sensorList[i].equals(CoolProperties.humidityCodeName)){
                r = new Readable(Readable.Type.HUMIDITY);
                s.setReadable(r);
            }
            if (sensorList[i].equals(CoolProperties.sunCodeName)){
                r = new Readable(Readable.Type.SUNPOSITION);
                s.setReadable(r);
            }
            if (sensorList[i].equals(CoolProperties.awningsCodeName)){
                r = new Readable(Readable.Type.AWNINGINPUT);
                s.setReadable(r);
            }

            if (r == null) throw new ArrayIndexOutOfBoundsException("No sensor found for readable");
            this.sensors.put(sensorList[i],s);

        }
    }

    public HashMap<String, Sensor> getSensors() {
        return sensors;
    }
}
