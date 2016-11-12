package Parser;


import java.io.*;
import java.util.*;

import freemarker.template.*;

import static Parser.helper.addToList;

/**
 * Created by UA06NP on 09/11/2016.
 */


public class Grammar {

    Map<String, List<String>> expressions = new HashMap<String, List<String>>();
    Map<String, String> constraints = new HashMap<String, String>();

    Grammar(){
        expressions.put("Probability", addToList("probability", "chance"));
        expressions.put("timeBound", addToList("upperTimeBound","lowerTimeBound"));
        expressions.put("upperTimeBound",addToList("within the next", "in less than"));
        expressions.put("lowerTimeBound",addToList("after","in more than"));
        expressions.put("timeUnits",addToList("time units","time steps"));
        expressions.put("timeInterval",addToList("between \\d+ and \\d+ timeUnits"));
        expressions.put("stateFormula",addToList("\\\"(\\\\w+)\\\""));

        constraints.put("upperTimeBand",">");
        constraints.put("lowerTimeBand","<");
        constraints.put("timeInterval","><");
    }


    private String buildRegExpression(List<String> exps) {


        String result = "";

        for(String e: exps){
            result+=expand(e);
        }
        return result;

    }

    private String expand(String form) {
        List<String> values = expressions.get(form);

        if (values == null) {
            return form;
        } else {
            //String result = "(";
            String result = "(?:";
            boolean first_symbol = true;

            for (String s : values) {
                if (!first_symbol) result += "|";
                else first_symbol = false;

                result += expand(s);

            }
            return result + ")";

        }
    }

//    public void generate(Map<String,List<String>> expressions){
//
//        try{
//            PrintWriter writer = new PrintWriter("quality_steps_generated.java", "UTF-8");
//            writer.println("package Parser");
//            writer.println("import cucumber.api.PendingException;\n" +
//                    "import cucumber.api.java.en.Given;");
//            writer.println("public class quality_steps_generated {");
//
//
//
//            Iterator it = expressions.entrySet().iterator();
//            while (it.hasNext()) {
//                Map.Entry pair = (Map.Entry)it.next();
//                //System.out.println(pair.getKey() + " = " + pair.getValue());
//
//                String exp = buildRegExpression((List<String>) pair.getValue());
//
//                writer.println("@Given(\"^\\\\["+exp+"\\\\]$)");
//
//                writer.println("public void "+pair.getKey()+"() throws Throwable {\n" +
//                        "\n" +
//                        "        // Write code here that turns the phrase above into concrete actions\n" +
//                        "        throw new PendingException();\n" +
//                        "    }");
//
//                it.remove(); // avoids a ConcurrentModificationException
//            }
//
//            writer.println("}");
//            writer.close();
//        } catch (Exception e) {
//            // do something
//        }
//
////
////        String[] alternativeOneForms = {"the", }
////        buildRegExpression('altenativeOne',)
//    }


    public void generate(Map<String,List<String>> expressions) throws IOException, TemplateException {

        Configuration cfg = new Configuration();

        // Where do we load the templates from:
        cfg.setClassForTemplateLoading(Grammar.class, "templates");

        // Some other recommended settings:
        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        Template template = cfg.getTemplate("step_template.java.ftl");

        Map<String,Object> input = new HashMap();
        input.put("name", "quality_steps_generated");
        input.put("name", "Parser");







            Iterator it = expressions.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                //System.out.println(pair.getKey() + " = " + pair.getValue());

                String exp = buildRegExpression((List<String>) pair.getValue());
                String regex = "@Given(\"^\\\\["+exp+"\\\\]$)";

               input.put("name_function", pair.getKey());

                input.put("regex", exp);
                it.remove(); // avoids a ConcurrentModificationException
            }

        // 2.3. Generate the output

        // Write output to the console
        Writer consoleWriter = new OutputStreamWriter(System.out);
        template.process(input, consoleWriter);



//
//        String[] alternativeOneForms = {"the", }
//        buildRegExpression('altenativeOne',)
    }

    public static void main(String[] args) {
        // write your code here
        Grammar g = new Grammar();

        Map<String,List<String>> m = new HashMap<String, List<String>>();
        m.put("alternativeOne",addToList("the","Probability","of","stateFormula","timeBound"));

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




