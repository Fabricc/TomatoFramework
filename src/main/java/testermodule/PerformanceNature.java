package testermodule;

import java.lang.reflect.InvocationTargetException;

import testermodule.exceptions.StateFormulaNotAssignedException;
import testermodule.exceptions.verificationAlreadyExecutedException;
import testermodule.support.IterationReport;

public class PerformanceNature extends DefaultTestingSuiteDecorator {
	
	class Executer implements Runnable{

		public void run() {
			// TODO Auto-generated method stub
			
		}
		
	}

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
	
	public void executeTesting() throws StateFormulaNotAssignedException{
		
	}
	
	public IterationReport executeScenario(IterationReport iteration_report, int nextStep) throws ClassNotFoundException, NoSuchMethodException, 
		SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
	return null;
	}
	
}
	


