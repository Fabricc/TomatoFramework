package testermodule;

import java.lang.reflect.InvocationTargetException;

import testermodule.exceptions.StateFormulaNotAssignedException;
import testermodule.exceptions.verificationAlreadyExecutedException;

public class PerformanceNature extends DefaultTestingSuiteDecorator {

	private PerformanceNature(DefaultTestingSuite decoratedDefaultTestingSuite) {
		super(decoratedDefaultTestingSuite);
	}
	
	public static DefaultTestingSuite safelyDecore(DefaultTestingSuite decoratedDefaultTestingSuite){
		if(!decoratedDefaultTestingSuite.getDecorations().contains("Performance")){
			return new PerformanceNature(decoratedDefaultTestingSuite);
		}else return decoratedDefaultTestingSuite;
	}
	
	public String getNatureDescription(){
		return "Performance";
	}
	
	public boolean invokeTestingSuite(TesterModuleMessenger tmm) throws StateFormulaNotAssignedException {
	
			super.executeTesting();
			try {
				boolean result = super.verifyRequirement();
			} catch (verificationAlreadyExecutedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
		
		return false;
	}

}
