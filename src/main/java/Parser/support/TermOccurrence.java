package Parser.support;

/**
 * Created by UA06NP on 14/11/2016.
 */
public class TermOccurrence {
    String term;
    int startIndex, endIndex;

    public TermOccurrence(String term, int startIndex, int endIndex) {
        this.term = term;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
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
        return "TermOccurrence{" +
                "term='" + term + '\'' +
                ", startIndex=" + startIndex +
                ", endIndex=" + endIndex +
                '}';
    }
}
