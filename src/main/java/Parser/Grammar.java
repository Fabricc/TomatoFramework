package Parser;


import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Parser.support.SemanticWrapper;
import Parser.support.TermOccurrence;
import freemarker.template.*;


import static Parser.support.helper.extractTerms;
import static Parser.support.helper.replaceTerm;

/**
 * Created by UA06NP on 09/11/2016.
 */


public class Grammar {

    Map<String, String> expressions = new HashMap<String, String>();
    Map<String, SemanticWrapper> constraints = new HashMap<String, SemanticWrapper>();

    Grammar(){

        expressions.put("Probability", "probability|chance");
        expressions.put("probabilityBound", "atBound|thanBound");
        expressions.put("atBound","at most|at least");
        expressions.put("thanBound","greaterThan|lowerThan");
        expressions.put("greaterThan","greater than|higher than");
        expressions.put("lowerThan","lower than|less than");
        expressions.put("Of","of|to|that|in which");
        expressions.put("timeBound", "upperTimeBound|lowerTimeBound");
        expressions.put("upperTimeBound","within the next|in less than");
        expressions.put("lowerTimeBound","after|in more than");
        expressions.put("timeUnits","time units|time steps");
        expressions.put("timeInterval","between \\d+ and \\d+ timeUnits");
        expressions.put("stateFormula","\"([^\"]*)\"");
        expressions.put("p","0\\.[0-9]+|1");
        expressions.put("t","[0-9]*\\.[0-9]+|[0-9]+");

        constraints.put("upperTimeBand",new SemanticWrapper(">"));
        constraints.put("lowerTimeBand",new SemanticWrapper("<"));
        constraints.put("probabilityBound",new SemanticWrapper("probability constraint"));
        constraints.put("timeBound",new SemanticWrapper("time constraint"));
        constraints.put("timeInterval",new SemanticWrapper("><"));
        constraints.put("p",new SemanticWrapper(Double.class));
        constraints.put("t",new SemanticWrapper(Double.class));
        constraints.put("stateFormula",new SemanticWrapper(String.class));
    }




    private String buildRegExpressionIntern(String expression, Boolean isInGroup , Boolean isInCapturingGroup, Set<String> semantic, String father) {

        ArrayList<TermOccurrence> nonTerminals=extractTerms(expression);
        String result=expression;


        for(TermOccurrence word: nonTerminals ){
            String term=word.getTerm();
            String derivations = expressions.get(term);
            if(derivations!=null) {

                boolean capturingGroupReplacement = false;
                SemanticWrapper meaning = constraints.get(term);
                if(meaning!=null) {
                    if(meaning.getDescriptor().equals("class")){
                    semantic.add(term);
                    capturingGroupReplacement=true;
                    }else{
                        if(father!=null){
                            //implementare associazione con multiple scelte
                        }
                    }
                }

                String replacement = buildRegExpressionIntern(derivations, true, capturingGroupReplacement, semantic, father);

                result=replaceTerm(term,replacement,result);
            }
            }



            if(!isInGroup) return result;
            if(isInCapturingGroup)  return "("+result+")";
            return "(?:"+result+")";


    }

    private String buildRegExpression(String expression, Set<String> semantic){
        return buildRegExpressionIntern(expression,false,false,semantic,"null");
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

                String exp = buildRegExpression((String) pair.getValue(),semantic);

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

//        String test = "how are youare";
//        String regex_intern= "\\s(are)\\s";
//
//        test = test.replaceAll(regex_intern,"is");


//        System.out.println(test.split("is"));




        Grammar g = new Grammar();

        Map<String,String> m = new HashMap<String, String>();

        m.put("alternativeOne","the Probability Of stateFormula timeBound t is probabilityBound p");
//        m.put("alternativeTwo","the Probability is that stateFormula timeBound");
//        m.put("alternativeThree","with ['a'] Probability ['of'] probabilityBound p stateFormula timeBound t");



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




