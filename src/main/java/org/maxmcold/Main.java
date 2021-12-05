package org.maxmcold;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public  class Main {
    public static void main(String[] args) {
        //Controller c = new Controller();
        //c.execute();

        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        AtomicLong al = new AtomicLong(0);
        ScheduledFuture<?> scheduleFuture = ses.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("running");
                al.incrementAndGet();
            }
        }, 5, 5, TimeUnit.SECONDS);
        System.out.println("task scheduled");
        try {
            Thread.sleep(scheduleFuture.getDelay(TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true) {
            //System.out.println(scheduleFuture.isDone()); will always print false
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long l = al.get();
            System.out.println(l);
            if (l >= 5) {
                System.out.println("cancelling");
                scheduleFuture.cancel(true);
                ses.shutdown();
                break;
            }
        }
    }


}


