package TesterModule.src.test.java;

import cucumber.api.Scenario;

public aspect Generic {
	pointcut anyCall() : execution(void *.alternativeOne(..));

    before() : anyCall(){
        System.out.println("hey");
        
    }
    
    
    pointcut spotBefore(Scenario s) : (execution(* *.before(Scenario)) && args(s));
    
    
    after(Scenario s): spotBefore(s){
    	System.out.println("I am AspectJ: "+s.getName());}
    	
    pointcut spotAnnotations(): execution(@annotation(Before) * *(..));
    
    
    after():spotAnnotations(){
    	System.out.println("print after annotation");
    }

}
