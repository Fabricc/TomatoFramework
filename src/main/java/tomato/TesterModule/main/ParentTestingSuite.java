package tomato.TesterModule.main;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public abstract class ParentTestingSuite {
	TesterModuleMessenger tmm;
	
	public ParentTestingSuite(){
		this.tmm = new TesterModuleMessenger();
	}
	
	public TesterModuleMessenger getMessenger() {
		
		return this.tmm;
	}
	
	private Map<String, List<String>> stateFormulaStepsMap;
	private Map<String, stateFormulaImplementation> stateFormulaMethodsMap;
	
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

