package TesterModule.src.test.java;

import java.util.LinkedList;
import java.util.List;

public class TesterModuleMessenger {
	private List<String> classes;
	private List<String> methods;
	private List<Object[]> arguments;
	private List<Class[]> arguments_classes;
	
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
		
		this.arguments_classes = new LinkedList<Class[]>();
		
		for(Object[] list: arguments){
			
			Class[] classes_for_method = new Class[list.length];
			
			for(int i=0; i<list.length; i++){
				classes_for_method[i] = list[i].getClass();
				
			}
			
			this.arguments_classes.add(classes_for_method);
		}
		
	}
	
	public void insertMethod(List<String> classes, List<String> methods, List<Object[]> arguments,
			List<Class[]> classesOfArguments) {
		this.classes=classes;
		this.methods=methods;
		this.arguments=arguments;
		
		this.arguments_classes = classesOfArguments;
		
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
	
	public List<Class[]> getClassArguments(){

		
		return this.arguments_classes;
	}


}
