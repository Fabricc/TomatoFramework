 package testermodule;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import testermodule.exceptions.StateFormulaNotAssignedException;
import testermodule.exceptions.verificationAlreadyExecutedException;
import testermodule.support.IterationReport;

public interface DefaultTestingSuite {
	public void executeTesting() throws StateFormulaNotAssignedException;
	
	public LinkedList<IterationReport> getReport();
	
	public void wipeReport();
	
	public void defineRequirementNature(int nature);
	
	public boolean isRequirementNature(int nature);
	
	public TesterModuleMessenger getMessenger();
	
	public boolean invokeTestingSuite(TesterModuleMessenger tmm) throws StateFormulaNotAssignedException;
	
	public IterationReport getIterationReport(int index);
	
	public boolean applyExternalStateFormulaMethod(String stateFormula);

	public boolean verifyRequirement() throws verificationAlreadyExecutedException;
	
	public void assignRuleRandom(String stateFormula, String method, String argument, Double min, Double max);
	
	public void assignRuleRandom(String stateFormula, String method, String argument, int min, int max);
	
	public void assignStateFormula(String stateFormula, String method);
	
	public String getNatureDescription();
	
	public List<String> getDecorations();
	
	public IterationReport executeScenario(IterationReport iteration_report, int nextStep) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
}
