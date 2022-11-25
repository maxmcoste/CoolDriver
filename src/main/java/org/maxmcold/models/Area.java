package org.maxmcold.models;

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
            Sensor s = new Sensor();
            ReadableImpl r = null;
            if (sensorList[i].equals(CoolProperties.temperatureCodeName)){
                r = new ReadableImpl(ReadableImpl.Type.TEMPERATURE);
                s.setReadable(r);
            }
            if (sensorList[i].equals(CoolProperties.windowstatusCodeName)){
                r = new ReadableImpl(ReadableImpl.Type.WINDOWPOSITION);
                s.setReadable(r);
            }
            if (sensorList[i].equals(CoolProperties.humidityCodeName)){
                r = new ReadableImpl(ReadableImpl.Type.HUMIDITY);
                s.setReadable(r);
            }
            if (sensorList[i].equals(CoolProperties.sunCodeName)){
                r = new ReadableImpl(ReadableImpl.Type.SUNPOSITION);
                s.setReadable(r);
            }
            if (sensorList[i].equals(CoolProperties.awningsCodeName)){
                r = new ReadableImpl(ReadableImpl.Type.AWNINGINPUT);
                s.setReadable(r);
            }

            if (r == null) throw new ArrayIndexOutOfBoundsException("No sensor found for readable");
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
