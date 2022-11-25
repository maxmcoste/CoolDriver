package org.maxmcold;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.maxmcold.models.*;
import org.maxmcold.models.Readable;
import org.maxmcold.models.ReadableImpl;
import org.maxmcold.readable.ReadableFactory;
import org.maxmcold.utils.CoolProperties;
import org.maxmcold.utils.RuleParser;


import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;


public class Controller implements Runnable{

    final static Logger logger = LogManager.getLogger(Controller.class.getName());
    Properties prop;
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);
    private static int initialDelay = 5;
    private static int delay =5;


    @SuppressWarnings("rawtypes")
    public void execute() {
        ScheduledFuture handleAtFixedDelay = scheduler.scheduleWithFixedDelay(this, initialDelay, delay, SECONDS);
    }

    @Override
    public void run() {
       try{

           prop = CoolProperties.getProperties();
           logger.debug("Starting controller");

           Area area = new Area("Salotto");
           logger.debug("getting sensor list");

           HashMap<String, Sensor> sensors = area.getSensors();

           //TODO: just print for now
           RuleParser rp = new RuleParser();
           String[] tokens = rp.parse();

           org.maxmcold.models.Readable readme = ReadableFactory.getReadable(ReadableFactory.Type.TEMPERATURE);
           long value = readme.getValue();

           for (int i=0; i< tokens.length; logger.debug(tokens[i++]+"\n"));


           Iterator<Sensor> iterator = sensors.values().iterator();

           while (iterator.hasNext()){

               Sensor s =iterator.next();
               ReadableImpl r = s.getReadable();

               logger.debug("sensor description --->" + s.getFullDescription());
               HashMap<String,Object> values = r.getValues();

               String log = " --> Code: " + r.getCode()
                               +" Desc: "  + r.getDescription()
                               +" Value: " + values.toString();
               logger.debug("beep :: " + new Date() + " value: " + log);//*/
           }


        }catch(Exception e){
            logger.error(e.getMessage(), e);
        }
    }

}

