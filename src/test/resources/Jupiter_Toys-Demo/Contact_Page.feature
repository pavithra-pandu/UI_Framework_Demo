Feature: This is a feature file to test contact page of the Jupiter Toys web application

Background:
    Given launch Jupiter_Toys web application 
      
  Scenario: TC1_verify sucessfull submission of contact details
    Given Jupiter_Toys web application landing page
    When I click on "contactTab"
    And verify contact page is present
    And I enter all required details
        | Forename  | Jai                |
        | Surname   | Prashanth          |
        | Email     | jp@gmail.com       |
        | Telephone | '459088824'        |
        | Feedback  | Am a happy customer|
    When I click on "Submit"
    Then validate message with given name "Jai"
    And check "Back_button" is present
    
 
  Scenario: TC2_verify error messages
    Given Jupiter_Toys web application landing page
    When I click on "contactTab"
    And verify contact page is present
    When I click on "Submit"
    Then I validate incomplete feedback message
    	"""
    	We welcome your feedback - but we won't get it unless you complete the form correctly.
    	"""
    Then I validate below error messages in their respective fields
        | Forename_er  | Forename is required |
        | Email_er     | Email is required    |
        | Feedback_er  | Message is required  |        