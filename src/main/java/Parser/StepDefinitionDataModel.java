package Parser;

/**
 * Created by ricch on 13/11/2016.
 */
public class StepDefinitionDataModel {
    private String name_function, regex;

    public StepDefinitionDataModel(String name_function, String regex) {
        this.name_function = name_function;
        this.regex = regex;
    }

    public String getName_function() {
        return name_function;
    }

    public String getRegex() {
        return regex;
    }
}
