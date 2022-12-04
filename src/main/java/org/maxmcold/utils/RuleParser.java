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


    public void parse(Rule rule) throws FileNotFoundException,RuleSyntaxError {

        Yaml yaml = new Yaml();
        FileInputStream fis = new FileInputStream(CoolProperties.ruleConfigFile);

        rule = yaml.load(fis);
        this.ruleText = rule.getAction();
        //logger.debug(rule.getAction());
        setConditionStatements();
        //Check syntax errors
        if (RuleSyntax.checkCondition(rule.getAction()) != true) throw new RuleSyntaxError();




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
