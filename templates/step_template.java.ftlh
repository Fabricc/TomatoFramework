<#noautoesc>
package ${package}
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;

public class ${name} {

<#list steps as step>
@Given("^\\[${step.regex}\\]$")
public void ${step.name_function}() throws Throwable {

        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
</#list>

}
</#noautoesc>