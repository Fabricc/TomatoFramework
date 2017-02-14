package testermodule;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.time.StopWatch;

import testermodule.exceptions.IncorrectConditionException;
import testermodule.exceptions.StateFormulaNotAssignedException;
import testermodule.exceptions.verificationAlreadyExecutedException;
import testermodule.support.IterationReport;
import testermodule.support.conditionChecker;



public class DefaultTestSuiteImplementation extends ParentTestSuite implements DefaultTestSuite {
	
	
//	
//	private class reliabilityReport{
//		public double rocof, mttf, pofod,totalFailings;
//	}
//	
	
	private int numberIterations = 10;
	private int nextStep = 0;
	private int nature = 0;
	//private Boolean requestedReliabilityReport = false;
	private LinkedList<IterationReport> report;
	//private reliabilityReport rreport;
	
//	protected reliabilityReport getReliabilityReport(){
//		if(rreport==null){
//			rreport=this.elaborateReliabilityReport();
//		}
//		
//		return rreport;
//	}
	
//	protected Boolean isRequestedReliabilityReport(){
//		return this.requestedReliabilityReport;
//	}
	
	public TesterModuleMessenger getMessenger(){
		return super.getMessenger();
	}
	
	public int getNature(){
		return this.nature;
	}
	
	public void wipeReport(){
		this.report=null;
	}
	
	public void setIterations(int iterations){
		this.numberIterations = iterations;
	}
	
	public LinkedList<IterationReport> getReport(){
		if(this.report==null){
			this.report = new LinkedList<IterationReport>();
		}
		return report;
	}
	
