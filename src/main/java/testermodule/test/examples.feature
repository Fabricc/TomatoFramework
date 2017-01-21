@quality
Feature: Examples 
  A list of example scenario to test the capabilities Tomato 
  
#Performance scenario with stateFormula's scope of one step and one paramater
	Scenario: API testing
		When I GET /api/riaf/reference/countries
    Then the JsonPath key countries should contain 249 elements
    Then [the probability that "JsonPath key is received" in less than 0.5 s is at least 0.2]
    And [rocof of "the whole scenario" should be lower than 0.5]
    

#Availability scenario with stateFormula's scope of two steps and two paramaters
	Scenario: Error recovery
		Given an user issued 3 concurrent tasks
		And the software has 3 backup services
		When a fatal error occurs
		Then the software recovers automatically
		And all the tasks are resumed
		And ["the system recovers completely" in less than 0.5 s with a probability of at least 0.9]
		
#Security scenario with one stateFormula with external scope and one paramater
	Scenario: Protection from IP spoofing attack
		Given an user wants to login in the website
		And an hacker tries to identify the ip of the customer with ip spoofing attacks
		And the hacker uses a botnet with 3 terminals
		Then the system activates the protection against ip spoofing attack
		Then [the probability in which "the IP of the user is detected" is less than 0.1]
		
		
#Performance and reliability scenario with two stateFormula with scope a single step and external and three paramaters
	Scenario: Message system
		Given a process A that wants to communicate with process B
		And a 2 processes are sending a message in the same channel at 2.0 kb/s
		And the noise in th channel is 1 db 
		When the process A sends the message to B
		Then ["process b should receive the message" within the next 3.0 s with a probability of at least 0.1]
		Then [the probability in which "the message is corrupted" is less than 0.6]
		

	Scenario: WebServer Response
		Given the client requests 3 files of 10 mb each to the server
		Then ["the webserver responds" within the next 3.0 s with a probability of at least 0.9]
		And the client elaborates the file
		And ["output is produced" in less than 0.5 s with a probability of at least 0.9]
		
