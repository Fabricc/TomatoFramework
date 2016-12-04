package ParserModule;

import java.io.Serializable;

/**
 * Created by UA06NP on 28/11/2016.
 */
public class Pair implements Serializable {
    String right_value, left_value;

    public Pair(String right_value, String left_value) {
        this.right_value = right_value;
        this.left_value = left_value;
    }

    public String getRightValue() {
        return right_value;
    }

    public String getLeftValue() {
        return left_value;
    }
}
