package testermodule.main;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class IterationReport {
	private int failedStep;
	private String nameFailedStep;
	private List<String> steps; 
	private Exception raisedException;
	private List<Double> executionsTime;
	private List<Boolean> stateFormulaScope;
	private Set<Object> classInstances;
	private Double totalTime;
	
	IterationReport(){
		failedStep=0;
		totalTime=0.0;
		executionsTime = new LinkedList<Double>();
		classInstances = new HashSet<Object>();
		stateFormulaScope = new LinkedList<Boolean>();
		steps = new LinkedList<String>();
	}
	
	void addClassInstance(Object instance){
		classInstances.add(instance);
	}
	
	Object getClassInstanceByName(String klass){
		for(Object instance: this.classInstances){
			if((instance.getClass().getName()).equals(klass)){
				return instance;
			}
		}
		return null;
	}
	
	void insertStepExecutionTime(Double time){
		executionsTime.add(time);
		totalTime+=time;
		stateFormulaScope.add(false);
	}
	
	void insertStepExecutionTime(Double time, String step){
		insertStepExecutionTime(time);
		this.steps.add(step);
	}
	
	void insertStepExecutionTime(Double time, boolean reportTime){
		executionsTime.add(time);
		totalTime+=time;
		stateFormulaScope.add(reportTime);
	}
	
	void declareStepFailed(String nameStep, Exception e){
		this.failedStep = executionsTime.size();
		this.nameFailedStep = nameStep;
		this.raisedException = e;
	}
	
	void declareStepFailed(String nameStep, Exception e, int step){
		this.failedStep = step;
		this.nameFailedStep = nameStep;
		this.raisedException = e;
	}
	
	boolean isCorrectlyExecuted(){
		if(failedStep>0) return false;
		else return true;
	}
	
	String getFailingMethod(){
		return this.nameFailedStep;
		}
	
	Double getScopedTotalExecutionTime(List<Boolean> scope){
		Double totalTime = 0.0;
		for(int i=0; i<this.executionsTime.size(); i++){
			if(scope.get(i)){
				totalTime+=this.executionsTime.get(i);
			}
		}
		return totalTime;
	}
	
	Double getTotalExecutionTime(){
		return totalTime;
	}
	

}
	