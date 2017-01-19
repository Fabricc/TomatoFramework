package testermodule.main;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;



public class ReliabilityTestingSuite extends DefaultTestingSuite {
		

	@Override
	public boolean verifyRequirement(){
		System.out.println("Report ReliabilityTestingSuite Execution");
		reliabilityReport rreport = super.getReliabilityReport();
		String metric = (String)tmm.getParameter("reliabilityMetric");
		String bound = (String)tmm.getParameter("reliabilityBound");
		Double value = (Double)tmm.getParameter("p");
		Double actual_value=0.0;
		System.out.println("The value of the metric "+metric+" should be "+
		bound+" "+tmm.getParameter("p"));
		
		if(metric.equals("rocof")) actual_value = rreport.rocof;
		else if(metric.equals("pofod")) actual_value = rreport.pofod;
		else if(metric.equals("mttf")) actual_value = rreport.mttf;
		System.out.println("The current value is "+actual_value);
		
		conditionChecker checker = new conditionChecker(bound);
		try {
			if(checker.compare(actual_value,value)){
				System.out.println("NFR Satisfied");
				System.out.println();
				System.out.println();
				return true;
			}else{
				System.out.println("NFR Not Satisfied");
				System.out.println();
				System.out.println();
				return false;
			}
		} catch (IncorrectConditionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
