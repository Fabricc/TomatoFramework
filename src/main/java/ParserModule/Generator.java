package ParserModule;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.*;

import ParserModule.support.SemanticWrapper;

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
            Set<String> parameters = new HashSet<String>();

            Grammar g  = new Grammar();

            String exp = g.buildRegExpression((String) pair.getValue(),parameters,lexical_parameters);

            List<ParameterDataModel> params = new LinkedList<ParameterDataModel>();

            for(String s: parameters){
                SemanticWrapper sw = g.constraints.get(s);
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
        //Writer consoleWriter = new OutputStreamWriter();
        Writer consoleWriter = new FileWriter("quality_steps_generated.java");
        template.process(root, consoleWriter);
        consoleWriter.close();


            FileOutputStream fos = new FileOutputStream("lexicalparams.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(lexical_parameters);
            oos.close();
            fos.close();
            System.out.printf("Serialized HashMap data is saved in lexicalparams.ser");

    }


    public static void main(String[] args) {


        Map<String,String> m = new HashMap<String, String>();

        m.put("alternativeOne","'the' Probability Of stateFormula timeBound t 'is' probabilityBound p");
        m.put("alternativeTwo","'the' Probability 'is' 'that' stateFormula timeBound");
        m.put("alternativeThree","'with' Probability probabilityBound p stateFormula timeBound t");



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

