package Parser;

/**
 * Created by UA06NP on 01/11/2016.
 */

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;

//        alternativeOne :== 'the' probability of stateFormula timeBound 'is' probabilityBound
//        alternativeTwo :== 'the' probability 'is' probabilityBound 'that' stateFormula timeBound
//        alternativeThree :== 'with' ['a'] probability ['of'] probabilityBound stateFormula timeBound
//        alternativeFour :== 'there is' ['a'] probability ['of'] probabilityBound of stateFormula timeBound
//        alternativeFive :== 'with' aProbabilityBound probability stateFormula timeBound
//        alternativeSix :== 'there is' aProbabilityBound probability of stateFormula timeBound
//        alternativeSeven :== stateFormula timeBound ['in'] probabilityBound ofTheTime
//        alternativeEight :== 'in' probabilityBound ofTheTime stateFormula timeBound
//        alternativeNine :== stateFormula timeBound 'with' ['a'] probability ['of'] probabilityBound
//
//
//        timeBound :== upperTimeBound | lowerTimeBound | timeInterval | noTimeBound
//        upperTimeBound :== ('within the next' | 'in less than' | 'in at most' | 'within' | 'in' | 'before') t 􀗐 R􀮵􀬴 timeUnits
//        lowerTimeBound :== ('after' | 'in more than' | 'in at least') t 􀗐 R􀮵􀬴 timeUnits
//        timeInterval :== 'between' t 􀗐 R􀮵􀬴 'and' t 􀗐 R􀮵􀬴 timeUnits
//        noTimeBound :== ''
//        timeUnits :== 'time units' | 'time steps'

public class quality_steps {

    //public String string1 = "^\\[the (probability|chance) of (\\w+) is (\\w+) (\\d+|\\d+[.]\\d+)\\]%";

    //public final String string = string1;

    @Given("^\\[the (probability|chance) of (\\w+) is (\\w+) (\\d+|\\d+[.]\\d+)\\]%")
    public void alternativeOne() throws Throwable {

        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }


    public static void main(String[] args) {
        // write your code here
        return;
    }

}