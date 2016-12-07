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

@Given("^\\[with (?:((?:(?: a)?(?: a)?)?(\\d+\\.\\d+)(?:(?: a)?(?: a)?)?most|(?:(?: a)?(?: a)?)?(\\d+\\.\\d+)(?:(?: a)?(?: a)?)?le(?:(?: a)?(?: a)?)?st)[(?:(?: a)?(?: a)?)?(?:(?: a)?(?: a)?)?(?:(?: a)?(?: a)?)?(?:(?: a)?(?: a)?)?]|[(?:(?: a)?(?: a)?)?(?:(?: a)?(?: a)?)?(?:(?: a)?(?: a)?)?(?:(?: a)?(?: a)?)?](?:(?: a)?(?: a)?)?th(?:(?: a)?(?: a)?)?nBound) (\\d+\\.\\d+) (?:probability|chance) \"([^\"]*)\" ((?:within the next|in less than) (\d+\.\d+)|(?:after|in more than) (\d+\.\d+)) (\\d+\\.\\d+)\\]$")
public void alternativeFive(java.lang.String atBound,java.lang.String thanBound,java.lang.Double p,java.lang.String stateFormula,java.lang.String timeBound,java.lang.Double t) throws Throwable {
		
		tmm.insertParameter("atBound", atBound);
		tmm.insertParameter("thanBound", thanBound);
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

}
