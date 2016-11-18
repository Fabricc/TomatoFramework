package Parser;


import java.io.*;
import java.util.*;

import Parser.support.SemanticWrapper;
import Parser.support.TermOccurrence;
import freemarker.template.*;


import static Parser.support.helper.extractTerms;

/**
 * Created by UA06NP on 09/11/2016.
 */


public class Grammar {

    Map<String, String> expressions = new HashMap<String, String>();
    Map<String, SemanticWrapper> constraints = new HashMap<String, SemanticWrapper>();

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

        constraints.put("upperTimeBand",new SemanticWrapper(">"));
        constraints.put("lowerTimeBand",new SemanticWrapper("<"));
        constraints.put("timeBound",new SemanticWrapper("time"));
        constraints.put("timeInterval",new SemanticWrapper("><"));
        constraints.put("p",new SemanticWrapper(Double.class));
        constraints.put("t",new SemanticWrapper(Double.class));
        constraints.put("stateFormula",new SemanticWrapper(String.class));
    }




    private String buildRegExpression(String expression, Boolean isInGroup , Boolean isIncapturingGroup, Set<String> semantic) {

        ArrayList<TermOccurrence> nonTerminals=extractTerms(expression);
        String result=expression;


        for(TermOccurrence word: nonTerminals ){
            String term=word.getTerm();
            String derivations = expressions.get(term);
            if(derivations!=null) {

                boolean capturingGroupReplacement = false;
                if(constraints.get(term)!=null) {
                    semantic.add(term);
                    capturingGroupReplacement=true;
                }

                String replacement = buildRegExpression(derivations, true, capturingGroupReplacement, semantic);

                String regex_intern= "\\s"+term+"\\s";
                String regex_begin="^"+term+"\\s";
                String regex_end="\\s"+term+"$";

                result = result.replaceAll(regex_begin,replacement+" ");
                result = result.replaceAll(regex_intern," "+replacement+" ");
                result = result.replaceAll(regex_end," "+replacement);
            }
            }



            if(!isInGroup) return result;
            if(isIncapturingGroup)  return "("+result+")";
            return "(?:"+result+")";


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
                Set<String> semantic = new HashSet<String>();

                String exp = buildRegExpression((String) pair.getValue(),false,false,semantic);

                List<ParameterDataModel> params = new LinkedList<ParameterDataModel>();

                for(String s: semantic){
                    SemanticWrapper sw = constraints.get(s);
                    if(sw.getDescriptor().equals("class")){
                        params.add(new ParameterDataModel(((Class)sw.get()).getCanonicalName(),s));
                    }
                }


               //root.put("name_function", pair.getKey());
                list.add(new StepDefinitionDataModel((String) pair.getKey(),exp,params));

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

        m.put("alternativeOne","the Probability of stateFormula timeBound t");
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




