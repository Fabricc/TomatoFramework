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
//	ParentTestingSuite ptt;
	Scenario scenario;
	TesterModuleMessenger tmm;
	
	@Before("@quality")
	public void before(Scenario scenario){
		this.scenario=scenario;
//		this.ptt = new ParentTestingSuite();
		
		this.ptt = new ProbabilisticTestingSuite();
		ptt.assignStateFormula("something happening", "dostuff");
		ptt.assignStateFormula("something happening", "otherstuff");
		ptt.assignRuleRandom("something happening", "dostuff", "number", 1.0, 10.0);
		
		ptt.assignStateFormula("something else happening", "dostuff");
		ptt.assignRuleRandom("something else happening", "dostuff", "number", 0.5, 1.0);
		
		
		tmm = ptt.getMessenger();
}
	
	@Given("^don't do it$")
	public void noquality() throws Throwable{
		System.out.println("no quality");
        return;
	}
	
	@Given("^I want to do stuff and I insert (\\d+\\.\\d+)$")
	public void dostuff(double number) throws Throwable{
		System.out.println("dostuff with the number "+number);
		Thread.sleep(400);
		
		 //Assert.assertEquals(number, 1, 0);
		
        return;
	}
	
	@Given("^other stuff happens and I insert \"([^\"]*)\"$")
	public void otherstuff(String str) throws Throwable{
		Thread.sleep(400);
		System.out.println("otherstuff with the string "+str);
        return;
	}
	
	@Tomato
	@Given("^\\[with (?:probability|chance) ((at most|at least)|((greater than|higher than)|(lower than|less than))) (0.[0-9]+|1) \"([^\"]*)\" ((within the next|in less than)|(after|in more than)) ([0-9]*.[0-9]+|[0-9]+)\\]$")
	public void alternativeThree(Double p,Double t,String stateFormula) throws Throwable {
	
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^\\[the (?:probability|chance) is that \"([^\"]*)\" ((within the next|in less than)|(after|in more than))\\]$")
    public void alternativeTwo(String stateFormula) throws Throwable {

        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
    
    @Tomato
	@Given("^\\[the (?:probability|chance) (?:of|to|that|in which) \"([^\"]*)\" ((?:within the next|in less than)|(?:after|in more than)) (\\d+\\.\\d+) is ((?:at most|at least)|(?:(?:greater than|higher than)|(?:lower than|less than))) (\\d+\\.\\d+)\\]$")
	public void alternativeOne(String stateFormula, String timeBound, double t, String probabilityBound, double p) throws Throwable {
    	 System.out.println("alternativeOneStarted");
//    	tmm.insertParameter("stateFormula", stateFormula);
//    	tmm.insertLexicalParameter("timeBound", timeBound);
//    	tmm.insertParameter("t", t);
//    	tmm.insertLexicalParameter("probabilityBound", probabilityBound);
//    	tmm.insertParameter("p", p);
    	 
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
    	
    	
        System.out.println("alternativeOneFinished");
    }



}



