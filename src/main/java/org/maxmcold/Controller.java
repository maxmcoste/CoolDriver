package org.maxmcold;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.models.InputReader;
import org.maxmcold.models.OutputWriter;
import org.maxmcold.models.Readable;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;


public class Controller implements Runnable{
    final static Logger logger = LogManager.getLogger(Controller.class.getName());

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);
    private static int initialDelay = 5;
    private static int delay =5;

    @SuppressWarnings("rawtypes")
    public void execute() {
       ScheduledFuture handleAtFixedDelay = scheduler.scheduleWithFixedDelay(this, initialDelay, delay, SECONDS);
    }

    @Override
    public void run() {
        logger.debug("Starting controller");
        InputReader ir = new InputReader(Readable.Type.TEMPERATURE);

        Readable r = ir.getValues();
        logger.debug(r.getCode());
        String log =
                " --> Code: " + r.getCode()
                +" Desc: "  + r.getDescription()
                +" Value: " + r.getValues().get("temp");//*/
        logger.debug("beep :: " + new Date() + " value: " + log);
    }
}

