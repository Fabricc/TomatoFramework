package tomato.TesterModule.main;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.time.StopWatch;



public class ProbabilisticTestingSuite extends ParentTestingSuite {
	
	private class conditionChecker{
		conditionChecker(String condition){
			if(condition.equals("<")){
				this.mode=0;
			}else if(condition.equals(">")){
				this.mode=1;
			}else if(condition.equals("=")){
				this.mode=2;
			}else if(condition.equals("<=")){
				this.mode=3;
			}else if(condition.equals(">=")){
				this.mode=4;
			}else this.mode=5;
		}
		
		private int mode;
		
		public boolean compare(double a, double b) throws IncorrectConditionException{
			if(this.mode==0) return (a<b);
			if(this.mode==1) return (a>b);
			if(this.mode==2) return (a==b);
			if(this.mode==3) return (a<=b);
			if(this.mode==4) return (a>=b);
			throw new IncorrectConditionException();
		}
	}
	
	
	private int numberIterations = 10;
	
	private Boolean reliabilityReport = false;
	

//	public void executeTesting(String stateFormula, List<Double> executions, List<Boolean> successes) 
//			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, 
//			NoSuchMethodException, SecurityException, InstantiationException, StateFormulaNotAssignedException{
//		
//		
//		
//		List<String> steps = super.getStateFormulaSteps(stateFormula);
//		List<String> external_methods = super.getStateFormulaMethod(stateFormula);
//		if(steps==null && external_methods==null) throw new StateFormulaNotAssignedException();
//		boolean external = false;
//		if(external_methods!=null) external=true;
//
//		StopWatch stopwatch = new StopWatch();
//		
//		List<Object> cache_object = new LinkedList<Object>();
//		
//		for(int i = 0; i<numberIterations; i++){
//			System.out.println("Iteration nr."+ (i+1));
//			
//			boolean noExceptions = true;
//			boolean timed_constrain = false;
//			stopwatch.reset();
//			
//			for(int j = 0; j<tmm.getClasses().size() && noExceptions; j++){
//				
//				Class c = null;
//				boolean timed = false;
//				
//				String met = tmm.getMethods().get(j);
//				if(steps!=null && steps.contains(met)) {
//					timed=true;
//					timed_constrain=true;
//				}
//				
//				
//				Class[] class_arguments= tmm.getClassArguments().get(j);
//				
//				Method method = null;
//				
//					String class_name = tmm.getClasses().get(j);
//					c = Class.forName(class_name);
//					method = c.getMethod(met,class_arguments);
//					
//					Object o = null;
//					
//					for(int y = 0; y<cache_object.size(); y++){
//						String name_class_in_list = ((Class)(cache_object.get(y)).getClass()).getName();
//						if(class_name.equals(name_class_in_list)) o = cache_object.get(y);
//					}
//					
//					if(o==null){
//					if(this.dependency!=null && this.dclass!=null && this.dclass.equals(c.getName())){
//						o = c.getConstructor(this.dependency.getClass()).newInstance(this.dependency);
//					}else o = c.newInstance();
//					cache_object.add(o);
//					}
//					
//					Object[] arguments = tmm.getArguments().get(j);
//					String[] names = tmm.getNameOArguments().get(j);
//					
//					Object[] modified_arguments = super.modifyArguments(stateFormula, met, class_arguments, arguments, names);
//				
//				    
//			
//				if(timed){
//					try{
//						
//						System.out.println("Measuring method:"+met);
//						
//						if(!stopwatch.isStarted()) {
//							stopwatch.reset();
//							stopwatch.start();
//						}
//						else stopwatch.resume();
//						
//						
//						
//						method.invoke(o,modified_arguments);
//						//if(userdefined_mode) sfi.execute();
//						if(i==(tmm.getClasses().size()-1)) stopwatch.stop();
//						else stopwatch.suspend();
//						
//					}catch(Exception e){
//						e.printStackTrace();
//						stopwatch.stop();
//						noExceptions=false;
//					}
//					
//					} else {
//						method.invoke(o,modified_arguments);
//					}
//				
//				if(external && j==tmm.getClasses().size()-1){
//					class_name = tmm.getClasses().get(0);
//					c = Class.forName(class_name);
//					method = c.getMethod(external_methods.get(0));
//					
//					Boolean result = (Boolean)method.invoke(o);
//					successes.add(i, result);
//				}
//				
//				}
//			
//			
//			
//			long milliseconds = stopwatch.getTime();
//
//			double seconds = (double)(milliseconds / 1000.0);
//			
//			executions.add(i, seconds);
//			stopwatch.reset();
//			if(timed_constrain) successes.add(i, noExceptions);
//		}
//	}
	
