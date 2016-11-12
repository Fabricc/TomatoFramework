Feature: Perfomance testing of scenarios defined in files .feature
  This feature shows you can define perfomance constraints through probabilistic expression

  Background:
    Given I defined a scenario

  Scenario: Define scenario performance constraints
    Given I want to define performance constraints
    When I execute the scenario
    Then I want to display its execution time