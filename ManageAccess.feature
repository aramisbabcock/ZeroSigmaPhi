Feature: Access Request Framework

  #Scenario: Login into Sailpoint
  #@Login
  @Regression-Testing
  Scenario: Sigma Zero Run
    Given launch application
    Then click on agree button
    When enter username and password
    Then click on login button
    And validate home page is displayed
    #Then check identity cube pre
    Then identity Refresh
    Then click on quickinks
    Then open Manage User Access
    And validate manage user access page is displayed
    Then open Manage Accounts
    #And validate manage accounts page is displayed
    Then search for user
    And validate user in search result
    Then click on manage
    Then click on delete disable enable
    Then approve delete disable enable
    #All this below is for app flow
    Then click on next
    Then Search and click on entitlement
    Then click on next
    #Then enter comments
    Then submit access request
    Then check for violations
    Then click on complete form
    And enter attributes
    And Same if applicable
    And enter manager
    And enter security manager
    Then click on Submit form button
    Then Cancel if applicable
    Then Approval Process
