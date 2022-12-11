package org.maxmcold.actions;

import org.maxmcold.models.Action;

public class BoilerAction  {

    public int execute(String actionType){
        switch (actionType){
            case "on" -> this.switchOn();
            case "off" -> this.switchOff();
        }
        return 0;
    }
    private void switchOn(){

    }
    private void switchOff(){

    }
}
