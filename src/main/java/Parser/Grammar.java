package Parser;


import java.io.*;
import java.util.*;

import Parser.support.TermOccurrence;
import freemarker.template.*;


import static Parser.support.helper.extractTerms;

/**
 * Created by UA06NP on 09/11/2016.
 */


public class Grammar {

    Map<String, String> expressions = new HashMap<String, String>();
    Map<String, Object> constraints = new HashMap<String, Object>();

    Grammar(){

        expressions.put("Probability", "probability|chance");
        expressions.put("timeBound", "upperTimeBound|lowerTimeBound");
        expressions.put("upperTimeBound","within the next|in less than");
        expressions.put("lowerTimeBound","after|in more than");
        expressions.put("timeUnits","time units|time steps");
        expressions.put("timeInterval","between \\d+ and \\d+ timeUnits");
        expressions.put("stateFormula","\\\"(\\\\w+)\\\"");
        expressions.put("p","(0\\.[0-9]+|1)");
        expressions.put("t","([0-9]*\\.[0-9]+|[0-9]+)");

        constraints.put("upperTimeBand",">");
        constraints.put("lowerTimeBand","<");
        constraints.put("timeInterval","><");
        constraints.put("p",Double.class);
        constraints.put("t",Double.class);
        constraints.put("stateFormula",String.class);
    }




    private String buildRegExpression(String expression, Boolean grouping , Boolean capturingGroup) {


        ArrayList<TermOccurrence> nonTerminals=extractTerms(expression);
        String result=expression;


        for(TermOccurrence word: nonTerminals ){
            String derivations = expressions.get(word.getTerm());
            if(derivations!=null) result = result.replace(word.getTerm(),buildRegExpression(derivations,true, true));

            }

            if(!grouping) return result;
            if(capturingGroup)  return "(?:"+result+")";
            return "("+result+")";


    }


    public void generate(Map<String,String> input_expressions) throws IOException, TemplateException {

        Template template = CodeGenerator.initialize("templates","step_template.java.ftlh");



        Map<String,Object> root = new HashMap();
        root.put("name", "quality_steps_generated");
        root.put("package", "Parser");
        List<StepDefinitionDataModel> list = new LinkedList<StepDefinitionDataModel>();


            Iterator it = input_expressions.entrySet().iterator();
            while (it.hasNext()) {

                Map.Entry pair = (Map.Entry)it.next();
                //System.out.println(pair.getKey() + " = " + pair.getValue());

                String exp = buildRegExpression((String) pair.getValue(),false,false);

               //root.put("name_function", pair.getKey());
                list.add(new StepDefinitionDataModel((String) pair.getKey(),exp));

                //root.put("regex", exp);
                it.remove(); // avoids a ConcurrentModificationException
            }

            root.put("steps",list);

        // 2.3. Generate the output

        // Write output to the console
        Writer consoleWriter = new OutputStreamWriter(System.out);
        template.process(root, consoleWriter);
        consoleWriter.close();

    }

    public static void main(String[] args) {
        // write your code here
        Grammar g = new Grammar();

        Map<String,String> m = new HashMap<String, String>();
        m.put("alternativeOne","the Probability of stateFormula timeBound");
        m.put("alternativeTwo","the Probability is that stateFormula timeBound");


        try {
            g.generate(m);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }


        return;
    }



}




