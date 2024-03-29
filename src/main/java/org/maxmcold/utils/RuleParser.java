package org.maxmcold.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.Controller;
import org.maxmcold.errors.RuleSyntaxError;
import org.maxmcold.readable.Readable;
import org.maxmcold.readable.ReadableFactory;
import org.maxmcold.rules.Rule;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RuleParser {

    final static Logger logger = LogManager.getLogger(Controller.class.getName());
    private String ruleText;
    private String whenStatement;
    private String thenStatement;

    public RuleParser(){
        //Do Nothing
    }
    public RuleParser(String ruleTxt){
        this.ruleText = ruleTxt;
    }


    public void parse(String rule) throws FileNotFoundException,RuleSyntaxError {

        this.ruleText = rule;
        //logger.debug(rule.getAction());
        setConditionStatements();
        //Check syntax errors
        if (RuleSyntax.checkCondition(rule) != true) throw new RuleSyntaxError();

    }
    /**
     * Expects an input like action(param) and returns action name
     * Example: "setStatus(ON)" returns string "setStatus"
     * @param ruleAction
     * @return status name of a rule action
     */
    public static String getActionName(String ruleAction){
        Pattern pattern = Pattern.compile("[A-Za-z]+\\(");
        Matcher matcher = pattern.matcher(ruleAction);
        String out = "";
        //logger.debug("Group Count pre: "+matcher.groupCount());
        while(matcher.find())
            out +=  matcher.group()+";";
        //remove last digit that is a "("
        out = out.split(";")[0].trim().substring(0,out.length()-2);
        return out;
    }

    public static String getActionParam(String ruleAction){
        Pattern pattern = Pattern.compile("\\(.*\\)");
        Matcher matcher = pattern.matcher(ruleAction);
        String out = "";
        //logger.debug("Group Count pre: "+matcher.groupCount());
        while(matcher.find())
            out +=  matcher.group()+";";
        //take first parameter

        //TODO: better error management
        out = out.split(";")[0].trim();
        //logger.debug("Group Count post: "+matcher.groupCount());

        //Stripe parentheses
        out = out.substring(1,out.length()-1);
        return out;
    }
    public static String untagParam(String param){

        if (param.length() == 0) return "";
        if (param.startsWith("${")) param = param.substring(2);
        if (param.endsWith("}")) param = param.substring(0,param.length()-1);
        return param;


    }
    private void setConditionStatements() {

        String[] tokens = ruleText.split("THEN");
        //TODO manager error condition

        this.whenStatement = tokens[0].substring(4).trim();
        this.thenStatement = tokens[1].trim();
    }









    public String getWhenStatement() {
        return whenStatement;
    }

    public String getThenStatement() {
        return thenStatement;
    }

    public String getRuleText() {
        return ruleText;
    }
}
