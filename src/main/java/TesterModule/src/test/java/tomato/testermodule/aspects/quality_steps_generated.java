package tomato.testermodule.aspects;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class quality_steps_generated {

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

        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

}
