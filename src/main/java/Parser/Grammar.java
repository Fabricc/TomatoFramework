package Parser;


import java.io.*;
import java.util.*;

import Parser.support.TermOccurrence;
import freemarker.template.*;

import static Parser.support.helper.addToList;
import static Parser.support.helper.extractTerms;

/**
 * Created by UA06NP on 09/11/2016.
 */


public class Grammar {

    Map<String, List<String>> expressions = new HashMap<String, List<String>>();
    Map<String, Object> constraints = new HashMap<String, Object>();

    Grammar(){
        expressions.put("Probability", addToList("probability", "chance"));

        expressions.put("timeBound", addToList("upperTimeBound","lowerTimeBound"));
        expressions.put("upperTimeBound",addToList("within the next", "in less than"));
        expressions.put("lowerTimeBound",addToList("after","in more than"));
        expressions.put("timeUnits",addToList("time units","time steps"));
        expressions.put("timeInterval",addToList("between \\d+ and \\d+ timeUnits"));
        expressions.put("stateFormula",addToList("\\\"(\\\\w+)\\\""));
        expressions.put("p",addToList("(0\\.[0-9]+|1)"));
        expressions.put("t",addToList("([0-9]*\\.[0-9]+|[0-9]+)"));

        constraints.put("upperTimeBand",">");
        constraints.put("lowerTimeBand","<");
        constraints.put("timeInterval","><");
        constraints.put("p",Double.class);
        constraints.put("t",Double.class);
        constraints.put("stateFormula",String.class);
    }


    private String buildRegExpression(List<String> exps) {


        String result = "";

        for(String e: exps){
            result+=expand(e);
            result+=" ";
        }
        return result;

    }

//    private String expand(String non_terminal) {
//        //if()
//
//        List<String> values = expressions.get(non_terminal);
//
//        if (values == null) {
//            return non_terminal;
//        } else {
//            //String result = "(";
//            String result = "(?:";
//            boolean first_symbol = true;
//
//            for (String s : values) {
//                if (!first_symbol) result += "|";
//                else first_symbol = false;
//
//                String[] decomposition = s.split(" ");
//
//                for(int i=0; i<decomposition.length; i++){
//                    result+=expand(decomposition[i]);
//                    if(i!=decomposition.length-1){
//                        result+=" ";
//                    }
//                }
//
//
//
//            }
//            return result + ")";
//
//        }
//    }

    private String expand(String expression) {


        ArrayList<TermOccurrence> nonTerminals=extractTerms(expression);

        for(TermOccurrence word: nonTerminals ){
            List<String> derivations = expressions.get(word.getTerm());
            expand(word.getTerm());

            if (derivations == null) {
            return non_terminal;
        } else {
            //String result = "(";
            String result = "(?:";
            boolean first_symbol = true;

            for (String s : values) {
                if (!first_symbol) result += "|";
                else first_symbol = false;

                String[] decomposition = s.split(" ");

                for(int i=0; i<decomposition.length; i++){
                    result+=expand(decomposition[i]);
                    if(i!=decomposition.length-1){
                        result+=" ";
                    }
                }



            }
            return result + ")";

//        }

        }




    }


    public void generate(Map<String,List<String>> input_expressions) throws IOException, TemplateException {

        Template template = CodeGenerator.initialize("templates","step_template.java.ftlh");



        Map<String,Object> root = new HashMap();
        root.put("name", "quality_steps_generated");
        root.put("package", "Parser");
        List<StepDefinitionDataModel> list = new LinkedList<StepDefinitionDataModel>();


            Iterator it = input_expressions.entrySet().iterator();
            while (it.hasNext()) {

                Map.Entry pair = (Map.Entry)it.next();
                //System.out.println(pair.getKey() + " = " + pair.getValue());

                String exp = buildRegExpression((List<String>) pair.getValue());

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



//
//        String[] alternativeOneForms = {"the", }
//        buildRegExpression('altenativeOne',)
    }

    public static void main(String[] args) {
        // write your code here
        Grammar g = new Grammar();

        Map<String,List<String>> m = new HashMap<String, List<String>>();
        m.put("alternativeOne",addToList("the","Probability","of","stateFormula","timeBound"));
        m.put("alternativeTwo",addToList("the","Probability", "is", "that","stateFormula","timeBound"));

//        Map<String, String> m = new HashMap<String, String>();
//        m.put("alternativeOne","the Probability of stateFormula timeBound");
//        m.put("alternativeTwo","the Probability is that stateFormula timeBound");

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




