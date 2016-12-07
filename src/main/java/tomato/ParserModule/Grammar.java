package tomato.ParserModule;


import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tomato.ParserModule.support.IllegalExpressionException;
import tomato.ParserModule.support.SemanticWrapper;
import tomato.ParserModule.support.TermOccurrence;
import tomato.ParserModule.support.helper;

/**
 * Created by UA06NP on 09/11/2016.
 */


public class Grammar {

    public Map<String, String> expressions = new HashMap<String, String>();
    public Map<String, SemanticWrapper> constraints = new HashMap<String, SemanticWrapper>();

    Grammar(){
        //Probability rules
        expressions.put("Probability", "'probability'|'chance'");
        expressions.put("probabilityBound", "upperProbailityBound|lowerProbailityBound");
        //expressions.put("aProbabilityBound", "atBound[' a']|[' a'] thanBound");
        expressions.put("upperProbailityBound", "'at most'|lowerThan");
        expressions.put("lowerProbailityBound", "'at least'|greaterThan");
//        expressions.put("aProbabilityBound", "atUpperBound[' a']|atLowerBound[' a']|[' a'] thanUpperBound|[' a'] thanLowerBound");
//        expressions.put("atUpperBound","'at most'");
//        expressions.put("atLowerBound","'at least'");
//        expressions.put("thanUpperBound","lowerThan");
//        expressions.put("thanLowerBound","greaterThan");
        
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
        expressions.put("p","\\\\\\\\d+\\\\\\\\.\\\\\\\\d+");
        expressions.put("t","\\\\\\\\d+\\\\\\\\.\\\\\\\\d+");

        constraints.put("upperTimeBound",new SemanticWrapper("<"));
        constraints.put("lowerTimeBound", new SemanticWrapper(">"));
        constraints.put("lowerProbailityBound",new SemanticWrapper(">"));
        constraints.put("upperProbailityBound",new SemanticWrapper("<"));
        constraints.put("probabilityBound",new SemanticWrapper("probability constraint"));
        constraints.put("timeBound",new SemanticWrapper("time constraint"));
        constraints.put("timeInterval",new SemanticWrapper("><"));
        constraints.put("p",new SemanticWrapper(Double.class));
        constraints.put("t",new SemanticWrapper(Double.class));
        constraints.put("stateFormula",new SemanticWrapper(String.class));
    }




    private String buildRegExpressionIntern(String expression, Boolean isInGroup , Boolean isInCapturingGroup, List<String> parameters, Map<String,Pair> lexical_parameters, String nonTerminalFather, Boolean isTerminal) {

        String result=expression;


        if(!isTerminal) {

            ArrayList<TermOccurrence> nonTerminals = null;
			try {
				nonTerminals = helper.extractTerms2(expression);
			} catch (IllegalExpressionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            for (TermOccurrence word : nonTerminals) {
                String father = nonTerminalFather;
                String term = word.getTerm();
                String derivations = expressions.get(term);
                if (!word.isTerminalString() && derivations != null) {


                    boolean capturingGroupReplacement = isInCapturingGroup;
                    boolean isATerminalForm = false;



                    SemanticWrapper meaning_of_the_term = constraints.get(term);

                    if (meaning_of_the_term != null) {

                        if (meaning_of_the_term.getDescriptor().equals("class")) {
                        	if(!parameters.contains(term)) parameters.add(term);
                            capturingGroupReplacement = true;
                            isATerminalForm = true;
                            father=term;

                        }

                        if (meaning_of_the_term.getDescriptor().equals("string")) {
                            
                            if (father == null) {
                                father = term;
                                if(!parameters.contains(father)) parameters.add(father);
                                capturingGroupReplacement = true;
                            } else {
                                lexical_parameters.put(term, new Pair(father, (String) constraints.get(father).get()));
                                father = term;
                                capturingGroupReplacement = false;
                            }
                        }
                    }

                    String replacement = buildRegExpressionIntern(derivations, true, capturingGroupReplacement, parameters, lexical_parameters, father, isATerminalForm);

                    result = helper.replaceTerm(term, replacement, result);
                } else {
                    if (nonTerminalFather != null) {
                        lexical_parameters.put(term, new Pair(nonTerminalFather, (String) constraints.get(nonTerminalFather).get()));
                    }
                    
                    String toReplace = "'"+term+"'";
                    
                    if(word.isOptional()){
                    	toReplace=Pattern.quote("["+toReplace+"]");
                    	result=result.replaceAll(toReplace, "(?:"+term+")?");
                    	}else if(word.isTerminalString()) result=result.replaceFirst(toReplace,term);
                }
            }
        }

            SemanticWrapper meaning = constraints.get(nonTerminalFather);



            if(!isInGroup) return result;
            if(isInCapturingGroup){
                if(meaning!=null && meaning.getDescriptor().equals("class") &&  meaning.get().equals(String.class)){
                    return "\\\\\"("+result+")\\\\\"";
                }
                return "("+result+")";
            }
            return "(?:"+result+")";


    }

    public String buildRegExpression(String expression, List<String> parameters, Map<String,Pair> lexical_parameters){
        return buildRegExpressionIntern(expression,false,false,parameters,lexical_parameters,null,false);
    }



}




