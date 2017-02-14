package testermodule;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;

import cucumber.api.Scenario;

@Aspect
public class TesterModuleAspect {
	
	private Scenario scen;
	
	private List<String> classes = new LinkedList<String>();
	private boolean ignoreNextClass = false;
	private List<String> methods= new LinkedList<String>();
	private List<Object[]> arguments= new LinkedList<Object[]>();
	private List<Class[]> classesOfArguments = new LinkedList<Class[]>();
	private List<String[]> nameOfArguments = new LinkedList<String[]>();
	
	private Boolean storing = false;
	
    @After("execution(* *.initializationStep(Scenario)) && args(s)")
    public void spotinitializationStep(Scenario s, JoinPoint jp){
    	
    	System.out.println("Tomato Framework: Observing steps of Scenario "+s.getName());
    	this.storing = true;
    	this.scen=s;
    	}
    
    @After("execution(* *.finalizationStep())")
    public void spotfinalizationStep(JoinPoint jp){
    	
    	classes = new LinkedList<String>();
    	methods= new LinkedList<String>();
    	arguments= new LinkedList<Object[]>();
    	classesOfArguments = new LinkedList<Class[]>();
    	nameOfArguments = new LinkedList<String[]>();;
    	}
    
    @After("call(* *.getMethod(String, ..))")
    public void disableStoreMethods(){
    	this.storing = false;
    }
    
    @Pointcut("execution(@cucumber.api.java.en.Given * *(..))")
    public void Given(){}
    
    @Pointcut("execution(@cucumber.api.java.en.Then * *(..))")
    public void Then(){}
    
    @Pointcut("execution(@cucumber.api.java.en.When * *(..))")
    public void When(){}
    
    @After("(Given() || Then() || When()) && !@annotation(testermodule.Tomato)")
    public void storeMethods(JoinPoint jp){

    	if(!this.ignoreNextClass){
    		if(this.storing){

    	
    			if(scen.getSourceTagNames().contains("@quality")){
    		
    				this.classes.add(jp.getThis().getClass().getCanonicalName());
    				String method = jp.getSignature().getName();
    				this.methods.add(method);
    				this.arguments.add(jp.getArgs());
    				CodeSignature cs = (CodeSignature)jp.getSignature();
    				this.classesOfArguments.add(cs.getParameterTypes());
    				this.nameOfArguments.add(cs.getParameterNames());

    	}
    	}
    	}else{
    		this.ignoreNextClass=false;
    	}
    }
    
    
    @Before("call(boolean *.invokeTestingSuite(TesterModuleMessenger)) && args(tmm)")
    public void addInformation(TesterModuleMessenger tmm){
    	
    	tmm.insertMethod(classes, methods, arguments, classesOfArguments, nameOfArguments);
  
    	System.out.println("adding stored method");
    	
    }
    
    @After("execution(boolean *.invokeTestingSuite(TesterModuleMessenger)) && args(tmm)")
    public void enableStoreMethods(TesterModuleMessenger tmm){
    	this.storing = true;
    	//ignoreNextClass = true;
    	tmm.defineNature(0);
      	
    }
    
}




