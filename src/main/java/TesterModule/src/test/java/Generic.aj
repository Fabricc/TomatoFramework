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

import cucumber.api.Scenario;

@Aspect
public class Generic {
	
	private Scenario scen;
	
	private List<String> classes = new LinkedList<String>();
	private List<String> methods= new LinkedList<String>();
	private List<Object[]> arguments= new LinkedList<Object[]>();
	
    @After("execution(* *.before(Scenario)) && args(s)")
    public void spotBefore(Scenario s){
    	
    	System.out.println("Esegui scenario "+s.getName());
    	this.scen=s;
    	}
    
    @After("execution(@cucumber.api.java.en.Given * *(..))")
    public void storeMethods(JoinPoint jp){
    	
    	if(scen.getSourceTagNames().contains("@quality")){
    		
    		classes.add(jp.getThis().getClass().getCanonicalName());
    		methods.add(jp.getSignature().getName());
    		arguments.add(jp.getArgs());
    		
    	}
    }
    
    
    @Before("call(void TesterModule.executeTests(TesterModuleMessenger)) && args(tmm)")
    public void addInformation(TesterModuleMessenger tmm){
    	
    	tmm.insertMethod(classes, methods, arguments);
    
    	System.out.println("ma prima sono qui");
    	
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




