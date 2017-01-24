package testermodule.support;

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
	
	public IterationReport(){
		failedStep=0;
		totalTime=0.0;
		executionsTime = new LinkedList<Double>();
		classInstances = new HashSet<Object>();
		stateFormulaScope = new LinkedList<Boolean>();
		steps = new LinkedList<String>();
	}
	
	public void addClassInstance(Object instance){
		classInstances.add(instance);
	}
	
	public Object getClassInstanceByName(String klass){
		for(Object instance: this.classInstances){
			if((instance.getClass().getName()).equals(klass)){
				return instance;
			}
		}
		return null;
	}
	
	public void insertStepExecutionTime(Double time){
		executionsTime.add(time);
		totalTime+=time;
		stateFormulaScope.add(false);
	}
	
	public void insertStepExecutionTime(Double time, String step){
		insertStepExecutionTime(time);
		this.steps.add(step);
	}
	
	public void insertStepExecutionTime(Double time, boolean reportTime){
		executionsTime.add(time);
		totalTime+=time;
		stateFormulaScope.add(reportTime);
	}
	
	public void declareStepFailed(String nameStep, Exception e){
		this.failedStep = executionsTime.size();
		this.nameFailedStep = nameStep;
		this.raisedException = e;
	}
	
	public void declareStepFailed(String nameStep, Exception e, int step){
		this.failedStep = step;
		this.nameFailedStep = nameStep;
		this.raisedException = e;
	}
	
	public boolean isCorrectlyExecuted(){
		if(failedStep>0) return false;
		else return true;
	}
	
	public String getFailingMethod(){
		return this.nameFailedStep;
		}
	
	public Double getScopedTotalExecutionTime(List<Boolean> scope){
		Double totalTime = 0.0;
		for(int i=0; i<this.executionsTime.size(); i++){
			if(scope.get(i)){
				totalTime+=this.executionsTime.get(i);
			}
		}
		return totalTime;
	}
	
	public Double getTotalExecutionTime(){
		return totalTime;
	}
	

}
	