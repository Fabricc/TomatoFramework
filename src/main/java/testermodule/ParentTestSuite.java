package testermodule;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import testermodule.exceptions.StateFormulaNotAssignedException;
import testermodule.rules.RandomRuleTuple;
import testermodule.rules.RuleTuple;

public abstract class ParentTestSuite {
	TesterModuleMessenger tmm;
	
	protected String dclass;
	
	public ParentTestSuite(){
		this.tmm = new TesterModuleMessenger();
	}
	
	public TesterModuleMessenger getMessenger() {
		
		return this.tmm;
	}
	
	private Map<String, List<String>> stateFormulaStepsMap;
	private Map<String,String> stateFormulaExternalMethodsMap;
	private List<RuleTuple> rules;
	
	public void assignStateFormulaExternal(String stateFormula, String method){
		
		if(stateFormulaExternalMethodsMap == null) stateFormulaExternalMethodsMap = new HashMap<String, String>();
		stateFormulaExternalMethodsMap.put(stateFormula, method);
	}
	
	public void assignStateFormula(String stateFormula, String method){
		if(stateFormulaStepsMap == null) stateFormulaStepsMap = new HashMap<String, List<String>>();
		List<String> list = stateFormulaStepsMap.get(stateFormula);
		if(list==null) list=new LinkedList<String>();
		list.add(method);
		stateFormulaStepsMap.put(stateFormula, list);
		
	}
	
	public void assignRuleRandom(String stateFormula, String method, String argument, Double min, Double max){
		if(rules==null) rules = new LinkedList<RuleTuple>();
		rules.add(new RandomRuleTuple(stateFormula, method, argument, min, max));
		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		}
	
	public void declareDependency(String dclass, Object o){
		this.dclass=dclass;
	}
	
	public void assignRuleRandom(String stateFormula, String method, String argument, int min, int max){
		if(rules==null) rules = new LinkedList<RuleTuple>();
		rules.add(new RandomRuleTuple(stateFormula, method, argument, min, max));
		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		}
	
	public Object[] modifyArguments(String stateFormula, String method, Class[] class_arguments, Object[] arguments,
			String[] names) {
		
		Object[] modified_arguments = arguments.clone();
		boolean found = false;
		for(int i = 0; i<class_arguments.length; i++){
			String argument_name = names[i];
			
			
			
			for(RuleTuple rt: rules){
				if(rt.identifyRule(stateFormula, method, argument_name)){
					if(rt.getRule().equals("randomInt")){
						modified_arguments[i]=((RandomRuleTuple)rt).getRandomIntValue();
						found=true;
					}
					if(rt.getRule().equals("randomDouble")){
						modified_arguments[i]=((RandomRuleTuple)rt).getRandomDoubleValue();
						found=true;
					}
						
					break;
				}
			}
		}
		
//		if(found){
//		System.out.println("The method "+method+" will take the following arguments:");
//		for(int i=0; i<modified_arguments.length; i++){
//			System.out.println("Name: "+names[i]+" value:"+modified_arguments[i]);
//		}}
		return modified_arguments;
		
	}
	
	
	
	
	
	protected List<String> getStateFormulaSteps(String stateFormula){
		if(stateFormulaStepsMap !=null) return stateFormulaStepsMap.get(stateFormula);
		return null;
	}
	
	protected String getStateFormulaMethod(String stateFormula){
		if(stateFormulaExternalMethodsMap !=null) return stateFormulaExternalMethodsMap.get(stateFormula);
		return null;
	}

	public abstract boolean invokeTestSuite(TesterModuleMessenger tmm) throws StateFormulaNotAssignedException;



}

