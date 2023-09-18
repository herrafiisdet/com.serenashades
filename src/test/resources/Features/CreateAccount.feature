Feature: Loging to MyAccount by clicking on My Account button


  Background:
    Given user launched the browser and navigate to the URL
    And   user clicked on My Account button
    Then  user clicked on LOGIN button to log in

@LoginPass
  Scenario: Loging in to MyAccount With valid credential

    #Given user launched the browser and navigate to the URL
    #And   user clicked on My Account button
    When  user enters Email Address
    And   user enters  password
    Then  user clicked on LOGIN button to log in

  @Loginfail
  Scenario Outline: Loging in to MyAccount With correct Email and Wrong Password

    When  user enters valid email "<EmailAddress>"
    And   user enters  invalid "<password>"
    #Then  user clicked on LOGIN button to log in

  Examples:
    | EmailAddress          | password       |
    | herrafiisdet@gmail.com | WrongPassword1 |


