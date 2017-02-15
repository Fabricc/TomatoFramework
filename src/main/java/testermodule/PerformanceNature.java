package testermodule;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang3.time.StopWatch;

import support.Helpers;
import testermodule.exceptions.StateFormulaNotAssignedException;
import testermodule.support.IterationReport;

public class PerformanceNature extends DefaultTestSuiteDecorator {
	
	class Executer implements Runnable{
		
		private int iteration_number;
		private IterationReport ir;
		private Thread t;
		
		Executer(int i, IterationReport ir){
			this.iteration_number=i;
			this.ir=ir;
		}
		
		public void run() {
			try {
				executeScenario(this.ir, 0);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		 public void start () {
			 System.out.println("Starting Thread for iteration Nr. " +  iteration_number );
			      if (t == null) {
			         t = new Thread (this);
			         t.start ();
			      }
		
	}
	}

	private double requestedExecutionTime = 20.0;
	
	private double rateScenarioExecution = 0.1;

	private PerformanceNature(DefaultTestSuite decoratedDefaultTestingSuite) {
		super(decoratedDefaultTestingSuite);
	}
	
	public static DefaultTestSuite safelyDecore(DefaultTestSuite decoratedDefaultTestingSuite){
		if(!decoratedDefaultTestingSuite.getDecorations().contains("Performance")){
			return new PerformanceNature(decoratedDefaultTestingSuite);
		}else return decoratedDefaultTestingSuite;
	}
	
	public String getNatureDescription(){
		return "Performance";
	}
	
	public boolean invokeTestSuite(TesterModuleMessenger tmm) throws StateFormulaNotAssignedException {     
			System.out.println("<<Opening Performance Overview>>");
			executeTesting();
//			try {
//				boolean result = super.verifyRequirement();
//			} catch (verificationAlreadyExecutedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			System.out.println("<<Closing Performance Overview>>");
		return true;
	}
	
	public void executeTesting() throws StateFormulaNotAssignedException{
		 super.wipeReport();
		 StopWatch totalTime = new StopWatch();

		 
		 totalTime.start();
		 int i=0;
		 while(Helpers.millisecondsToSeconds(totalTime.getTime())<this.requestedExecutionTime){
			 IterationReport ir = super.getIterationReport(i);
			 new Executer(i, ir).start();
			 i++;
			 try {
				Thread.sleep(Helpers.secondsToMilliseconds(this.rateScenarioExecution));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	}	



}
	


