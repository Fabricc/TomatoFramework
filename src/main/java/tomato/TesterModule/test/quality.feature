Feature: Quality testing
  This feature shows you can define perfomance constraints through probabilistic expression

@quality
  Scenario: Execute quality testing
  	Given I want to do stuff and I insert 1.0
  	When other stuff happens and I insert "hello"
    Then [the probability of "something happening" in less than 1.0 is at most 0.5]
    And [the probability of "something else happening" after 0.9 is at most 0.5]
    
	Scenario: I don't execute quality testing
		Then don't do it
