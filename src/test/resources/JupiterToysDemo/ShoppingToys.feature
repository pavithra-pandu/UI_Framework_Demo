Feature: This is a feature file to test shopping page of the Jupiter Toys web application

Background:
    Given launch Jupiter_Toys web application 
     @shop 
  Scenario: TC1_verify sucessfull submission of contact details
    Given Jupiter_Toys web application landing page
    When I click on "shopTab"
    And I add few items in cart
         | Stuffed_Frog   | 2 |
         | Fluffy_Bunny   | 5 |
         | Valentine_Bear | 3 |
    And Verify the price for each product
    And Verify the subtotal for each product
    And verify total is sum of subtotals