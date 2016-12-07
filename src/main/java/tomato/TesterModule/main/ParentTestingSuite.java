package tomato.TesterModule.main;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public abstract class ParentTestingSuite {
	TesterModuleMessenger tmm;
	
	protected Object dependency;
	protected String dclass;
	
	public ParentTestingSuite(){
		this.tmm = new TesterModuleMessenger();
	}
	
	public TesterModuleMessenger getMessenger() {
		
		return this.tmm;
	}
	
	private Map<String, List<String>> stateFormulaStepsMap;
	private Map<String, stateFormulaImplementation> stateFormulaMethodsMap;
	private List<RuleTuple> rules;
	
	public void assignStateFormula(String stateFormula, stateFormulaImplementation method){
		if(stateFormulaMethodsMap == null) stateFormulaMethodsMap = new HashMap<String,stateFormulaImplementation>();
		stateFormulaMethodsMap.put(stateFormula, method);
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
		dependency=o;
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
		
		if(found){
		System.out.println("The method "+method+" will take the following arguments:");
		for(int i=0; i<modified_arguments.length; i++){
			System.out.println("Name: "+names[i]+" value:"+modified_arguments[i]);
		}}
		return modified_arguments;
		
	}
	
	
	
	
	
	protected List<String> getStateFormulaSteps(String stateFormula){
		return stateFormulaStepsMap.get(stateFormula);
	}
	
	protected stateFormulaImplementation getStateFormulaMethod(String stateFormula){
		return stateFormulaMethodsMap.get(stateFormula);
	}

	public abstract boolean invokeTestingSuite(TesterModuleMessenger tmm) throws StateFormulaNotAssignedException;



		
//		try{
//			
//			for(int i = 0; i<tmm.getClasses().size(); i++){
//				Class c = Class.forName(tmm.getClasses().get(i));
//				
//				String met = tmm.getMethods().get(i);
//				
//				tmm.getClassArguments().get(i);
//				
//				Class[] class_arguments= tmm.getClassArguments().get(i);
//				
////				for(int j = 0; j<class_arguments.length; j++){
////					if(class_arguments[j].equals(Integer.class)){
////						class_arguments[j] = int.class;
////					}
////				}
////				
//				
//				Method method = c.getMethod(met,tmm.getClassArguments().get(i));
//				
//				Object o = c.newInstance();
//		        method.invoke(o,tmm.getArguments().get(i));
//		        
//		       
//		        
//			}
//		
//		
//
//		
//		} catch (InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		} catch (NoSuchMethodException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	

	

}

