package TesterModule.src.test.java;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;

import cucumber.api.Scenario;

@Aspect
public class TesterModuleAspect {
	
	private Scenario scen;
	
	private List<String> classes = new LinkedList<String>();
	private List<String> methods= new LinkedList<String>();
	private List<Object[]> arguments= new LinkedList<Object[]>();
	private List<Class[]> classesOfArguments = new LinkedList<Class[]>();
	
	private Boolean storing = false;
	
    @After("execution(* *.before(Scenario)) && args(s)")
    public void spotBefore(Scenario s){
    	
    	System.out.println("Esegui scenario "+s.getName());
    	this.storing = true;
    	this.scen=s;
    	}
    
    @After("call(* *.getMethod(String, ..))")
    public void disableStoreMethods(){
    	this.storing = false;
    }
    
    @After("execution(@cucumber.api.java.en.Given * *(..)) && !@annotation(TesterModule.src.test.java.Tomato)")
    public void storeMethods(JoinPoint jp){
    	if(this.storing){

    	
    	if(scen.getSourceTagNames().contains("@quality")){
    		
    		this.classes.add(jp.getThis().getClass().getCanonicalName());
    		this.methods.add(jp.getSignature().getName());
    		this.arguments.add(jp.getArgs());
    		CodeSignature cs = (CodeSignature)jp.getSignature();
    		this.classesOfArguments.add(cs.getParameterTypes());

    	}
    	}
    }
    
    
    @Before("call(void TesterModule.executeTests(TesterModuleMessenger)) && args(tmm)")
    public void addInformation(TesterModuleMessenger tmm){
    	
    	tmm.insertMethod(classes, methods, arguments, classesOfArguments);
    
    	System.out.println("adding stored method");
    	
    }
    
    
//    @After("execution(@cucumber.api.java.en.Given * *(..))")
//    public void spotAnnotations(JoinPoint jp) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, ClassNotFoundException{
//    	
//    	Boolean found = false;
//    	
//    	for (String str : scen.getSourceTagNames()){
//    		if(str.equals("@quality")) {
//    			
//    			found=true;
//    			
//    			
//    			}
//    		}
//    	
//    	if(found){
//    	System.out.println("and I test the quality");
//		String klass = jp.getThis().getClass().getCanonicalName();
//		String met = jp.getSignature().getName();
//		Signature sig = jp.getSignature();
//		Object[] parameters = jp.getArgs();
//		Class[] class_parameters = new Class[parameters.length];
//		System.out.println("and I am class: "+klass);
//		System.out.println("and method: "+ met);
//		System.out.println("and the arguments are:");
//		for(int i=0; i<parameters.length; i++){
//			System.out.println(parameters[i].getClass().getCanonicalName());
//			class_parameters[i] = parameters[i].getClass();
//		}
//		
//	
//		
//		Class c = Class.forName(klass);
//		
//		Method[] allMethods = c.getDeclaredMethods();
//		
//		for(Method m : allMethods){
//			System.out.println(m.getName());
//			System.out.println(m.getParameterCount());
//			
//		}
//		
//		try {
//		
//		Method method = c.getMethod(met, String.class,int.class, int.class);
//		
//		Object o = c.newInstance();
//		
//		System.out.println("what?");
//		
//		
//			method.invoke(o, parameters);
//		} catch (InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			System.err.println(e.getCause());
//		}}	
//    
    
    
  //  }
    
    
    
    
    
    	
    }




