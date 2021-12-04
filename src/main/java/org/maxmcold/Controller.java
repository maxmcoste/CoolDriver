package org.maxmcold;
import org.maxmcold.models.OutputWriter;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;


public class Controller {


    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    // ScheduledExecutorService created with 3 threads
    private static int initialDelay = 5;
    private static int delay =5;

    @SuppressWarnings("rawtypes")
    public static void execute() {

        final Runnable control = new Runnable() {
            public void run() {
                System.out.println("beep :: " + new Date() ); }
        }; // Creating a new runnable task which will be passed as an argument to scheduler

        ScheduledFuture handleAtFixedDelay = scheduler.scheduleWithFixedDelay(control, initialDelay, delay, SECONDS);
        // Creates and executes a ScheduledFuture that becomes enabled after 5 seconds and gets executed with fixed delay of 5 seconds


        scheduler.schedule(
                new Runnable() {
                    public void run() {
                        handleAtFixedDelay.cancel(false);
                    } // Attempts to cancel execution of task beeperHandleArFixedDelay after one hour
                },

                60,
                SECONDS
        );

    }









}
