@test
Feature: Quality testing
  This feature shows you can define perfomance constraints through probabilistic expression

@quality
  Scenario: Execute quality testing
  	Given I want to do stuff and I insert 1
  	When other stuff happens and I insert "hello"
    Then [the probability of "something happening" after 5 is at least 3]
    
	Scenario: I don't execute quality testing
		Then don't do it
