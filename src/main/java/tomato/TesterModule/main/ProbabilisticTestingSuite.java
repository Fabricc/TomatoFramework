package tomato.TesterModule.main;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class ProbabilisticTestingSuite extends ParentTestingSuite {
	
	private int numberIterations = 30;

	
	Map<String, String> stateFormulaMethodsMap;
	
	public void assignStateFormula(String stateFormula, Callable<Boolean> method){
		  
	}
	
	public void assignStateFormula(String stateFormula, String method){
		if(stateFormulaMethodsMap == null) stateFormulaMethodsMap = new HashMap<String, String>();
		stateFormulaMethodsMap.put(stateFormula, method);
		
	}
	//{p=3, t=5, timeBound=>, stateFormula=something happening, probabilityBound=<}
	
	@Override
	public void invokeTestingSuite(TesterModuleMessenger tmm){
		super.invokeTestingSuite(tmm);
		
		int mode = 0; //1 for method scope, 2 for scenario scope, 3 for ad-hoc method scope
		
		tmm.getParameter("timeBound");
		System.out.print("Tomato Framework =================== Starting execution");
		
		String stateFormula = (String)tmm.getParameter("stateFormula");
		String stateFormulaMethod = null;
		
		if(stateFormula != null) mode=1;
		
		if(mode==1) stateFormulaMethod = stateFormulaMethodsMap.get(stateFormula);
		
		double[] executions = new double[numberIterations]; 
		boolean[] successes = new boolean[numberIterations];
		System.out.println("The number of iterations is "+ numberIterations);
		System.out.println("The stateFormula is "+stateFormula);
		
		for(int i = 0; i<numberIterations; i++){
			System.out.println("Iteration nr."+ (i+1));
			
			long startTime = System.nanoTime();
			
			for(int j = 0; j<tmm.getClasses().size(); j++){
				Class c = null;
				
					
			
				
				String met = tmm.getMethods().get(j);
				
				tmm.getClassArguments().get(j);
				
				Class[] class_arguments= tmm.getClassArguments().get(j);
				
				Method method = null;
				try {
					c = Class.forName(tmm.getClasses().get(j));
					method = c.getMethod(met,tmm.getClassArguments().get(j));
				
				
				Object o;
				
					o = c.newInstance();
			
				if(met.equals(stateFormulaMethod)){
					
					
					try{
					Object obj =tmm.getArguments().get(j);
					method.invoke(o,tmm.getArguments().get(j));
					long endTime = System.nanoTime();
					executions[i] = (endTime - startTime)/1000000000.0;  //divide by 1000000 to get milliseconds.
					
					successes[i]=true;
					}catch(Exception e){
						successes[i]=false;
					}
					
				} else method.invoke(o,tmm.getArguments().get(j));
				
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchMethodException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SecurityException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			
		}
		       
			

			
		}
		
		for(int i=0; i<executions.length; i++){
			System.out.println(executions[i]);
			System.out.println(successes[i]);
			
		}
		
	}
	
	private class conditionChecker{
		conditionChecker(String condition){
			if(condition.equals("<")){
				this.mode=0;
			}else if(condition.equals(">")){
			this.mode=1;
			}
		}
		
		private int mode;
	}
	
	
	//{p=3, t=5, timeBound=>, stateFormula=something happening, probabilityBound=<}
	private void verifyRequirement(double[] executions, boolean[] successes){
		boolean[] passed = new boolean[executions.length];
		
	}
	
}
		
	


