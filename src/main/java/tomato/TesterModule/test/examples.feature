@quality
Feature: Examples 
  A list of example scenario to test the capabilities Tomato 
  
#Performance scenario with stateFormula's scope of one step and one paramater
	Scenario: API testing
		When I GET /api/riaf/reference/countries
    Then the JsonPath key countries should contain 249 elements
    Then [the probability that "JsonPath key is received" in less than 0.6 s is at least 0.5]

#Reliability scenario with stateFormula's scope of two steps and two paramaters
	Scenario: Error recovery
		Given an user issued 3 concurrent tasks
		And the software has 3 backup services
		When a fatal error occurs
		Then the software recovers automatically
		And all the tasks are resumed
		And ["the system recovers completely" in less than 3.0 s with a probability of at least 0.9]
	
		
#Availability scenario with stateFormula's scope of one step and three paramaters
	Scenario: Video streaming testing
	 Given the user wants to open 3 video streams
	 When the connectivity is 10 mb/s
	 And 3 media servers are available
	 Then the system reduces the QoS 
	 And [with chance of at least 0.90 "QOS reaches the minimum" in more than 5 s]
		
#Security scenario with two stateFormulas
	Scenario: Protection from IP spoofing attack
		Given an user wants to login in the website
		And an hacker tries to identify the ip of the customer with ip spoofing attacks
		And the hacker uses a botnet with 3 terminals
		Then the system activates the protection against ip spoofing attack
		Then [the probability in which "the user’s IP is detected" is less than 0.1]