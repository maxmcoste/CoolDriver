package org.maxmcold.rules;

import org.maxmcold.utils.CoolProperties;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RuleFactory {

    public static Rule getRule() throws FileNotFoundException {

        Yaml yaml = new Yaml();
        FileInputStream fis = new FileInputStream(CoolProperties.ruleConfigFile);
        Rule r = yaml.load(fis);
        return r;
    }
    public static Rule getRule(String fileFullPath) throws FileNotFoundException {
        Yaml yaml = new Yaml();
        FileInputStream fis = new FileInputStream("./rules/multi.configuration.yml");
        return yaml.load(fis);
    }


}
