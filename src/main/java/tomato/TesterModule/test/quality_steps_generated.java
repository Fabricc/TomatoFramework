package tomato.TesterModule.test;
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
		
		ptt.declareDependency("tomato.TesterModule.test.examples_steps",new services_and_tasks());
		
		//Assign your StateFormulas and you rules here
		
		//Scenario: API testing
		//Scope stateFormula
		ptt.assignStateFormula("JsonPath key is received", "the_JsonPath_key_countries_should_contain_elements");
		//Rules
		ptt.assignRuleRandom("JsonPath key is received", "the_JsonPath_key_countries_should_contain_elements", "arg1", 100, 300);
		
		//Scenario: Error recovery
		//Scope stateFormula
		ptt.assignStateFormula("the system recovers completely", "the_software_recovers_automatically");
		ptt.assignStateFormula("the system recovers completely", "all_the_tasks_are_resumed");
		//Rules
		ptt.assignRuleRandom("the system recovers completely", "an_user_issued_concurrent_tasks", "tasks", 1, 10);
		ptt.assignRuleRandom("the system recovers completely", "the_software_has_backup_services", "services", 1, 10);
		
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
	    		else {
	    			System.err.println("Quality constraint not satisfied");
	    			Assert.fail("Quality constraint not satisfied");
	    		}
	    	}
	    	catch(StateFormulaNotAssignedException e){
	    		System.err.println("StateFormula not assigned");
	    		Assert.fail("Quality constraint not satisfied");
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		Assert.fail("Configuration Error");
	    		
	    	}finally{
	    		tmm.cleanAll();
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
	    		else {
	    			System.err.println("Quality constraint not satisfied");
	    			Assert.fail("Quality constraint not satisfied");
	    		}
	    	}
	    	catch(StateFormulaNotAssignedException e){
	    		System.err.println("StateFormula not assigned");
	    		Assert.fail("Quality constraint not satisfied");
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		Assert.fail("Configuration Error");
	    		
	    	}finally{
	    		tmm.cleanAll();
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
	    		else {
	    			System.err.println("Quality constraint not satisfied");
	    			Assert.fail("Configuration Error");
	    		}
	    	}
	    	catch(StateFormulaNotAssignedException e){
	    		System.err.println("StateFormula not assigned");
	    		Assert.fail("Quality constraint not satisfied");
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		Assert.fail("Configuration Error");
	    		
	    	}finally{
	    		tmm.cleanAll();
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
	    		else {
	    			System.err.println("Quality constraint not satisfied");
	    			Assert.fail("Quality constraint not satisfied");
	    		}
	    	}
	    	catch(StateFormulaNotAssignedException e){
	    		System.err.println("StateFormula not assigned");
	    		Assert.fail("Quality constraint not satisfied");
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		Assert.fail("Configuration Error");
	    		
	    	}finally{
	    		tmm.cleanAll();
	    	}
	    	
	    }

	}
