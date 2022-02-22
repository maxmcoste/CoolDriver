package org.maxmcold.models;

import org.maxmcold.utils.CoolProperties;

import java.util.HashMap;

public class House {

    public HashMap<String, Area> getAreas() {
        return areas;
    }

    public void setAreas(HashMap<String, Area> areas) {
        this.areas = areas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private HashMap<String, Area> areas;
    private String name;
    private String description;
    public  House(){
        this.name = CoolProperties.houseName;
        this.description = CoolProperties.houseDescription;
        //initial configuration: only one area in the house - to be extended
        Area a = new Area();

        areas.put(name,a);
    }

}
