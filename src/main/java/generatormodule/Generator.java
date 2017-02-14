package generatormodule;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import generatormodule.support.SemanticWrapper;

/**
 * Created by UA06NP on 28/11/2016.
 */
public class Generator {

    public static void generate(Map<String,String> input_expressions) throws IOException, TemplateException {

        Template template = CodeGeneratorConfigurator.initialize("templates","step_template.java.ftlh");




        Map<String,Object> root = new HashMap();
        root.put("name", "quality_steps_generated");
        root.put("package", "ParserModule");

        List<StepDefinitionDataModel> list = new LinkedList<StepDefinitionDataModel>();




        Iterator it = input_expressions.entrySet().iterator();
        Map<String, Pair> lexical_parameters= new HashMap<String, Pair>();
        while (it.hasNext()) {

            Map.Entry pair = (Map.Entry)it.next();
            //System.out.println(pair.getKey() + " = " + pair.getValue());
            List<String> parameters = new LinkedList<String>();

            Grammar grammar  = new Grammar();

            String exp = grammar.buildRegExpression((String) pair.getValue(),parameters,lexical_parameters);

            List<ParameterDataModel> params = new LinkedList<ParameterDataModel>();

            for(String name: parameters){
                SemanticWrapper sw = grammar.semantic.get(name);
                if(sw.getDescriptor().equals("class")){
                	String type=((Class)sw.get()).getCanonicalName();
                    params.add(new ParameterDataModel(type,name));
                }else params.add(new ParameterDataModel("java.lang.String",name));
            }


            //root.put("name_function", pair.getKey());
            String name_expression = (String) pair.getKey();
            list.add(new StepDefinitionDataModel(name_expression,exp,params));

            //root.put("regex", exp);
            it.remove(); // avoids a ConcurrentModificationException
        }

        root.put("steps",list);

        // 2.3. Generate the output

        // Write output to the console
        //Writer consoleWriter = new OutputStreamWriter();
        Writer consoleWriter = new FileWriter("quality_steps_generated.java");
        template.process(root, consoleWriter);
        consoleWriter.close();


            FileOutputStream fos = new FileOutputStream("lexicalparams.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(lexical_parameters);
            oos.close();
            fos.close();
            System.out.printf("Your grammar was elaborated without errors. Step definitions generated.");

    }


    public static void main(String[] args) {


        Map<String,String> m = new HashMap<String, String>();

        m.put("alternativeOne","'the' Probability Of stateFormula timeBound t 's is' probabilityBound p");
        m.put("alternativeTwo","'the' Probability 'is' probabilityBound p 'that' stateFormula timeBound t 's'");
        m.put("alternativeThree","'with'[' a'] Probability[' of'] probabilityBound p stateFormula timeBound t 's'");
        m.put("alternativeNine","stateFormula timeBound t 's with'[' a'] Probability[' of'] probabilityBound p");




        try {
            generate(m);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }


        return;
    }

}