	public List<IterationReport> executeTesting(String stateFormula) 
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, 
			NoSuchMethodException, SecurityException, InstantiationException, StateFormulaNotAssignedException{
		
		StopWatch stopwatch = new StopWatch();
		List<IterationReport> result = new LinkedList<IterationReport>();
		List<String> stateFormulaScope = super.getStateFormulaSteps(stateFormula);
		
		
		for(int i = 0; i<numberIterations; i++){
			System.out.println("Iteration nr."+ (i+1));
			
			boolean noExceptions = true;
			stopwatch.reset();
			int numberOfSteps = tmm.getClasses().size();
			IterationReport iteration_report = new IterationReport();
			
			for(int j = 0; j<numberOfSteps && noExceptions; j++){
				
				String class_name = tmm.getClasses().get(j);
				Class c = Class.forName(class_name);
				
				String name_function = tmm.getMethods().get(j);
				boolean inScope = false;
				if(stateFormulaScope!=null && stateFormulaScope.contains(name_function)) inScope=true;
				Class[] class_arguments= tmm.getClassArguments().get(j);
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
					
				Object[] arguments = tmm.getArguments().get(j);
				String[] names = tmm.getNameOArguments().get(j);
				Object[] modified_arguments = super.modifyArguments(stateFormula, name_function, class_arguments, arguments, names);
				
				try{
						
					stopwatch.start();
					method.invoke(o,modified_arguments);
					stopwatch.stop();
					
				}catch(Exception e){
					stopwatch.stop();
					e.printStackTrace();
					noExceptions=false;
					iteration_report.declareStepFailed(name_function, e, j+1);
				}finally{
					long milliseconds = stopwatch.getTime();
					double seconds = (double)(milliseconds / 1000.0);
					iteration_report.insertStepExecutionTime(seconds,inScope);
					stopwatch.reset();
					
				}
		
			}
			
			result.add(iteration_report);
		}
		return result;
	}
	
	
	public void executeTestingWithExternalMethod(List<IterationReport> report, String stateFormula) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
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
				Class c = Class.forName(class_name);
				
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
		
		if(userDefinedStateFormulaMethod==null) return;
		
