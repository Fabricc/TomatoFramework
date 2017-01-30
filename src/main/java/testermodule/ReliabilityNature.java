package testermodule;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import testermodule.exceptions.IncorrectConditionException;
import testermodule.exceptions.StateFormulaNotAssignedException;
import testermodule.exceptions.verificationAlreadyExecutedException;
import testermodule.support.IterationReport;
import testermodule.support.conditionChecker;
import testermodule.support.reliabilityReport;

public class ReliabilityNature extends  DefaultTestingSuiteDecorator{

	private ReliabilityNature(DefaultTestingSuite decoratedDefaultTestingSuite) {
			super(decoratedDefaultTestingSuite);
		}
	
	public static DefaultTestingSuite safelyDecore(DefaultTestingSuite decoratedDefaultTestingSuite){
		if(!decoratedDefaultTestingSuite.getDecorations().contains("Reliability")){
			return new ReliabilityNature(decoratedDefaultTestingSuite);
		}else return decoratedDefaultTestingSuite;
	}
	
	private reliabilityReport rreport;
	private Boolean requestedReliabilityReport = false;
	
	public void enableReliabilityReport(){
		this.requestedReliabilityReport=true;
	}
	
	
//	@Override  
//	public boolean verifyRequirement()
//			throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
//		return super.verifyRequirement();
//	}
	
	@Override
	public boolean verifyRequirement() throws verificationAlreadyExecutedException{
		int nature = super.getMessenger().getNature();
		if(nature==-1) throw new verificationAlreadyExecutedException();
		else if (nature!=1) return super.verifyRequirement();
		else super.getMessenger().defineNature(-1);
		
		System.out.println("Report ReliabilityTestingSuite Execution");
		reliabilityReport rreport = this.getReliabilityReport();
		TesterModuleMessenger tmm = super.getMessenger();
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
	
	private void printReliabilityReport(){
		reliabilityReport report = getReliabilityReport();
		System.out.println("<<Opening Reliability Overview>>");
		
		if(report.totalFailings==0) {
			System.out.println("Scenario executed without failures");
			System.out.println("<<Closing Reliability Overview>>");
			return;
		}
		
		//Calculation Probability Of Failure On Demand(POFOD)
		System.out.println("Probability Of Failure On Demand(POFOD): "+report.pofod+" out of 1");
		
		//Calculation Rate Of Occurrence Of Failure(ROCOF)
		System.out.println("Rate Of Occurrence Of Failure(ROCOF): "+report.rocof+" failures per second");
		
		//Calculation Mean Time To Failure
		if(report.totalFailings>1) {
			System.out.println("Mean Time To Failure(MTTF): "+report.mttf+" seconds");
		}
		System.out.println("<<Closing Reliability Overview>>");
		
	}
	
	public reliabilityReport elaborateReliabilityReport(){
		reliabilityReport result = new reliabilityReport();
		List<IterationReport> report = super.getReport();
		double totalExecutions = report.size();
		double totalFailings = 0;
		double totalExecutionTime = 0;
		double mttf = 0;
		List<Double> ttfs = new LinkedList<Double>();
		double timeToFailure=0;
		
		for(IterationReport ir:report){
			timeToFailure+=ir.getTotalExecutionTime();
			if(!ir.isCorrectlyExecuted()) {
				totalFailings++;
				ttfs.add(timeToFailure);
				timeToFailure=0;
			}
			totalExecutionTime += ir.getTotalExecutionTime();
			
		}
		
		result.totalFailings=totalFailings;
		//Calculation Probability Of Failure On Demand(POFOD)
		result.pofod = totalFailings/totalExecutions;
		
		//Calculation Rate Of Occurrence Of Failure(ROCOF)
		result.rocof = totalFailings/totalExecutionTime;
		
		//Calculation Mean Time To Failure
		if(totalFailings>1) {
			for(Double d: ttfs){
			mttf+=d;
			}
			mttf=mttf/ttfs.size();
			result.mttf=mttf;
		}
		return result;
	}
	

	
	protected reliabilityReport getReliabilityReport(){
		if(rreport==null){
			rreport=this.elaborateReliabilityReport();
		}
		
		return rreport;
	}

	public boolean invokeTestingSuite(TesterModuleMessenger tmm) throws StateFormulaNotAssignedException {
		super.executeTesting();
		
		boolean result=false;
		try {
			result = this.verifyRequirement();
		} catch (verificationAlreadyExecutedException e) {
			e.printStackTrace();
		}
		
		if(this.requestedReliabilityReport) this.printReliabilityReport();
		return result;
	}
	
	public String getNatureDescription(){
		return "Reliability";
	}
	
	@Override
	public List<String> getDecorations(){
		List<String> list = super.getDecorations();
		list.add(this.getNatureDescription());
		return list;
	}



}
