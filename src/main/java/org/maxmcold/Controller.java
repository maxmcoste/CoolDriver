package org.maxmcold;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.maxmcold.models.InputReader;
import org.maxmcold.models.InputReaderFactory;
import org.maxmcold.models.Readable;
import org.maxmcold.rules.Rule;
import org.maxmcold.rules.RuleFactory;
import org.maxmcold.utils.CoolProperties;



import java.io.IOException;
import java.util.Date;
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
            logger.debug("Test property: "+prop.getProperty("test"));
            logger.debug("Starting controller");

            InputReader ir = InputReaderFactory.getInputReader(Readable.Type.TEMPERATURE);
            Readable r = ir.getValues();
            Rule rule = RuleFactory.getRule();
            rule.perform(r);



            String log =
                    " --> Code: " + r.getCode()
                            +" Desc: "  + r.getDescription()
                            +" Value: " + r.getValues().get(CoolProperties.temperatureFieldName);//*/
            logger.debug("beep :: " + new Date() + " value: " + log);

        }catch(IOException e){
            logger.error(e.getMessage());
        }




    }


}

