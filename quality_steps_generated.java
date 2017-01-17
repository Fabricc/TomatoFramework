package ParserModule
import org.junit.Assert;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import tomato.TesterModule.main.ProbabilisticTestingSuite;
import tomato.TesterModule.main.StateFormulaNotAssignedException;
import tomato.TesterModule.main.TesterModuleMessenger;
import tomato.TesterModule.main.Tomato;

public class quality_steps_generated {

	ProbabilisticTestingSuite ptt;
	Scenario scenario;
	TesterModuleMessenger tmm;
	
	@Before("@quality")
	public void before(Scenario scenario){
		this.scenario=scenario;
		this.ptt = new ProbabilisticTestingSuite();
		
		//Assign your StateFormulas and you rules here
		
		tmm = ptt.getMessenger();
}

@Given("^\\[with(?: a)? (?:probability|chance)(?: of)? ((?:at most|(?:lower than|less than))|(?:at least|(?:greater than|higher than))) (\\d+\\.\\d+) \"([^\"]*)\" ((?:within the next|in less than)|(?:after|in more than)) (\\d+\\.\\d+) s\\]$")
public void alternativeThree(java.lang.String probabilityBound,java.lang.Double p,java.lang.String stateFormula,java.lang.String timeBound,java.lang.Double t) throws Throwable {
		
		tmm.insertParameter("probabilityBound", probabilityBound);
		tmm.insertParameter("p", p);
		tmm.insertParameter("stateFormula", stateFormula);
		tmm.insertParameter("timeBound", timeBound);
		tmm.insertParameter("t", t);
    	
    	try{
    		if(this.ptt.invokeTestingSuite(tmm)) System.out.println("Quality constraint satisfied");
    		else Assert.fail("Quality constraint not satisfied");
    	}
    	catch(StateFormulaNotAssignedException e){
    		System.err.println("StateFormula not assigned");
    		Assert.fail("Quality constraint not satisfied");
    	}catch(Exception e){
    		e.printStackTrace();
    		Assert.fail("Quality constraint not satisfied");
    		
    	}
    	
    }
@Given("^\\[((?:rocof|rate of occurrence of failures)|(?:pofod|probability of failures on demand)|(?:mttf|mean time to failure)) (?:of|to|that|in which) \"([^\"]*)\" should be ((?:(?:lower than|less than))|(?:(?:greater than|higher than))) (\\d+\\.\\d+)\\]$")
public void reliabilityConstraint(java.lang.String reliabilityMetric,java.lang.String stateFormula,java.lang.String reliabilityBound,java.lang.Double p) throws Throwable {
		
		tmm.insertParameter("reliabilityMetric", reliabilityMetric);
		tmm.insertParameter("stateFormula", stateFormula);
		tmm.insertParameter("reliabilityBound", reliabilityBound);
		tmm.insertParameter("p", p);
    	
    	try{
    		if(this.ptt.invokeTestingSuite(tmm)) System.out.println("Quality constraint satisfied");
    		else Assert.fail("Quality constraint not satisfied");
    	}
    	catch(StateFormulaNotAssignedException e){
    		System.err.println("StateFormula not assigned");
    		Assert.fail("Quality constraint not satisfied");
    	}catch(Exception e){
    		e.printStackTrace();
    		Assert.fail("Quality constraint not satisfied");
    		
    	}
    	
    }
@Given("^\\[the (?:probability|chance) is ((?:at most|(?:lower than|less than))|(?:at least|(?:greater than|higher than))) (\\d+\\.\\d+) that \"([^\"]*)\" ((?:within the next|in less than)|(?:after|in more than)) (\\d+\\.\\d+) s\\]$")
public void alternativeTwo(java.lang.String probabilityBound,java.lang.Double p,java.lang.String stateFormula,java.lang.String timeBound,java.lang.Double t) throws Throwable {
		
		tmm.insertParameter("probabilityBound", probabilityBound);
		tmm.insertParameter("p", p);
		tmm.insertParameter("stateFormula", stateFormula);
		tmm.insertParameter("timeBound", timeBound);
		tmm.insertParameter("t", t);
    	
    	try{
    		if(this.ptt.invokeTestingSuite(tmm)) System.out.println("Quality constraint satisfied");
    		else Assert.fail("Quality constraint not satisfied");
    	}
    	catch(StateFormulaNotAssignedException e){
    		System.err.println("StateFormula not assigned");
    		Assert.fail("Quality constraint not satisfied");
    	}catch(Exception e){
    		e.printStackTrace();
    		Assert.fail("Quality constraint not satisfied");
    		
    	}
    	
    }
@Given("^\\[\"([^\"]*)\" ((?:within the next|in less than)|(?:after|in more than)) (\\d+\\.\\d+) s with(?: a)? (?:probability|chance)(?: of)? ((?:at most|(?:lower than|less than))|(?:at least|(?:greater than|higher than))) (\\d+\\.\\d+)\\]$")
public void alternativeNine(java.lang.String stateFormula,java.lang.String timeBound,java.lang.Double t,java.lang.String probabilityBound,java.lang.Double p) throws Throwable {
		
		tmm.insertParameter("stateFormula", stateFormula);
		tmm.insertParameter("timeBound", timeBound);
		tmm.insertParameter("t", t);
		tmm.insertParameter("probabilityBound", probabilityBound);
		tmm.insertParameter("p", p);
    	
    	try{
    		if(this.ptt.invokeTestingSuite(tmm)) System.out.println("Quality constraint satisfied");
    		else Assert.fail("Quality constraint not satisfied");
    	}
    	catch(StateFormulaNotAssignedException e){
    		System.err.println("StateFormula not assigned");
    		Assert.fail("Quality constraint not satisfied");
    	}catch(Exception e){
    		e.printStackTrace();
    		Assert.fail("Quality constraint not satisfied");
    		
    	}
    	
    }
@Given("^\\[the (?:probability|chance) (?:of|to|that|in which) \"([^\"]*)\" ((?:within the next|in less than)|(?:after|in more than)) (\\d+\\.\\d+) s is ((?:at most|(?:lower than|less than))|(?:at least|(?:greater than|higher than))) (\\d+\\.\\d+)\\]$")
public void alternativeOne(java.lang.String stateFormula,java.lang.String timeBound,java.lang.Double t,java.lang.String probabilityBound,java.lang.Double p) throws Throwable {
		
		tmm.insertParameter("stateFormula", stateFormula);
		tmm.insertParameter("timeBound", timeBound);
		tmm.insertParameter("t", t);
		tmm.insertParameter("probabilityBound", probabilityBound);
		tmm.insertParameter("p", p);
    	
    	try{
    		if(this.ptt.invokeTestingSuite(tmm)) System.out.println("Quality constraint satisfied");
    		else Assert.fail("Quality constraint not satisfied");
    	}
    	catch(StateFormulaNotAssignedException e){
    		System.err.println("StateFormula not assigned");
    		Assert.fail("Quality constraint not satisfied");
    	}catch(Exception e){
    		e.printStackTrace();
    		Assert.fail("Quality constraint not satisfied");
    		
    	}
    	
    }

}
