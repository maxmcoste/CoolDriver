package org.maxmcold.rules;

public class RuleFactory {

    public RuleFactory(){

    }
    public Rule getRule(){
        return new WarmRule();
    }
}
