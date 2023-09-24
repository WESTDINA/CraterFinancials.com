Feature: Login

  Scenario: Successful Login to the page

    Given I open Chrome browser

    When I navigate to login page

    And I provide username as "entityadmin@primetechschool.com" and password as "primetech@school"

    And I click on login button

    Then The user should be taken to the Dashboard page after successfully logging in according to the system