	public IterationReport getIterationReport(int index){
		List<IterationReport> report = getReport();
		IterationReport result = null;
		try{
		result = report.get(index);
		}catch(IndexOutOfBoundsException e){
			result = new IterationReport();
			report.add(index, result);
		}
		return result;
	}
	


	
	public void executeTesting() throws StateFormulaNotAssignedException{
		System.out.println("Tomato Framework =================== Starting execution");
		String stateFormula = (String)tmm.getParameter("stateFormula");
		int numberOfSteps = tmm.getClasses().size();
		
		
		
		if(numberOfSteps==this.nextStep) {
			System.out.println("No methods to execute");
			return;
		}
		for(int i = 0; i<numberIterations; i++){
			System.out.println("Iteration nr."+ (i+1));
			
			IterationReport iteration_report = this.getIterationReport(i);
			try {
				executeScenario(iteration_report,this.nextStep);
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
		nextStep = numberOfSteps;
		System.out.println("The number of iterations is "+ numberIterations);
		System.out.println("The stateFormula is "+stateFormula);
		
		
	}
	
	
	public IterationReport executeScenario(IterationReport iteration_report, int nextStep) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		int numberOfSteps = tmm.getClasses().size();
		StopWatch stopwatch = new StopWatch();
		boolean noExceptions = true;
		String stateFormula = (String)tmm.getParameter("stateFormula");
		
		for(int executionPointer = nextStep; executionPointer<numberOfSteps && noExceptions; executionPointer++){
			
			String class_name = tmm.getClasses().get(executionPointer);
			Class c = Class.forName(class_name);
			
			String name_function = tmm.getMethods().get(executionPointer);
			//boolean inScope = false;
			//if(stateFormulaScope!=null && stateFormulaScope.contains(name_function)) inScope=true;
			Class[] class_arguments= tmm.getClassArguments().get(executionPointer);
			Method method = c.getMethod(name_function,class_arguments);
		
			Object o = iteration_report.getClassInstanceByName(class_name);
			
			if(o==null){
				
				Constructor[] constructors = c.getConstructors();
				//add here exception if the array has more than one entry
				Class[] parametersClass = constructors[0].getParameterTypes();
				Object[] parametersInstance = new Object[parametersClass.length];
				
				for(int y=0; y<parametersClass.length; y++){
					parametersInstance[y]=parametersClass[y].newInstance();
				}
				o = constructors[0].newInstance(parametersInstance);
				iteration_report.addClassInstance(o);
			}
				
			Object[] arguments = tmm.getArguments().get(executionPointer);
			String[] names = tmm.getNameOArguments().get(executionPointer);
			
			Object[] modified_arguments = null;
			if(stateFormula!=null){
				modified_arguments = super.modifyArguments(stateFormula, name_function, class_arguments, arguments, names);
			}
			try{
					
				stopwatch.start();
				method.invoke(o,modified_arguments);
				stopwatch.stop();
				
			}catch(Exception e){
				stopwatch.stop();
				e.printStackTrace();
				noExceptions=false;
				iteration_report.declareStepFailed(name_function, e, executionPointer+1);
			}finally{
				long milliseconds = stopwatch.getTime();
				double seconds = (double)(milliseconds / 1000.0);
				//iteration_report.insertStepExecutionTime(seconds,inScope);
				iteration_report.insertStepExecutionTime(seconds,name_function);
				stopwatch.reset();
				
			}
	
		}
		return iteration_report;
	}
	
	
	public boolean applyExternalStateFormulaMethod(String stateFormula) {
		
		List<String> stateFormulaScope = super.getStateFormulaSteps(stateFormula);
		Method userDefinedStateFormulaMethod = null;
		String className = null;
		
		for(String stateFormulaMethodName: stateFormulaScope){
			
			boolean found = false;
			for(String method_name:tmm.getMethods()){
				if(method_name.equals(stateFormulaMethodName)){
					found=true;
					break;
				}
			}
			
			if(!found){
				for(String class_name : tmm.getClasses()){
				Class c = null;
				try {
					c = Class.forName(class_name);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try{
					Method method = c.getMethod(stateFormulaMethodName);
					className = class_name;
					userDefinedStateFormulaMethod = method;
					break;
				}catch(NoSuchMethodException e){
					
				}
				
				}
			}
			
		}
		
		if(userDefinedStateFormulaMethod==null) {
			System.out.println("ExternalStateFormulaMethod not found");
			return false;
		}
		
		for(IterationReport ir: report){
			Object classInstance = ir.getClassInstanceByName(className);
//With external methods is needed a way to define what is expected. In fact, in this context true does not necessarily means success and viceversa.
//hacked for the demo
			try {
				if(ir.isCorrectlyExecuted() && (Boolean)userDefinedStateFormulaMethod.invoke(classInstance)){
					ir.declareStepFailed(userDefinedStateFormulaMethod.getName(), new Exception());
				}
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
		System.out.println("ExternalStateFormulaMethod "+userDefinedStateFormulaMethod.getName()+" executed");
		return true;
		
		
	}

//	protected boolean executeAndVerify() throws IllegalAccessException, IllegalArgumentException, 
//	InvocationTargetException, ClassNotFoundException, NoSuchMethodException, SecurityException, 
//	InstantiationException, StateFormulaNotAssignedException{
//		
//		String stateFormula = (String)tmm.getParameter("stateFormula");
//		this.executeTesting(stateFormula);
//		boolean result = this.verifyRequirement();
//		return result;
//	}
	
	//{p=3, t=5, timeBound=>, stateFormula=something happening, probabilityBound=<}
	
	@Override
	public boolean invokeTestingSuite(TesterModuleMessenger tmm) throws StateFormulaNotAssignedException{

				this.executeTesting();
				try {
					return this.verifyRequirement();
				} catch (verificationAlreadyExecutedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return false;
		
	}
	
//	private boolean verifyRequirement(String stateFormula, int testingSuite) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
//		if(testingSuite==0) return verifyProbabilisticRequirement(stateFormula);
//		if(testingSuite==1) return verifyReliabilityRequirement(elaborateReliabilityReport());
//		return false;
//		
//	}
	
//	private boolean verifyReliabilityRequirement(reliabilityReport report){
//		System.out.println("Report ReliabilityTestingSuite Execution");
//		String metric = (String)tmm.getParameter("reliabilityMetric");
//		String bound = (String)tmm.getParameter("reliabilityBound");
//		Double value = (Double)tmm.getParameter("p");
//		Double actual_value=0.0;
//		System.out.println("The value of the metric "+metric+" should be "+
//		bound+" "+tmm.getParameter("p"));
//		
//		if(metric.equals("rocof")) actual_value = report.rocof;
//		else if(metric.equals("pofod")) actual_value = report.pofod;
//		else if(metric.equals("mttf")) actual_value = report.mttf;
//		System.out.println("The current value is "+actual_value);
//		
//		conditionChecker checker = new conditionChecker(bound);
//		try {
//			if(checker.compare(actual_value,value)){
//				System.out.println("NFR Satisfied");
//				System.out.println();
//				System.out.println();
//				return true;
//			}else{
//				System.out.println("NFR Not Satisfied");
//				System.out.println();
//				System.out.println();
//				return false;
//			}
//		} catch (IncorrectConditionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return false;
//		}
//	}
	
	
	public boolean verifyRequirement() throws verificationAlreadyExecutedException{
		if(tmm.getNature()==-1) throw new verificationAlreadyExecutedException();
		else tmm.defineNature(-1);
		String stateFormula = (String)tmm.getParameter("stateFormula");
		Boolean externalMethod = applyExternalStateFormulaMethod(stateFormula);
		
		System.out.println("Report ProbabilisticTestingSuite Execution");
		List<String> stateFormulaScopeMethods = super.getStateFormulaSteps(stateFormula);
		List<String> steps = tmm.getMethods();
		List<Boolean> stateFormulaScope = new LinkedList<Boolean>();
		
		for(String step: steps){
			Boolean inScope=false;
			if(!externalMethod){
				
				for(String scope_method: stateFormulaScopeMethods){
					if(step.equals(scope_method)) inScope=true;
				}
			}else inScope=true;
			
			stateFormulaScope.add(inScope);
		}
		
		int number_of_iterations = report.size();
		boolean[] passed = new boolean[number_of_iterations];
		double executionProbability = 0;
		
		boolean checkTime = false;
		conditionChecker timeChecker = null;
		double timeConditionValue = 0;
		String timeBound = (String)tmm.getParameter("timeBound");
		if(timeBound!=null) {
			checkTime=true;
			timeChecker = new conditionChecker(timeBound);
			timeConditionValue = (Double)tmm.getParameter("t");
		
		
			System.out.println("StateFormula verified for the following values where time "+timeBound+" of "+timeConditionValue);
			int number_of_passed=0;
			
			for(int i=0; i<number_of_iterations; i++){
				IterationReport ir = report.get(i);
				
				if(ir.isCorrectlyExecuted()){
					System.out.println("Iteration nr."+i+" stateFormulaScope Time: "+ir.getScopedTotalExecutionTime(stateFormulaScope)+" seconds, Total Time:"+ir.getTotalExecutionTime()+" seconds");
					if(checkTime) {
						try {
							passed[i]=timeChecker.compare(ir.getScopedTotalExecutionTime(stateFormulaScope), timeConditionValue);
						} catch (IncorrectConditionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(passed[i]) System.out.println("Passed");
						else {
							ir.declareStepFailed(stateFormula+" not satisfied", new Exception());
							System.out.println("Not Passed");
					}
					}
					else passed[i]=true;
				
					if(passed[i]) number_of_passed++;
				}else{
					System.out.println("Iteration nr."+i+" failed because method "+ir.getFailingMethod());
				}
		}
			
		executionProbability = (double)number_of_passed/(double)number_of_iterations;
		}else{
			System.out.println("StateFormula verified for the following values where the condition is verified");
			int verified=0;
			int i=0;
			for(IterationReport ir: report){
				if(ir.isCorrectlyExecuted()) {
					verified++;
					System.out.println("Iteration nr."+i+" Success");
				}else {
					
					System.out.println("Iteration nr."+i+" Failed because method "+ir.getFailingMethod());
				}
				i++;
			}
			executionProbability = (double)verified/(double)number_of_iterations;
		}
		
		
		double probabilityConditionValue = (Double)tmm.getParameter("p");
		String probabilityBound = (String)tmm.getParameter("probabilityBound");
		conditionChecker probabilityChecker = new conditionChecker(probabilityBound);
		
		
		
		
		System.out.println("Constraint verified if the previous assertion is true for "+probabilityBound+" "+probabilityConditionValue+" of iterations");
		System.out.println("Probaility measured:"+executionProbability);
		
		try {
			return probabilityChecker.compare(executionProbability,probabilityConditionValue);
		} catch (IncorrectConditionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		}
	
//	private void printReliabilityReport(){
//		reliabilityReport report = getReliabilityReport();
//		System.out.println("<<Opening Reliability Overview>>");
//		
//		if(report.totalFailings==0) {
//			System.out.println("Scenario executed without failures");
//			System.out.println("<<Closing Reliability Overview>>");
//			return;
//		}
//		
//		//Calculation Probability Of Failure On Demand(POFOD)
//		System.out.println("Probability Of Failure On Demand(POFOD) is "+report.pofod);
//		
//		//Calculation Rate Of Occurrence Of Failure(ROCOF)
//		System.out.println("Rate Of Occurrence Of Failure(ROCOF) is "+report.rocof);
//		
//		//Calculation Mean Time To Failure
//		if(report.totalFailings>1) {
//			System.out.println("Mean Time To Failure(MTTF) is "+report.mttf);
//		}
//		System.out.println("<<Closing Reliability Overview>>");
//		
//	}
//	
//	public reliabilityReport elaborateReliabilityReport(){
//		reliabilityReport result = new reliabilityReport();
//		double totalExecutions = report.size();
//		double totalFailings = 0;
//		double totalExecutionTime = 0;
//		double mttf = 0;
//		List<Double> ttfs = new LinkedList<Double>();
//		double timeToFailure=0;
//		
//		for(IterationReport ir:report){
//			timeToFailure+=ir.getTotalExecutionTime();
//			if(!ir.isCorrectlyExecuted()) {
//				totalFailings++;
//				ttfs.add(timeToFailure);
//				timeToFailure=0;
//			}
//			totalExecutionTime += ir.getTotalExecutionTime();
//			
//		}
//		
//		result.totalFailings=totalFailings;
//		//Calculation Probability Of Failure On Demand(POFOD)
//		result.pofod = totalFailings/totalExecutions;
//		
//		//Calculation Rate Of Occurrence Of Failure(ROCOF)
//		result.rocof = totalFailings/totalExecutionTime;
//		
//		//Calculation Mean Time To Failure
//		if(totalFailings>1) {
//			for(Double d: ttfs){
//			mttf+=d;
//			}
//			mttf=mttf/ttfs.size();
//			result.mttf=mttf;
//		}
//		return result;
//	}
//	
//	public void showReliabilityReport(){
//		this.requestedReliabilityReport=true;
//	}

	public void defineRequirementNature(int nature) {
		this.nature = nature;
		
	}

public boolean isRequirementNature(int nature) {
	// TODO Auto-generated method stub
	return false;
}

public String getNatureDescription(){
	return "default";
}

public List<String> getDecorations(){
	List<String> list = new LinkedList<String>();
	list.add(this.getNatureDescription());
	return list;
}


	
}
		
	


