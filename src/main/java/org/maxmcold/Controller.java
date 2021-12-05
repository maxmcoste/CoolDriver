package org.maxmcold;
import org.maxmcold.models.OutputWriter;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;


public class Controller implements Runnable{

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
    private static int initialDelay = 5;
    private static int delay =5;

    @SuppressWarnings("rawtypes")
    public void execute() {
       ScheduledFuture handleAtFixedDelay = scheduler.scheduleWithFixedDelay(this, initialDelay, delay, SECONDS);
    }
    @Override
    public void run() {
        System.out.println("beep :: " + new Date() );
    }
}

