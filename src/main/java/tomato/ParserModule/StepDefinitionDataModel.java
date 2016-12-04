package ParserModule;

import java.util.List;

/**
 * Created by ricch on 13/11/2016.
 */
public class StepDefinitionDataModel {
    private String name_function, regex;
    private List<ParameterDataModel> params;

    public StepDefinitionDataModel(String name_function, String regex, List<ParameterDataModel> params) {
        this.name_function = name_function;
        this.regex = regex;
        this.params = params;
    }

    public String getName_function() {
        return name_function;
    }

    public String getRegex() {
        return regex;
    }

    public List<ParameterDataModel> getParams() {
        return params;
    }
}
