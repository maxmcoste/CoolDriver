package org.maxmcold.rules;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.Controller;
import org.maxmcold.utils.CoolProperties;

public class RuleFactory {

    final static Logger logger = LogManager.getLogger(Controller.class.getName());

    public RuleFactory(){

    }

    //get default rule, i.e.: warmRule
    public static Rule getRule(){

        Rule r = new BoilerRule();
        logger.debug("returning rule");
        return r;
    }
    public static Rule getRule(String ruleCode) {

        if (null == ruleCode) throw new NullPointerException("Called getRule with null string");
        //set default rule as WarmRule
        Rule r = new BoilerRule();
        if (ruleCode.equals(CoolProperties.sunCodeName)) r = new WindowsRule();


        logger.debug("returning rule");
        return r;

    }
}
