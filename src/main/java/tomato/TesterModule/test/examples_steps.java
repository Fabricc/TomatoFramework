package tomato.TesterModule.test;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import tomato.TesterModule.main.DefaultTestingSuite;
import tomato.TesterModule.main.TesterModuleMessenger;

public class examples_steps {
	
	public shared_data st;
	
	public examples_steps(shared_data st){
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
	
	

	@Given("^a process A that wants to communicate with process B$")
	public void a_process_A_that_wants_to_communicate_with_process_B() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println("a_process_A_that_wants_to_communicate_with_process_B executed");
	}

	@Given("^a (\\d+) processes are sending a message in the same channel at (\\d+\\.\\d+) kb/s$")
	public void a_processes_are_sending_a_message_in_the_same_channel_at_kb_s(int procs, double speed) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    this.st.processess=procs;
	    this.st.speed=speed;
	    System.out.println("a_processes_are_sending_a_message_in_the_same_channel_at_kb_s executed with "
	    		+procs+" processes and communication rate of"+speed+" kb/s");
	}

	@Given("^the noise in th channel is (\\d+) db$")
	public void the_noise_in_th_channel_is_db(int noise) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    this.st.noise=noise;
	    System.out.println("the_noise_in_th_channel_is_db executed with noise "+noise+" dB");
	}

	@When("^the process A sends the message to B$")
	public void the_process_A_sends_the_message_to_B() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		long time = 100;
		long sleep= time*st.processess*st.noise+(st.noise*100);
		
		Thread.sleep(sleep);
		
		System.out.println("the_process_A_sends_the_message_to_B executed");
	}
	
	public boolean a_message_is_corrupted() throws Throwable {
		if(this.st.sleep>1000) return true;
		return false;
	}
	
	public boolean spoofing_succesful(){
		if(this.st.bots>5) return true;
		else return false;
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
	public void the_hacker_uses_a_botnet_with_terminals(int terminals) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		this.st.bots=terminals;
		Thread.sleep(200*this.st.bots);
	    System.out.println("the_hacker_uses_a_botnet_with_terminals executed with "+terminals+" terminals");
	}

	@Then("^the system activates the protection against ip spoofing attack$")
	public void the_system_activates_the_protection_against_ip_spoofing_attack() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println("the_system_activates_the_protection_against_ip_spoofing_attack executed");
	}
	
	@Given("^the client requests (\\d+) files of (\\d+) mb each to the server$")
	public void the_client_requests_files_of_mb_each_to_the_server(int file, int size) throws Throwable {
		Thread.sleep(400);
		System.out.println("the_client_requests_files_of_mb_each_to_the_server executed");
	}


	@Then("^the client elaborates the file$")
	public void the_client_elaborates_the_file() throws Throwable {
		Thread.sleep(800);
		System.out.println("the_client_elaborates_the_file executed");
	}

}
