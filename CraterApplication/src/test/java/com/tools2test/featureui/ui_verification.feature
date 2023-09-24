Feature: Crater App UI Component Verification

  Scenario: Navigate to Crater App Login Page
    Given I open Chrome browser
    When I navigate to login page
    Then User should be directed to the Crater login page

  Scenario: Verify Page Title
    Given I open Chrome browser
    When I navigate to login page
    Then Page title should be Crater

  Scenario: Verify Email Textbox
    Given I open Chrome browser
    When I navigate to login page
    Then User should see the Email Textbox input
  Scenario: Verify Password Input
    Given I open Chrome browser
    When I navigate to login page
    Then User should see the Password input

  Scenario: Verify Forgot Password Link
    Given I open Chrome browser
    When I navigate to login page
    Then User should see the "forgot password" link under password input field

  Scenario: Verify Heading 2
    Given I open Chrome browser
    When I navigate to login page
    Then User should see Heading 2 located below Heading 1 with the text "Crater helps you track expenses, record payments & generate beautiful invoices & estimates."
