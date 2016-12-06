<#noautoesc>
package ${package}
import org.junit.Assert;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import tomato.TesterModule.main.ProbabilisticTestingSuite;
import tomato.TesterModule.main.StateFormulaNotAssignedException;
import tomato.TesterModule.main.TesterModuleMessenger;
import tomato.TesterModule.main.Tomato;

public class ${name} {

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

<#list steps as step>
@Given("^\\[${step.regex}\\]$")
public void ${step.name_function}(<#list step.params as par>${par.type} ${par.name}<#if par_has_next>,</#if></#list>) throws Throwable {
		
		<#list step.params as par>
		tmm.insertParameter("${par.name}", ${par.name});
		</#list>
    	
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
</#list>

}
</#noautoesc>