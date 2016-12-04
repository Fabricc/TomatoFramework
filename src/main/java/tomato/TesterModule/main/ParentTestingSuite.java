package tomato.TesterModule.main;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ParentTestingSuite {
	TesterModuleMessenger tmm;
	
	public ParentTestingSuite(){
		this.tmm = new TesterModuleMessenger();
	}
	
	public TesterModuleMessenger getMessenger() {
		
		return this.tmm;
	}

	public void invokeTestingSuite(TesterModuleMessenger tmm) {
		this.tmm = tmm;
		
		
		System.out.println("insideExecuteTest");
		
		try{
			
			for(int i = 0; i<tmm.getClasses().size(); i++){
				Class c = Class.forName(tmm.getClasses().get(i));
				
				String met = tmm.getMethods().get(i);
				
				tmm.getClassArguments().get(i);
				
				Class[] class_arguments= tmm.getClassArguments().get(i);
				
//				for(int j = 0; j<class_arguments.length; j++){
//					if(class_arguments[j].equals(Integer.class)){
//						class_arguments[j] = int.class;
//					}
//				}
//				
				
				Method method = c.getMethod(met,tmm.getClassArguments().get(i));
				
				Object o = c.newInstance();
		        method.invoke(o,tmm.getArguments().get(i));
		        
		       
		        
			}
		
		

		
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
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	

}

