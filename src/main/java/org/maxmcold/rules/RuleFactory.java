package org.maxmcold.rules;

public class RuleFactory {

    public RuleFactory(){

    }
    public static Rule getRule(){
        return new WarmRule();
    }
}
