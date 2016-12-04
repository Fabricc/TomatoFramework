package tomato.ParserModule.support;

/**
 * Created by UA06NP on 18/11/2016.
 */
public class SemanticWrapper {

    private Class klass;
    private String string;
    private String descriptor;

    public SemanticWrapper(Class klass){
        this.klass=klass;
        this.descriptor="class";
    }

    public SemanticWrapper(String string){
        this.string=string;
        this.descriptor="string";
    }

    public Object get(){
        if(descriptor.equals("class")) return klass;
        else return string;
    }

    public String getDescriptor() {
        return descriptor;
    }
}
