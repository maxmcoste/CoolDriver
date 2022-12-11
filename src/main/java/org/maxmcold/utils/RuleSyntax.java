package org.maxmcold.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Check the syntax of a given rule
 */
public  class RuleSyntax {

    final static Logger logger = LogManager.getLogger(Controller.class.getName());

    public static boolean checkCondition(String condition){

        if (!condition.substring(0,4).equals("WHEN")) return false;


        return true;
    }

    public static boolean checkAction(String action){
        return true;
    }

    private static boolean checkparams(String match){
        List<String> params = getparams(match);
        Iterator<String> iterator = params.iterator();
        while (iterator.hasNext()){

        }
        return true;
    }
    private boolean checkFields(String field){
        return true;
    }

    private static List<String> getparams(String match){

        Pattern pattern = Pattern.compile("\\$\\{\\S*\\}");
        Matcher matcher = pattern.matcher(match);
        String out = "";
        //logger.debug("Group Count pre: "+matcher.groupCount());
        ArrayList<String> params = new ArrayList<>();
        while(matcher.find())

            params.add(matcher.group());

        logger.debug("Found "+ params.size() + " Parameters in the ");
        return params;
    }
}
