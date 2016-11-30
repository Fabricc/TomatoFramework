package ParserModule;


import java.util.*;

import ParserModule.support.SemanticWrapper;
import ParserModule.support.TermOccurrence;


import static ParserModule.support.helper.extractTerms;
import static ParserModule.support.helper.replaceTerm;

/**
 * Created by UA06NP on 09/11/2016.
 */


public class Grammar {

    public Map<String, String> expressions = new HashMap<String, String>();
    public Map<String, SemanticWrapper> constraints = new HashMap<String, SemanticWrapper>();

    Grammar(){
        //Probability rules
        expressions.put("Probability", "'probability'|'chance'");
        expressions.put("probabilityBound", "atBound|thanBound");
        expressions.put("atBound","'at most'|'at least'");
        expressions.put("thanBound","greaterThan|lowerThan");
        expressions.put("greaterThan","'greater than'|'higher than'");
        expressions.put("lowerThan","'lower than'|'less than'");
        expressions.put("Of","'of'|'to'|'that'|'in which'");
        //Time rules
        expressions.put("timeBound", "upperTimeBound|lowerTimeBound");
        expressions.put("upperTimeBound","'within the next'|'in less than'");
        expressions.put("lowerTimeBound","'after'|'in more than'");
        expressions.put("timeUnits","'time units'|'time steps'");
        expressions.put("timeInterval","'between '\\d+' and '\\d+' 'timeUnits");
        //Parameters rules
        expressions.put("stateFormula","[^\\\\\"]*");
        expressions.put("p","0\\.[0-9]+|1");
        expressions.put("t","[0-9]*\\.[0-9]+|[0-9]+");

        constraints.put("upperTimeBound",new SemanticWrapper("<"));
        constraints.put("lowerTimeBound",new SemanticWrapper(">"));
        constraints.put("atBound",new SemanticWrapper("<"));
        constraints.put("thanBound",new SemanticWrapper(">"));
        constraints.put("probabilityBound",new SemanticWrapper("probability constraint"));
        constraints.put("timeBound",new SemanticWrapper("time constraint"));
        constraints.put("timeInterval",new SemanticWrapper("><"));
        constraints.put("p",new SemanticWrapper(Double.class));
        constraints.put("t",new SemanticWrapper(Double.class));
        constraints.put("stateFormula",new SemanticWrapper(String.class));
    }




    private String buildRegExpressionIntern(String expression, Boolean isInGroup , Boolean isInCapturingGroup, Set<String> parameters, Map<String,Pair> lexical_parameters, String right_hand_value, Boolean isAClass) {

        String result=expression;


        if(!isAClass) {

            ArrayList<TermOccurrence> nonTerminals = extractTerms(expression);

            for (TermOccurrence word : nonTerminals) {
                String father = right_hand_value;
                String term = word.getTerm();
                String derivations = expressions.get(term);
                if (!word.isTerminalString() && derivations != null) {


                    boolean capturingGroupReplacement = isInCapturingGroup;
                    boolean isATerminalClass = false;



                    SemanticWrapper meaning = constraints.get(term);

                    if (meaning != null) {

                        if (meaning.getDescriptor().equals("class")) {
                            parameters.add(term);
                            capturingGroupReplacement = true;
                            isATerminalClass = true;
                            father=term;

                        }

                        if (meaning.getDescriptor().equals("string")) {
                            capturingGroupReplacement = true;
                            if (father == null) {
                                father = term;
                            } else {
                                lexical_parameters.put(term, new Pair(father, (String) constraints.get(father).get()));
                                father = term;
                            }
                        }
                    }

                    String replacement = buildRegExpressionIntern(derivations, true, capturingGroupReplacement, parameters, lexical_parameters, father, isATerminalClass);

                    result = replaceTerm(term, replacement, result);
                } else {
                    if (right_hand_value != null) {
                        lexical_parameters.put(term, new Pair(right_hand_value, (String) constraints.get(right_hand_value).get()));
                    }
                    result=result.replaceFirst("'"+term+"'",term);
                }
                //right_hand_value=null;
            }
        }

            SemanticWrapper meaning = constraints.get(right_hand_value);



            if(!isInGroup) return result;
            if(isInCapturingGroup){
                if(meaning!=null && meaning.getDescriptor().equals("class") &&  meaning.get().equals(String.class)){
                    return "\\\\\"("+result+")\\\\\"";
                }
                return "("+result+")";
            }
            return "(?:"+result+")";


    }

    public String buildRegExpression(String expression, Set<String> parameters, Map<String,Pair> lexical_parameters){
        return buildRegExpressionIntern(expression,false,false,parameters,lexical_parameters,null,false);
    }



}




