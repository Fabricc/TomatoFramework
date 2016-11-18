<#noautoesc>
package ${package}
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;

public class ${name} {

<#list steps as step>
@Given("^\\[${step.regex}\\]$")
public void ${step.name_function}(<#list step.params as par>${par.type} ${par.name}<#if par_has_next>,</#if></#list>) throws Throwable {

        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
</#list>

}
</#noautoesc>