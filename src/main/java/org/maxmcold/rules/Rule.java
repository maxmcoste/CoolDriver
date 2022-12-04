package org.maxmcold.rules;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.Controller;
import org.maxmcold.errors.RuleSyntaxError;
import org.maxmcold.io.OutputWriter;
import org.maxmcold.items.Item;
import org.maxmcold.items.ItemFactory;
import org.maxmcold.readable.Readable;
import org.maxmcold.readable.ReadableFactory;
import org.maxmcold.utils.CoolProperties;
import org.maxmcold.utils.RuleParser;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Class.forName;

public class Rule {




    final static Logger logger = LogManager.getLogger(Controller.class.getName());
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String description;
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAreas() {
        return areas;
    }

    public void setAreas(String areas) {
        this.areas = areas;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }


    private String name;
    private String areas;
    private String action;

    public void execute() throws FileNotFoundException, RuleSyntaxError, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        RuleParser rp = new RuleParser();
        rp.parse(this);

        String when = rp.getWhenStatement();
        String then = rp.getThenStatement();
        if (null == when) throw new RuleSyntaxError("When condition is null");


        if (runConditions(when)){

            //expected only one param in then statement
            String param = extractParams(then)[0];
            String untaggedParam = RuleParser.untagParam(param);
            String itemName = untaggedParam.split("->")[0];
            String actionName = untaggedParam.split("->")[1];
            Item item = getItem(itemName);
            String parameter = RuleParser.getActionParam(actionName);
            String methodName = RuleParser.getActionName(actionName);

            //TODO: define the position
            Method method = Item.class.getMethod(methodName, Item.Status.class);
            if (parameter.equals("ON"))
                method.invoke(item, Item.Status.ON);
            else if (parameter.equals("OFF"))
                method.invoke(item, Item.Status.OFF);



        }




    }


    private boolean runConditions(String when){

        when = when.trim();

        int firstAndOperatorIndex = when.indexOf("AND");
        int firstOrOperatorIndex = when.indexOf("OR");
        //if is not empty and there is no OR or AND then is the last condition
        if (when.length() > 1 && firstAndOperatorIndex == -1 && firstOrOperatorIndex == -1){
            return evalStatement(when);
        }
        String statement = "";
        String operator = "";
        boolean out = false;
        if (firstAndOperatorIndex != -1){

            //evaluate first statement
            statement = when.substring(0,firstAndOperatorIndex).trim();
            String[] subStatement = statement.split("OR");
            if (subStatement.length > 1) {
                for (int k = 0; k< subStatement.length;k++) {
                    logger.debug("STATEMENT TO BE EVALUATED: " + subStatement[k]);
                    out = out || evalStatement(subStatement[k]);
                }
            } else {
                out = evalStatement(statement);
            }
            if (!out) return false;

            // remove "AND" at the beginning of the statement
            when = when.substring(firstAndOperatorIndex+3);
            //if statement is true run next condition otherwise return false
            if (out && when.length() > 0) out = out&&runConditions(when);
            else return false;

        }

        return out;
    }

    private String[] extractParams(String match){

        Pattern pattern = Pattern.compile("\\$\\{\\S*\\}");
        Matcher matcher = pattern.matcher(match);
        String out = "";
        //logger.debug("Group Count pre: "+matcher.groupCount());

        while(matcher.find())
            out +=  matcher.group()+";";

        //logger.debug("Group Count post: "+matcher.groupCount());
        return out.split(";");
    }

    private Item getItem(String param) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Item item = null;
        //TODO made simple now for testing reasons. To be abstracted in future
        String[] tokens = param.split("\\.");
        if (tokens[0].equals("item")){
            item = ItemFactory.getItem(tokens[1].trim());
        }
        return item;

    }
    private String extractOperator(String condition){
        String out = "";
        Pattern pattern = Pattern.compile("[<>=]");
        Matcher matcher = pattern.matcher(condition);
        if (matcher.find()){
            out = matcher.group();
        }
        //String out = matcher.group();

        //logger.debug("FOUND THIS OPERATOR: "+out);
        return out;

    }



    private boolean evalStatement(String statement){

        statement = statement.trim();
        String operator = extractOperator(statement).trim();


        String[] params = statement.split(operator);
        //if is a parameter
        Long param1,param2;
        if (params[0].trim().substring(0,1).equals("$")) {

            Readable r = parseReadable(params[0]);
            param1 = r.getValue();

        } else {
            param1 = Long.parseLong(params[0]);
        }
        if (params[1].trim().substring(0,1).equals("$")) {

            Readable r = parseReadable(params[1]);
            param2 = r.getValue();

        } else {
            param2 = Long.parseLong(params[1].trim());
        }
        logger.debug("EVALUATING IF "+param1+" "+operator+" "+param2);
        return evalLongCondition(param1,param2,operator);
    }

    private boolean evalLongCondition(Long param1,Long param2,String operator){

        if (operator.equals(">")) return param1 > param2;
        else if (operator.equals("<")) return param1 < param2;
        else if (operator.equals("=")) return param1 == param2;
        else return false;
    }

    private Readable parseReadable(String ruleParam) {


        ruleParam = ruleParam.trim();
        //Clean up variable tags if present

        if (ruleParam.substring(0,2).equals("${")) {
            ruleParam = ruleParam.substring(2);
        }
        String paramLeftCut = ruleParam.substring(0,ruleParam.length()-1);
        if (paramLeftCut.equals("}")){
            ruleParam = paramLeftCut;
        }
        Readable out = null;

        String[] tokens = ruleParam.trim().split("\\.");

        if (tokens[0].equals("readable")) {
            String readableType = tokens[1];

            if (tokens[1].equals(CoolProperties.temperatureFieldName))
                out = ReadableFactory.getReadable(ReadableFactory.Type.TEMPERATURE);
            else if (tokens[1].equals(CoolProperties.humidityFieldName))
                out = ReadableFactory.getReadable(ReadableFactory.Type.HUMIDITY);
            else if (tokens[1].equals(CoolProperties.awningsFieldName))
                out = ReadableFactory.getReadable(ReadableFactory.Type.AWNINGINPUT);
            else if (tokens[1].equals(CoolProperties.sunFieldName))
                out = ReadableFactory.getReadable(ReadableFactory.Type.SUNPOSITION);
            else if (tokens[1].equals(CoolProperties.windowstatusFieldName))
                out = ReadableFactory.getReadable(ReadableFactory.Type.WINDOWPOSITION);
        }

        return out;
    }


}
