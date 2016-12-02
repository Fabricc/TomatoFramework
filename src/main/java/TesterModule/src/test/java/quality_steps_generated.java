package TesterModule.src.test.java;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class quality_steps_generated {
	Scenario scenario;
	
	@Before
	public void before(Scenario scenario){
		this.scenario=scenario;
	}
	
	@Given("^don't do it$")
	public void noquality() throws Throwable{
		System.out.println("no quality");
        return;
	}
	
	@Given("^I want to do stuff$")
	public void dostuff() throws Throwable{
		System.out.println("dostuff");
        return;
	}
	
	@Given("^other stuff happens$")
	public void otherstuff() throws Throwable{
		System.out.println("otherstuff");
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
@Given("^\\[the (?:probability|chance) (?:of|to|that|in which) \"([^\"]*)\" (?:(?:within the next|in less than)|(?:after|in more than)) (\\d+) is (?:(?:at most|at least)|(?:(?:greater than|higher than)|(?:lower than|less than))) (\\d+)\\]$")
public void alternativeOne(String stateFormula, int p,int t) throws Throwable {
	   TesterModuleMessenger tmm = new TesterModuleMessenger();
	   
	   TesterModule.executeTests(tmm);

        // Write code here that turns the phrase above into concrete actions
        System.out.println("alternativeOneFinished");
        return;
    }

}
