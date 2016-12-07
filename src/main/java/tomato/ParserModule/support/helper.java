package tomato.ParserModule.support;

import java.io.File;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by UA06NP on 09/11/2016.
 */
public class helper {
    public static <E> List<E> addToList(E... values) {
        LinkedList<E> list = new LinkedList<E>();
        for (E value : values) {
            list.addLast(value);
        }

        return list;
    }
    

private static TermOccurrence addTermOnTheResult(String term, List<TermOccurrence> list, boolean isTerminal, boolean isOptional){
	TermOccurrence t = new TermOccurrence(term,isTerminal,isOptional);
	list.add(t);
	return t;
}
    
  
public static ArrayList<TermOccurrence> extractTerms2(String expression) throws IllegalExpressionException{
	Stack<Character> stack = new Stack<Character>();
	 ArrayList<TermOccurrence> res = new ArrayList<TermOccurrence>();
	 boolean squareBracketMode = false;
	 boolean appendixMode = false;
	 boolean emptyStackMode = false;
	 String term="";

	    for (int i = 0;i < expression.length(); i++){
	    	char current_character = expression.charAt(i);
	    	char previous_special_character = 0;
	    	
	    	try{
	    		
	    	previous_special_character = stack.peek();
	    		
	    	}catch(EmptyStackException e){
	    		emptyStackMode=true;
	    	}
	    	
	    	if(current_character=='|'){
	    		if(!term.equals("")){
	    		Boolean isOptional = false;
	    		if(squareBracketMode) isOptional = true;
	    		addTermOnTheResult(term, res, false, true);
	    		term="";
	    		}
	    		continue;
	    	}
	    	
	    	if(current_character==' '){
	    		if(!appendixMode){
	    			if(!term.equals("")){
	    
	    		Boolean isOptional = false;
	    		if(squareBracketMode) isOptional = true;
	    		addTermOnTheResult(term, res, false, true);
	    		term="";
	    		}
	    		}else{
	    			term+=current_character;
	    		}
	    		continue;
	    	}
	    	
	    	if(current_character=='\''){
	    		if(!emptyStackMode && previous_special_character=='\''){
	    			stack.pop();
	    			appendixMode = false;
		    		Boolean isOptional = false;
		    		if(squareBracketMode) isOptional = true;
		    		addTermOnTheResult(term, res, true, isOptional);
		    		term="";
		    		
	    		}else{
	    			stack.push(current_character);
	    			appendixMode = true;
	    			emptyStackMode=false;
	    		}
	    		continue;
	    	}
	    	
	    	if(current_character=='[') {
	    		if(!term.equals("")) {
	    			addTermOnTheResult(term, res, false, false);
	    			term="";
	    		}
	    		squareBracketMode = true;
	    		stack.push(current_character);
	    		continue;
	    	}
	    	
	    	if(!emptyStackMode && previous_special_character=='[' && current_character==']') {
	    		stack.pop();
	    		if(!term.equals("")) {
	    		squareBracketMode = false;
	    		addTermOnTheResult(term, res, true, true);
	    		term="";
	    		}
	    		continue;
	    	}
	    	
	    	if(i==expression.length()-1) {
	    		addTermOnTheResult(term+current_character, res, false, false);
	    		continue;
	    	}
	    	
	    	term+=current_character;
	    }
	    
	    if(!stack.isEmpty()) throw new IllegalExpressionException();

	return res;
}

public static ArrayList<TermOccurrence> extractTerms(String expression){
    String term="";
    int first_char=0;
    boolean first_appendix=false;
    boolean square_brackets=false;
    
    ArrayList<TermOccurrence> res = new ArrayList<TermOccurrence>();

    for (int i = 0;i < expression.length(); i++){
        char current_character = expression.charAt(i);

        if(current_character==' '||current_character=='|'||current_character=='('||current_character==')'
        		||current_character=='\''||current_character=='['||current_character==']'){

            if(current_character=='\'' && first_appendix==false){
                first_appendix=true;
            }
            
            
            if(current_character=='[') square_brackets=true;

            if(current_character==' ' && first_appendix==true) {
                term+=current_character;
                continue;
            }

            if(!term.equals("")) {
                TermOccurrence t = new TermOccurrence(term, first_char, i);
                if(first_appendix){
                    t.defineTerminalString();
                    first_appendix=false;
                }
                
                if(square_brackets){
                	t.defineOptional();
                	square_brackets=false;
                }

                res.add(t);

                term="";
            }
            
            //consume special character

        }else{
            if(term.equals("")) first_char=i;
            term+=current_character;
            if(i==expression.length()-1) res.add(new TermOccurrence(term,first_char,i));
        }
    }
    return res;
}

public static String replaceTerm(String term, String replacement, String context){

    String result = context;

    final List<Pattern> rxs = new ArrayList<Pattern>();

//    rxs.add(Pattern.compile("(\\s)"+term+"(\\s)"));
//    rxs.add(Pattern.compile("(^)"+term+"(\\s)"));
//    rxs.add(Pattern.compile("(\\s)"+term+"($)"));
//    rxs.add(Pattern.compile("(\\|)"+term+"(\\|)"));
//    rxs.add(Pattern.compile("(\\()"+term+"(\\|)"));
//    rxs.add(Pattern.compile("(^)"+term+"(\\))"));
//    rxs.add(Pattern.compile("(^)"+term+"(\\|)"));
//    rxs.add(Pattern.compile("(\\|)"+term+"($)"));
//    rxs.add(Pattern.compile("(\\()"+term+"($)"));

    rxs.add(Pattern.compile("(\\b)"+term+"(\\b)"));


    for (Pattern rx : rxs) {
        Matcher m = rx.matcher(result);
        if (m.find()){
            result= m.replaceAll("$1"+replacement+"$2");
        }
    }

    return result;
}


    public static void main(String[] args) {
//        String str = "(atBound | thanBound hji ||( jo))";
//        System.out.println(extractTerms(str));

        StringBuffer buf = new StringBuffer("Java this is a test");

        int start = 3;
        int end = 4;
        buf.replace(start, end, "4"); // Java Developers v1.4
        System.out.println(buf);



    }

}
