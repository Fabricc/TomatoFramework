package tomato.TesterModule.test;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import tomato.TesterModule.main.ProbabilisticTestingSuite;
import tomato.TesterModule.main.TesterModuleMessenger;

public class examples_steps {
	
	public services_and_tasks st;
	
	public examples_steps(services_and_tasks st){
		this.st=st;
	}

	
//	Scenario scenario;
//	
//	@Before("@quality")
//	public void before(Scenario scenario){
//		this.scenario=scenario;
//}
//	
	//Performance
	
	@When("^I GET /api/riaf/reference/countries$")
	public void i_GET_api_riaf_reference_countries() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	   System.out.println("i_GET_api_riaf_reference_countries executed");
	}

	@Then("^the JsonPath key countries should contain (\\d+) elements$")
	public void the_JsonPath_key_countries_should_contain_elements(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    if(arg1<200) Thread.sleep(400);
	    else Thread.sleep(800);
	    System.out.println("the_JsonPath_key_countries_should_contain_elements executed");
	}
	
	//Reliability

	
	@Given("^an user issued (\\d+) concurrent tasks$")
	public void an_user_issued_concurrent_tasks(int tasks) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		this.st.tasks=tasks;
		System.out.println("an_user_issed_concurrent_tasks executed with "+tasks+" tasks");
	}
	
	
	@Given("^the software has (\\d+) backup services$")
	public void the_software_has_backup_services(int services) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		this.st.services=services;
		System.out.println("the_software_has_backup_services executed with "+services+" services");
	}

	@When("^a fatal error occurs$")
	public void a_fatal_error_occurs() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println("a_fatal_error_occurs executed");
	}

	@Then("^the software recovers automatically$")
	public void the_software_recovers_automatically() throws Throwable {
		
		Thread.sleep(4000/this.st.services);
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println("the_software_recovers_automatically executed with "+this.st.services+" services");
	}

	@Then("^all the tasks are resumed$")
	public void all_the_tasks_are_resumed() throws Throwable {
	
		Thread.sleep(400*this.st.tasks);
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println("all_the_tasks_are_resumed executed with "+this.st.tasks+" tasks");
	}

	
	//Availability


	@Given("^the user wants to open (\\d+) video streams$")
	public void the_user_wants_to_open_video_streams(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println("the_user_wants_to_open_video_streams executed");
	}

	@When("^the connectivity is (\\d+) mb/s$")
	public void the_connectivity_is_mb_s(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println("the_connectivity_is_mb_s executed");
	}

	@When("^(\\d+) media servers are available$")
	public void media_servers_are_available(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println("media_servers_are_available executed");
	}

	@Then("^the system reduces the QoS at the minimum level$")
	public void the_system_reduces_the_QoS_at_the_minimum_level() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println("the_system_reduces_the_QoS_at_the_minimum_level executed");
	}

	//Security
	
	@Given("^an user wants to login in the website$")
	public void an_user_wants_to_login_in_the_website() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println("an_user_wants_to_login_in_the_website executed");
	}

	@Given("^an hacker tries to identify the ip of the customer with ip spoofing attacks$")
	public void an_hacker_tries_to_identify_the_ip_of_the_customer_with_ip_spoofing_attacks() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println("an_hacker_tries_to_identify_the_ip_of_the_customer_with_ip_spoofing_attacks executed");
	}

	@Given("^the hacker uses a botnet with (\\d+) terminals$")
	public void the_hacker_uses_a_botnet_with_terminals(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println("the_hacker_uses_a_botnet_with_terminals executed");
	}

	@Then("^the system activates the protection against ip spoofing attack$")
	public void the_system_activates_the_protection_against_ip_spoofing_attack() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println("the_system_activates_the_protection_against_ip_spoofing_attack executed");
	}

}
