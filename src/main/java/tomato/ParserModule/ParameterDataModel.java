package tomato.ParserModule;

/**
 * Created by UA06NP on 18/11/2016.
 */
public class ParameterDataModel {
    private String type, name;


    public ParameterDataModel(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
