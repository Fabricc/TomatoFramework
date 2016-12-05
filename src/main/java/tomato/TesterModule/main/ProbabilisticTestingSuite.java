package tomato.TesterModule.main;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

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
	

	public void executeTesting(String stateFormula, List<Double> executions, List<Boolean> successes) 
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, 
			NoSuchMethodException, SecurityException, InstantiationException, StateFormulaNotAssignedException{
		
		
		
		List<String> steps = super.getStateFormulaSteps(stateFormula);
		if(steps==null) throw new StateFormulaNotAssignedException();
		//stateFormulaImplementation sfi = null;
		//if(steps!=null) userdefined_mode = false;
		//else{
			//sfi = super.getStateFormulaMethod(stateFormula);
			//if(sfi!=null) userdefined_mode = true;
			//throw new StateFormulaNotAssignedException();
		//}
		

		for(int i = 0; i<numberIterations; i++){
			System.out.println("Iteration nr."+ (i+1));
			
			boolean noExceptions = true;
			StopWatch stopwatch = new StopWatch();
			
			for(int j = 0; j<tmm.getClasses().size() && noExceptions; j++){
				Class c = null;
				boolean timed = false;
				
				
				String met = tmm.getMethods().get(j);
				if(steps.contains(met)) timed=true;
				
				tmm.getClassArguments().get(j);
				
				Class[] class_arguments= tmm.getClassArguments().get(j);
				
				Method method = null;
				
					c = Class.forName(tmm.getClasses().get(j));
					method = c.getMethod(met,tmm.getClassArguments().get(j));
				
				
				Object o;
				
					o = c.newInstance();
			
				if(timed){
					try{
						Object obj =tmm.getArguments().get(j);
						
						
							
						if(!stopwatch.isStarted()) stopwatch.start();
						else stopwatch.resume();
						method.invoke(o,tmm.getArguments().get(j));
						//if(userdefined_mode) sfi.execute();
						if(i==tmm.getClasses().size()-1) stopwatch.stop();
						else stopwatch.suspend();
						
					}catch(Exception e){
						stopwatch.stop();
						noExceptions=false;
					}
					
					} else method.invoke(o,tmm.getArguments().get(j));
				}
			
			long milliseconds = stopwatch.getTime();

			double seconds = (double)(milliseconds / 1000.0);
			
			executions.add(i, seconds);
			stopwatch.reset();
			successes.add(i, noExceptions);
		}
	}
	
	public boolean executeTestingUserDefined(stateFormulaImplementation sfi, List<Double> executions, List<Double> successes){
		return true;
	}

	
	
	//{p=3, t=5, timeBound=>, stateFormula=something happening, probabilityBound=<}
	
	@Override
	public boolean invokeTestingSuite(TesterModuleMessenger tmm) throws StateFormulaNotAssignedException{
		
		
		int mode = 0; //1 for method scope, 2 for scenario scope, 3 for ad-hoc method scope
		
		tmm.getParameter("timeBound");
		System.out.print("Tomato Framework =================== Starting execution");
		
		String stateFormula = (String)tmm.getParameter("stateFormula");
		
		List<Double> executions = new ArrayList<Double>(numberIterations); 
		List<Boolean> successes =  new ArrayList<Boolean>(numberIterations);
		
		System.out.println("The number of iterations is "+ numberIterations);
		System.out.println("The stateFormula is "+stateFormula);

		 try {
			this.executeTesting(stateFormula, executions, successes);
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
		} 
		 
		 try {
			return this.verifyRequirement(executions, successes);
		} catch (IncorrectConditionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return false;
		
	}
	

	
	
	//{p=0.5, t=5, timeBound=>, stateFormula=something happening, probabilityBound=<}
	private boolean verifyRequirement(List<Double> executions, List<Boolean> successes) throws IncorrectConditionException{
		System.out.println("Report ProbabilisticTestingSuite Execution");
		boolean[] passed = new boolean[executions.size()];
		
		boolean checkTime = false;
		conditionChecker timeChecker = null;
		double timeConditionValue = 0;
		String timeBound = (String)tmm.getParameter("timeBound");
		if(timeBound!=null) {
			checkTime=true;
			timeChecker = new conditionChecker(timeBound);
			timeConditionValue = (Double)tmm.getParameter("t");
		}
		
		int number_of_passed=0;
		for(int i=0; i<executions.size(); i++){
		if(successes.get(i)){
			System.out.println("Iteration nr."+i+" Time: "+executions.get(i));
			if(checkTime) passed[i]=timeChecker.compare(executions.get(i), timeConditionValue);
			else passed[i]=true;
			
			if(passed[i]) number_of_passed++;
		}
		}
		
		double probabilityConditionValue = (Double)tmm.getParameter("p");
		conditionChecker probabilityChecker = new conditionChecker((String)tmm.getParameter("probabilityBound"));
		
		double executionProbability = number_of_passed/(executions.size());
		return probabilityChecker.compare(probabilityConditionValue,executionProbability);
		
		
		
		
		
		
	}
	
}
		
	


