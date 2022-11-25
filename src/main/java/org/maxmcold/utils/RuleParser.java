package org.maxmcold.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.Controller;
import org.maxmcold.errors.RuleSyntaxError;
import org.maxmcold.models.Readable;
import org.maxmcold.models.Rule;
import org.maxmcold.readable.ReadableFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RuleParser {

    final static Logger logger = LogManager.getLogger(Controller.class.getName());
    private String ruleText;
    private String whenStatement;

    public RuleParser(){
        //Do Nothing
    }
    public RuleParser(String ruleTxt){
        this.ruleText = ruleTxt;
    }

    public RuleParser(Rule rule){
        this.ruleText = rule.getAction();
    }

    public String[] parse() throws FileNotFoundException,RuleSyntaxError {

        Yaml yaml = new Yaml();
        FileInputStream fis = new FileInputStream(CoolProperties.ruleConfigFile);

        Rule rule = yaml.load(fis);
        this.ruleText = rule.getAction();
        logger.debug(rule.getAction());



        String when = getWhenCondition();

        //Check syntax errors
        if (RuleSyntax.checkCondition(when) != true) throw new RuleSyntaxError();

        logger.debug("WHEN STATEMENT: " + when);
        String[] params = extractParams(when);
        for (int i=0;i<params.length;logger.debug("VALUES STATEMENT "+ i +": " + params[i++]+"\n"));
        //logger.debug("VALUES STATEMENT: " + extractParams(when));

        String[] tokens = rule.getAction().split(" ");


        return tokens;

    }

    private String getWhenCondition(){
        String[] tokens = ruleText.split("THEN");
        //TODO manager error condition
        return tokens[0].substring(4).trim();
    }

    private String[] extractParams(String match){
        Pattern pattern = Pattern.compile("\\$\\{\\S*\\}");
        Matcher matcher = pattern.matcher(match);
        String out = "";
        logger.debug("Group Count pre: "+matcher.groupCount());

        while(matcher.find()) {


            out +=  matcher.group()+";";


            // return matcher.group();

        }
        logger.debug("Group Count post: "+matcher.groupCount());
        return out.split(";");
    }


    private String[] getConditions(){
        return new String[1];
    }






}
