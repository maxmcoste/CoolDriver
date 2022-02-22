package org.maxmcold.rules;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.actions.BoilerAction;
import org.maxmcold.actuators.Actuator;
import org.maxmcold.Controller;
import org.maxmcold.models.Action;
import org.maxmcold.utils.CoolProperties;

import java.util.HashMap;
import org.maxmcold.models.Readable;


public class BoilerRule implements Rule{
    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String code;
    private String name;
    private Long refTemp ;
    final static Logger logger = LogManager.getLogger(Controller.class.getName());
    public Action getAction(){
        return new BoilerAction();
    }
    public int perform(Readable r, Actuator a){

        logger.debug("Entering Actuator");
        HashMap<String,Object> hash = r.getValues();
        refTemp = CoolProperties.Temperature.refTemperature;
        Long currentTemp;

        try {

            currentTemp = (Long) hash.get(CoolProperties.temperatureFieldName);

            if (currentTemp.intValue() >= refTemp.intValue()){

                logger.info("current Temp:"+ currentTemp+ " HIGHER THAN refTemp:"+refTemp);
                a.switchOff();
            }
            if (currentTemp.intValue() < refTemp.intValue()){
                logger.info("current Temp:"+ currentTemp+ " LOWER THAN refTemp:"+refTemp);

                a.switchOn();
            }

        }catch(Exception e){
            logger.error(e.getMessage());
        }


        return 1;
    }

}
