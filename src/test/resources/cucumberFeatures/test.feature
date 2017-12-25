Feature: GitHub SignUp

    Background: Home Page
        Given User is on Sign Up page

    Scenario: Sign Up with Invalid Data
        When User enters Invalid Username to Sign Up Field
            | value    | error                                                                                                      |
            | _asd     | Username may only contain alphanumeric characters or single hyphens, and cannot begin or end with a hyphen |
            | iseedead | Username is already taken                                                                                  |
        And User enters Invalid Email to Sign Up Field
            | value      | error                             |
            | asd1       | Email is invalid or already taken |
            | asd@asd.   | Email is invalid or already taken |
            | @@@@@@     | Email is invalid or already taken |
            | @gmail.com | Email is invalid or already taken |
        And User enters Invalid Password to Sign Up Field
            | value     | error                                                                         |
            | asd1      | Password is too short (minimum is 7 characters)                               |
            | asdasdasd | Password needs at least one number                                            |
            | asd       | Password is too short (minimum is 7 characters) and needs at least one number |
            | ________  | Password needs at least one lowercase letter and needs at least one number    |
            | ________1 | Password needs at least one lowercase letter                                  |
        And Press the Create an account button
    Then Error Message "There were problems creating your account." is displayed

  Scenario: Sign Up with Valid Data
    When User enters Valid Data to Sign Up Fields
    And Press the Create an account button
    Then Welcome Message "Welcome to GitHub" is Displayed

