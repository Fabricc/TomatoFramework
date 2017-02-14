package testermodule;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import testermodule.exceptions.StateFormulaNotAssignedException;
import testermodule.exceptions.verificationAlreadyExecutedException;
import testermodule.support.IterationReport;

public abstract class DefaultTestingSuiteDecorator implements DefaultTestingSuite{
	
	protected final DefaultTestingSuite decoratedDefaulTestingSuite;
	
	public DefaultTestingSuiteDecorator(DefaultTestingSuite decoratedDefaulTestingSuite){
		this.decoratedDefaulTestingSuite = decoratedDefaulTestingSuite;
	}

	public void executeTesting() throws  StateFormulaNotAssignedException {
		this.decoratedDefaulTestingSuite.executeTesting();
	}
	
	public TesterModuleMessenger getMessenger(){
		return this.decoratedDefaulTestingSuite.getMessenger();
	}
	
	public void defineRequirementNature(int nature){
		this.decoratedDefaulTestingSuite.defineRequirementNature(nature);
	}

	
	public LinkedList<IterationReport> getReport() {
		 return this.decoratedDefaulTestingSuite.getReport();
	}

	public IterationReport getIterationReport(int index) {
		return this.decoratedDefaulTestingSuite.getIterationReport(index);
	}

	public boolean applyExternalStateFormulaMethod(String stateFormula) {
		return this.decoratedDefaulTestingSuite.applyExternalStateFormulaMethod(stateFormula);
	}

	public boolean verifyRequirement()
			throws verificationAlreadyExecutedException {
		return this.decoratedDefaulTestingSuite.verifyRequirement();
	}
	
	public boolean invokeTestingSuite(TesterModuleMessenger tmm) throws StateFormulaNotAssignedException{
		return this.decoratedDefaulTestingSuite.invokeTestingSuite(tmm);
	}
	
	public boolean isRequirementNature(int nature){
		return this.decoratedDefaulTestingSuite.isRequirementNature(nature);
	}
	
	public String getNatureDescription(){
		return this.decoratedDefaulTestingSuite.getNatureDescription();
	}
	
	public List<String> getDecorations(){
		return this.decoratedDefaulTestingSuite.getDecorations();
	}
	
	public void assignRuleRandom(String stateFormula, String method, String argument, Double min, Double max){
		 this.decoratedDefaulTestingSuite.assignRuleRandom(stateFormula, method, argument, min, max);
	}
	
	public void assignRuleRandom(String stateFormula, String method, String argument, int min, int max){
		this.decoratedDefaulTestingSuite.assignRuleRandom(stateFormula, method, argument, min, max);
	}
	
	public void assignStateFormula(String stateFormula, String method){
		this.decoratedDefaulTestingSuite.assignStateFormula(stateFormula, method);
	}
	
	public IterationReport executeScenario(IterationReport iteration_report, int nextStep) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		return this.decoratedDefaulTestingSuite.executeScenario(iteration_report,nextStep);
	}
	
	public void wipeReport(){
		this.decoratedDefaulTestingSuite.wipeReport();
	}
	
	public void setIterations(int iterations){
		this.decoratedDefaulTestingSuite.setIterations(iterations);
	}
	

}
