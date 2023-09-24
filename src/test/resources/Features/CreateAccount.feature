Feature: Loging to MyAccount by clicking on My Account button


  Background:
    #Given user launched the browser and navigate to the URL
    And   user clicked on My Account button
    Then  user clicked on LOGIN button to log in

@LoginPass @regression
  Scenario: Loging in to MyAccount With valid credential

    #Given user launched the browser and navigate to the URL
    #And   user clicked on My Account button
    When  user enters Email Address
    And   user enters  password
    Then  user clicked on LOGIN button to log in

  @Loginfail
  Scenario Outline: Invalid login functionality

    When user enters different "<username>" and "<password>" and verify the "<error>" for it

    Examples:
      | username              | password       | error                        |
      |herrafiisdet@gmail.com |cristiano       |Invalid login or password.    |
      |ronaldo@gmail.com      |Automation2023@ |Invalid login or password.    |

# 1 VALID username INVALID password
# 2 INVALID username VALID password

