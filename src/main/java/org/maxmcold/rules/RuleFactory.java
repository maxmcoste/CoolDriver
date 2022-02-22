package org.maxmcold.rules;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.Controller;
import org.maxmcold.utils.CoolProperties;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

public class RuleFactory {

    final static Logger logger = LogManager.getLogger(Controller.class.getName());

    public RuleFactory(){

    }

    //get default rule, i.e.: warmRule
    public static Rule getRule(){

        String configFile = CoolProperties.ruleConfigFile;
        Yaml yaml = new Yaml();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(configFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Map<String, Object> obj = yaml.load(inputStream);
        logger.debug(obj);
        Rule r = new BoilerRule();
        logger.debug("returning rule");
        return r;
    }

    public static Map<String,Object> getRules(){

        String configFile = CoolProperties.ruleConfigFile;
        Yaml yaml = new Yaml();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(configFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return yaml.load(inputStream);
    }

    public static Rule getRule(String ruleCode) {

        if (null == ruleCode) throw new NullPointerException("Called getRule with null string");
        //set default rule as WarmRule321
        Rule r = new BoilerRule();
        if (ruleCode.equals(CoolProperties.sunCodeName)) r = new WindowsRule();

        logger.debug("returning rule");
        return r;

    }

}
