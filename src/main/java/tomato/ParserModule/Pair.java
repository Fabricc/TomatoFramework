package tomato.ParserModule;

import java.io.Serializable;

/**
 * Created by UA06NP on 28/11/2016.
 */
public class Pair implements Serializable {
    String nonTerminal, derived;

    public Pair(String nonTerminal, String derived) {
        this.nonTerminal = nonTerminal;
        this.derived = derived;
    }

    public String getNonTerminal() {
        return nonTerminal;
    }

    public String getDerived() {
        return derived;
    }
}
