package tomato.TesterModule.main;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import tomato.ParserModule.Pair;
import tomato.support.LexicalParametersSerializer;

public class TesterModuleMessenger {
	private List<String> classes;
	private List<String> methods;
	private List<Object[]> arguments;
	private List<Class[]> arguments_classes;
	private List<String[]> nameOfArguments;
	private Map<String, Pair> semantic;
	private Map<String, Object> parameters;
	
	
	protected TesterModuleMessenger(){
		this.semantic = LexicalParametersSerializer.deserialize();
	}
	
//	public void insertParameter(String name, Object par){
//		if(this.parameters == null){
//			this.parameters = new HashMap<String, Object>();
//		}
//		
//		this.parameters.put(name, par);
//	}
	
//	public void insertLexicalParameter(String lexical_name, String lexical_value){
//		
//			Pair pair = this.semantic.get(lexical_value);
//			
//			insertParameter(lexical_name,pair.getDerived());
//		
//		
//	}
//	
	public void insertParameter(String name, Object value){
		
		if(this.parameters == null){
		this.parameters = new HashMap<String, Object>();
	}
		try{
			Pair pair = this.semantic.get((String)value);
			if(pair!=null) this.parameters.put(name, pair.getDerived());
			else this.parameters.put(name, value);
		}catch(ClassCastException e){
			this.parameters.put(name, value);
		}
		
		
	}
	
	public Object getParameter(String par){
		return this.parameters.get(par);
	}
	
//	public void insertMethod(String klass, String method, Object[] arguments){
//		if(this.classes==null) this.classes=new LinkedList<String>();
//		this.classes.add(klass);
//		
//		if(this.methods==null) this.methods=new LinkedList<String>();
//		this.methods.add(method);
//		
//		if(this.arguments==null) this.arguments=new LinkedList<Object[]>();
//		this.arguments.add(arguments);
//		
//	}
	
//	public void insertMethod(List<String> classes, List<String> methods, List<Object[]> arguments){
//		this.classes=classes;
//		this.methods=methods;
//		this.arguments=arguments;
//		
//		this.arguments_classes = new LinkedList<Class[]>();
//		
//		for(Object[] list: arguments){
//			
//			Class[] classes_for_method = new Class[list.length];
//			
//			for(int i=0; i<list.length; i++){
//				classes_for_method[i] = list[i].getClass();
//				
//			}
//			
//			this.arguments_classes.add(classes_for_method);
//		}
//		
//	}
	
	public void insertMethod(List<String> classes, List<String> methods, List<Object[]> arguments,
			List<Class[]> classesOfArguments, List<String[]> names) {
		this.classes=classes;
		this.methods=methods;
		this.arguments=arguments;
		this.arguments_classes = classesOfArguments;
		this.nameOfArguments= names;
		
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
	
	public List<String[]> getNameOArguments(){
		return this.nameOfArguments;
	}

}
