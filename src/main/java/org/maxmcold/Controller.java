package org.maxmcold;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import org.maxmcold.models.*;
import org.maxmcold.readable.Readable;

import org.maxmcold.rules.Rule;
import org.maxmcold.rules.RuleFactory;
import org.maxmcold.utils.CoolProperties;



import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;


public class Controller implements Runnable{

    public Controller() throws IOException {

        CoolProperties.loadProperties();
        prop = CoolProperties.getProperties();


    }

    final static Logger logger = LogManager.getLogger(Controller.class.getName());
    Properties prop;
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);
    private static int initialDelay = 5;
    private static int delay = 10;

    public static void setDelay(int readDelay){
        delay = readDelay;
    }
    @SuppressWarnings("rawtypes")
    public void execute() {


        Controller.delay = Integer.parseInt(CoolProperties.delay);
        scheduler.scheduleWithFixedDelay(this, initialDelay, delay, SECONDS);
    }

    @Override
    public void run() {
       try{
           logger.debug("Starting controller execution...");
           Area area = new Area("Salotto");
           logger.debug("getting sensor list for area "+area.getName());
           HashMap<String, Sensor> sensors = area.getSensors();

           //TODO: just print for now
           Rule rule = RuleFactory.getRule();
           logger.debug("[[[[[[[[[[[ INIT CONDITION RECURSIVE CHECK");
           rule.executeAll();
           //logger.debug("EVAL STATEMENT: "+rule.getAction());
           logger.debug("]]]]]]]]]]] END CONDITION RECURSIVE CHECK");
           //TODO group sensors by area


           Iterator<Sensor> iterator = sensors.values().iterator();

           while (iterator.hasNext()){

               Sensor s =iterator.next();
               Readable r = s.getReadable();
               logger.debug("sensor description --->" + s.getDesc());
               HashMap<String,Object> values = r.getValues();
               String log = " Value: " + values.toString();
               logger.debug("beep :: " + new Date() + " value: " + log);//*/
           }


        }catch(Exception e){
            logger.error(e.getMessage(), e);
        }
    }

}

