
@tag1
Feature: Submit Order on Ecommerce Website
 
 Background: 
	Given Land on Ecommerce page 

  @Regression
  Scenario Outline: Positive Testing of submitting order
    Given Logged in with username <username> and password <password>
    When I add product <productNeed> to Cart
    And Checkout <productNeed> and submit the order
    Then "Thankyou for the order." message is displayed on confirmationpage

    Examples: 
      | username 					| password 		 | productNeed   |
      | therokk@gmail.com |     qwerty12 | IPHONE 13 PRO |
   
