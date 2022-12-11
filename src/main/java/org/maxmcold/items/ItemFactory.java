package org.maxmcold.items;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.Controller;


import java.lang.reflect.InvocationTargetException;

public class ItemFactory {

    final static Logger logger = LogManager.getLogger(Controller.class.getName());


    public static Item getItem(String itemType,String itemName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Item out = null;
        String packageName = ItemFactory.class.getPackageName();
        String className = packageName+"."+buildClassName(itemType);
        Class c = Class.forName(className);
        out = (Item) c.getDeclaredConstructor().newInstance();
        //define a default name for item object
        //TODO: put this in a configuration
        if (null == itemName || itemName.equals("")) itemName = "main";
        out.setID(itemName);
        if (out == null) throw new InstantiationException("No item instantiated for name "+itemName+ " and type "+itemType);
        logger.debug(ItemFactory.class.getName()+" returning item "+out);
        return out;
    }
    private static String buildClassName(String type){
        String out = type.substring(0,1).toUpperCase() + type.substring(1);
        return out;

    }

}
