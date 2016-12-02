package TesterModule.src.test.java;

import java.util.LinkedList;
import java.util.List;

public class TesterModuleMessenger {
	private List<String> classes;
	private List<String> methods;
	private List<Object[]> arguments;
	
	public void insertMethod(String klass, String method, Object[] arguments){
		if(this.classes==null) this.classes=new LinkedList<String>();
		this.classes.add(klass);
		
		if(this.methods==null) this.methods=new LinkedList<String>();
		this.methods.add(method);
		
		if(this.arguments==null) this.arguments=new LinkedList<Object[]>();
		this.arguments.add(arguments);
		
	}
	
	public void insertMethod(List<String> classes, List<String> methods, List<Object[]> arguments){
		this.classes=classes;
		this.methods=methods;
		this.arguments=arguments;
		
	}
	
	public List<String> getClasses(){
		return this.classes;
	}
	
	public List<String> getMethods(){
		return this.methods;
	}
	
	public List<Object[]> getArguments(){
		return this.arguments;
	}

}
