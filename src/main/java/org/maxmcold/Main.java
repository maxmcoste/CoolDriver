package org.maxmcold;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.utils.DirUtilities;

import java.util.GregorianCalendar;

public  class Main {
    final static Logger logger = LogManager.getLogger(Controller.class.getName());

    public static void main(String[] args) {

        Controller c = new Controller();
        c.execute();



    }


}


