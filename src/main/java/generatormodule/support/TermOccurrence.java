package generatormodule.support;

/**
 * Created by UA06NP on 14/11/2016.
 */
public class TermOccurrence {
    String term;
    int startIndex, endIndex;
    private boolean isTerminal = false;
	private boolean isOptional = false;
	
	public TermOccurrence(String term) {
		this.term = term;
	}
	
	public TermOccurrence(String term, boolean isTerminal, boolean isOptional){
		this.term = term;
        this.isTerminal = isTerminal;
        this.isOptional = isOptional;
	}

    public TermOccurrence(String term, int startIndex, int endIndex) {

        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.term = term;

//        if(term.charAt(0)=='\'' && term.charAt(term.length()-1)=='\''){
//            isTerminal=true;
//
//            StringBuilder sb = new StringBuilder(term);
//            sb.deleteCharAt(term.length()-1);
//            sb.deleteCharAt(0);
//            this.term=sb.toString();
//        }else{
//            this.term = term;
//        }
    }

    public boolean isTerminalString(){
        return isTerminal;
    }
    
    public boolean isOptional(){
    	return isOptional;
    }

    public String getTerm() {
        return term;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }


    @Override
	public String toString() {
		return "TermOccurrence [term=" + term + ", isTerminal=" + isTerminal + ", isOptional=" + isOptional + "]";
	}

	public void defineTerminalString() {
        this.isTerminal=true;
    }
    
    public void defineOptional(){
    	this.isOptional =true;
    }
}
