Feature: Access Request Framework

  @Login
  Scenario: Login into Sailpoint
    Given launch application
    Then click on agree button
    When enter username and password
    Then click on login button
    And validate home page is displayed
 
  @Request-For-Access
  Scenario: Request to access for a user
    Then click on quickinks
    Then open Manage User Access
     And validate manage accounts page is displayed
