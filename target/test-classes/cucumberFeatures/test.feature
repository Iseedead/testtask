Feature: GitHub SignUp

  Background: Home Page
    Given User is on Sign Up page

  Scenario: Sign Up with Invalid Data
    When User enters Invalid Password to Sign Up Fields
      | password  | error                                                                         |
      | asd1      | Password is too short (minimum is 7 characters)                               |
      | asdasdasd | Password needs at least one number                                            |
      | asd       | Password is too short (minimum is 7 characters) and needs at least one number |
      | asd       | Password is too short (minimum is 7 characters) and needs at least one number |
    And Press the Create an account button
#    Then Error Message "There were problems creating your account." is displayed

#  Scenario: Sign Up with Valid Data
#    When User enters Valid Data to Sign Up Fields
#    And Press the Create an account button
#    Then Welcome Message "Welcome to GitHub" is Displayed

