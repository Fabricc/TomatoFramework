package tomato.TesterModule.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import tomato.ParserModule.Pair;
import tomato.TesterModule.main.ParentTestingSuite;
import tomato.TesterModule.main.ProbabilisticTestingSuite;
import tomato.TesterModule.main.TesterModuleMessenger;
import tomato.TesterModule.main.Tomato;
import tomato.support.LexicalParametersSerializer;

import static org.junit.Assert.assertEquals;

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
		
		
		tmm = ptt.getMessenger();
}
	
	@Given("^don't do it$")
	public void noquality() throws Throwable{
		System.out.println("no quality");
        return;
	}
	
	@Given("^I want to do stuff and I insert (\\d)$")
	public void dostuff(int number) throws Throwable{
		System.out.println("dostuff with the number "+number);
		Thread.sleep(400);
		 assertEquals(number, 1);
		
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
	@Given("^\\[the (?:probability|chance) (?:of|to|that|in which) \"([^\"]*)\" ((?:within the next|in less than)|(?:after|in more than)) (\\d+) is ((?:at most|at least)|(?:(?:greater than|higher than)|(?:lower than|less than))) (\\d+)\\]$")
	public void alternativeOne(String stateFormula, String timeBound, int t, String probabilityBound, int p) throws Throwable {
	   
    	tmm.insertParameter("stateFormula", stateFormula);
    	tmm.insertLexicalParameter("timeBound", timeBound);
    	tmm.insertParameter("t", t);
    	tmm.insertLexicalParameter("probabilityBound", probabilityBound);
    	tmm.insertParameter("p", p);
    	this.ptt.invokeTestingSuite(tmm);

        // Write code here that turns the phrase above into concrete actions
        System.out.println("alternativeOneFinished");
        return;
    }



}



