package org.maxmcold.io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.Controller;
import org.maxmcold.items.Item;
import org.maxmcold.readable.ReadableFactory;
import org.maxmcold.utils.CoolProperties;

import java.lang.reflect.InvocationTargetException;

public class OutputWriterFactory {

    final static Logger logger = LogManager.getLogger(Controller.class.getName());

    public static OutputWriter getOutputWriter(Item item) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        OutputWriter out = (OutputWriter) new FileOutputWriter();

        String packageName = InputReaderFactory.class.getPackageName();
        String className = packageName+"."+buildClassName(item.getWriterType());
        //logger.debug("ClassName: " + className);
        Class c = Class.forName(className);
        //FileInputReader fir = new FileInputReader(readType);
        out = (OutputWriter) c.getDeclaredConstructor().newInstance();

       return out;
    }
    private static String buildClassName(String type){

        String out = type.substring(0,1);
        out = out.toUpperCase() + type.substring(1) + "OutputWriter";

        return out;

    }
}
