Feature: Login Action

  Scenario Outline: Unsuccessful Login with InValid Credentials
    Given User is on Home Page
    When User Navigate to LogIn Page
    And User enters "<username>" and "<password>"
    Then Error "<message>" is shown
    Examples:
      |username  |password |message |
      |Test      |test1    |ERROR   |

