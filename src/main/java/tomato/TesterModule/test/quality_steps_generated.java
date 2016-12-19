package tomato.TesterModule.test;
import org.junit.Assert;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
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
		
		//Scenario: Message system
		//Scope stateFormula
		ptt.assignStateFormula("process b should receive the message", "the_process_A_sends_the_message_to_B");
//		ptt.assignStateFormulaExternal("the message is corrupted", "a_message_is_corrupted");
		ptt.assignStateFormula("the message is corrupted", "a_message_is_corrupted");
		//Rules
		ptt.assignRuleRandom("process b should receive the message", "a_processes_are_sending_a_message_in_the_same_channel_at_kb_s", "procs", 1, 10);
		ptt.assignRuleRandom("process b should receive the message", "a_processes_are_sending_a_message_in_the_same_channel_at_kb_s", "speed", 1, 50);
		ptt.assignRuleRandom("process b should receive the message", "the_noise_in_th_channel_is_db", "noise", 1, 4);
		
		//Scenario: Message system
		//Scope stateFormula
		//ptt.assignStateFormulaExternal("the IP of the user is detected", "a_message_is_corrupted");
		ptt.assignStateFormula("the IP of the user is detected", "spoofing_succesful");
		//Rules
		ptt.assignRuleRandom("the IP of the user is detected", "the_hacker_uses_a_botnet_with_terminals", "terminals", 1, 10);

				
		
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
	    			System.out.println("Quality constraint not satisfied");
	    			Assert.fail("Quality constraint not satisfied");
	    		}
	    	}
	    	catch(StateFormulaNotAssignedException e){
	    		System.out.println("StateFormula not assigned");
	    		Assert.fail("Quality constraint not satisfied");
	    	}finally{
	    		tmm.cleanParameters();
	    		System.out.println();
	    		System.out.println();
	    		System.out.println();
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
	    			System.out.println("Quality constraint not satisfied");
	    			Assert.fail("Quality constraint not satisfied");
	    		}
	    	}
	    	catch(StateFormulaNotAssignedException e){
	    		System.out.println("StateFormula not assigned");
	    		Assert.fail("Quality constraint not satisfied");
	    	}finally{
	    		tmm.cleanParameters();
	    		System.out.println();
	    		System.out.println();
	    		System.out.println();
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
	    			System.out.println("Quality constraint not satisfied");
	    			Assert.fail("Quality constraint not satisfied");
	    		}
	    	}
	    	catch(StateFormulaNotAssignedException e){
	    		System.out.println("StateFormula not assigned");
	    		Assert.fail("Quality constraint not satisfied");
	    	}finally{
	    		tmm.cleanParameters();
	    		System.out.println();
	    		System.out.println();
	    		System.out.println();
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
	    			System.out.println("Quality constraint not satisfied");
	    			Assert.fail("Quality constraint not satisfied");
	    		}
	    	}
	    	catch(StateFormulaNotAssignedException e){
	    		System.out.println("StateFormula not assigned");
	    		Assert.fail("Quality constraint not satisfied");
	    	}finally{
	    		tmm.cleanParameters();
	    		System.out.println();
	    		System.out.println();
	    		System.out.println();
	    	}
	    	
	    }
	
	@Given("^\\[the (?:probability|chance) (?:of|to|that|in which) \"([^\"]*)\" is ((?:at most|(?:lower than|less than))|(?:at least|(?:greater than|higher than))) (\\d+\\.\\d+)\\]$")
	public void alternativeOneNoTime(java.lang.String stateFormula,java.lang.String probabilityBound,java.lang.Double p) throws Throwable {
			
			tmm.insertParameter("stateFormula", stateFormula);
			tmm.insertParameter("probabilityBound", probabilityBound);
			tmm.insertParameter("p", p);
	    	
			try{
	    		if(this.ptt.invokeTestingSuite(tmm)) System.out.println("Quality constraint satisfied");
	    		else {
	    			System.out.println("Quality constraint not satisfied");
	    			Assert.fail("Quality constraint not satisfied");
	    		}
	    	}
	    	catch(StateFormulaNotAssignedException e){
	    		System.out.println("StateFormula not assigned");
	    		Assert.fail("Quality constraint not satisfied");
	    	}finally{
	    		tmm.cleanParameters();
	    		System.out.println();
	    		System.out.println();
	    		System.out.println();
	    	}
	    	
	    }
	
	@After("@quality")
	public void after(){
		
	}
	
	

	}
