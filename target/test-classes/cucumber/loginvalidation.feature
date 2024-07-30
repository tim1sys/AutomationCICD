
@tag
Feature: Error Validation
  I want to use this template for my feature file


  @ErrorValidation
  Scenario Outline: Title of your scenario outline
  Given Land on Ecommerce page 
  And Logged in with username <username> and password <password>
    Then Verify "Incorrect email or password." is displayed

    Examples: 
       | username 				| password 		|
      | therokk@gmail.com |     qwerty12s |
