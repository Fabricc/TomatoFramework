package TesterModule.src.test.java;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TesterModule {

	public static void executeTests(TesterModuleMessenger tmm) {
		System.out.println("insideExecuteTest");
		
		System.out.println(tmm.getClasses().get(0));
		
		try{
		
		Class c = Class.forName(tmm.getClasses().get(0));
		

		Method method = c.getMethod(tmm.getMethods().get(0));
		
		Object o = c.newInstance();
        method.invoke(o);
        
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getCause());
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