		for(IterationReport ir: report){
			Object classInstance = ir.getClassInstanceByName(className);
			
			if(ir.isCorrectlyExecuted() && !(Boolean)userDefinedStateFormulaMethod.invoke(classInstance)){
				ir.declareStepFailed(userDefinedStateFormulaMethod.getName(), new Exception());
			}
		}
		
		
	}

	
	
	//{p=3, t=5, timeBound=>, stateFormula=something happening, probabilityBound=<}
	
	@Override
	public boolean invokeTestingSuite(TesterModuleMessenger tmm) throws StateFormulaNotAssignedException{
		
		System.out.println("Tomato Framework =================== Starting execution");
		
		String stateFormula = (String)tmm.getParameter("stateFormula");
		
		System.out.println("The number of iterations is "+ numberIterations);
		System.out.println("The stateFormula is "+stateFormula);
		

		 try {
			List<IterationReport> report = this.executeTesting(stateFormula);
			executeTestingWithExternalMethod(report,stateFormula);
			System.out.println();
			boolean result = this.verifyRequirement(report,stateFormula);
			if(this.reliabilityReport){
				this.generateReliabilityReport(report);
			}
			System.out.println();
			return result;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		} catch (IncorrectConditionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 
		 return false;
		
	}
	

	
	
	//{p=0.5, t=5, timeBound=>, stateFormula=something happening, probabilityBound=<}
//	private boolean verifyRequirement(List<Double> executions, List<Boolean> successes) throws IncorrectConditionException{
//		System.out.println("Report ProbabilisticTestingSuite Execution");
//		
//		boolean[] passed = new boolean[executions.size()];
//		double executionProbability = 0;
//		
//		boolean checkTime = false;
//		conditionChecker timeChecker = null;
//		double timeConditionValue = 0;
//		String timeBound = (String)tmm.getParameter("timeBound");
//		int number_of_execution = executions.size();
//		if(timeBound!=null) {
//			checkTime=true;
//			timeChecker = new conditionChecker(timeBound);
//			timeConditionValue = (Double)tmm.getParameter("t");
//		
//		
//		System.out.println("StateFormula verified for the following values where time "+timeBound+" of "+timeConditionValue);
//		int number_of_passed=0;
//		
//		for(int i=0; i<number_of_execution; i++){
//		if(successes.get(i)){
//			System.out.print("Iteration nr."+i+" Time: "+executions.get(i)+" s ");
//			if(checkTime) {
//				passed[i]=timeChecker.compare(executions.get(i), timeConditionValue);
//				if(passed[i]) System.out.println("passed");
//				else System.out.println("not passed");
//			}
//			else passed[i]=true;
//				
//			
//			if(passed[i]) number_of_passed++;
//		}
//		}
//		executionProbability = (double)number_of_passed/(double)number_of_execution;
//		}else{
//			System.out.println("StateFormula verified for the following values where the condition is verified");
//			int verified=0;
//			int i=0;
//			for(boolean s: successes){
//				if(s) {
//					verified++;
//					System.out.println("Iteration nr."+i+" Verified: true");
//				}else System.out.println("Iteration nr."+i+" Verified: false");
//				i++;
//			}
//			executionProbability = (double)verified/(double)number_of_execution;
//		}
//		
//		
//		double probabilityConditionValue = (Double)tmm.getParameter("p");
//		String probabilityBound = (String)tmm.getParameter("probabilityBound");
//		conditionChecker probabilityChecker = new conditionChecker(probabilityBound);
//		
//		
//		
//		
//		System.out.println("Constraint verified if the previous assertion is true for "+probabilityBound+" "+probabilityConditionValue+" of iterations");
//		System.out.println("Probaility measured:"+executionProbability);
//		System.out.println("Tomato Report Framework =================== Stopping execution");
//		return probabilityChecker.compare(executionProbability,probabilityConditionValue);
//		
//		
//		
//		
//		
//		
//	}
	
	private boolean verifyRequirement(List<IterationReport> report, String stateFormula) throws IncorrectConditionException{
		System.out.println("Report ProbabilisticTestingSuite Execution");
		
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
			System.out.println("Iteration nr."+i+" Time: "+ir.getTotalExecutionTime()+" s ");
			if(checkTime) {
				passed[i]=timeChecker.compare(ir.getTotalExecutionTime(), timeConditionValue);
				if(passed[i]) System.out.println("passed");
				else {
					ir.declareStepFailed("quality constraint", new Exception());
					System.out.println("not passed");
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
					System.out.println("Iteration nr."+i+" Verified: true");
				}else {
					
					System.out.println("Iteration nr."+i+" failed because method "+ir.getFailingMethod());
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
		System.out.println("Tomato Report Framework =================== Stopping execution");
		return probabilityChecker.compare(executionProbability,probabilityConditionValue);
		
		}
	
	private void generateReliabilityReport(List<IterationReport> report){
		System.out.println("<<Opening Reliability Overview>>");
		double totalExecutions = report.size();
		double totalFailings = 0;
		double totalExecutionTime = 0;
		double mttf = 0;
		List<Double> ttfs = new LinkedList<Double>();
		double timeToFailure=0;
		
		for(IterationReport ir:report){
			if(!ir.isCorrectlyExecuted()) {
				totalFailings++;
				timeToFailure+=ir.getTotalExecutionTime();
				if(totalFailings>1) ttfs.add(timeToFailure);
				timeToFailure=0;
			}
			timeToFailure+=ir.getTotalExecutionTime();
			totalExecutionTime += ir.getTotalExecutionTime();
			
		}
		
		if(totalFailings==0) {
			System.out.println("Scenario executed without failures");
			System.out.println("<<Closing Reliability Overview>>");
			return;
		}
		
		//Calculation Probability Of Failure On Demand(POFOD)
		double pofod = totalFailings/totalExecutions;
		System.out.println("Probability Of Failure On Demand(POFOD) is "+pofod);
		
		//Calculation Rate Of Occurrence Of Failure(ROCOF)
		double rocof = totalFailings/totalExecutionTime;
		System.out.println("Rate Of Occurrence Of Failure(ROCOF) is "+rocof);
		
		//Calculation Mean Time To Failure
		if(totalFailings>1) {
			for(Double d: ttfs){
			mttf+=d;
			}
			mttf=mttf/ttfs.size();
			System.out.println("Mean Time To Failure(MTTF) is "+mttf);
		}
		System.out.println("<<Closing Reliability Overview>>");
		
	}
	
	public void showReliabilityReport(){
		this.reliabilityReport=true;
	}
	
}
		
	


