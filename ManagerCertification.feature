Feature: Manager Certification

  @Login
  Scenario: Login into Sailpoint
    Given launch application
    When enter username and password
    Then click on login button
    And validate home page is displayed
    
    @Application-Configuration-Validation
  Scenario: Schedule Manager Certification
    Then click on setup dropdown
    Then select Cetrification from setup drop down
    And validate certification page is displayed
    Then create new manager certification
    And validate schedule certification page is displayed
    Then enter certification name
    And enter certification recipient
    And select run now
    And select appliaction to certify
    And choose access level
    Then click next 
    And select staging period
    And unselect enable revocation period
    And select process revokes immediately	
    Then click next 
    And select supress initial notification
		Then click next 
		Then click next 
		And choose exclusion rule
		Then click schedule certification button
		
		  @Application-Configuration-Validation
  Scenario: Activate Certification
    Then click on setup dropdown
    Then select Cetrification from setup drop down
    And validate certification page is displayed
    Then search for certification created
    And validate certification is staged
    And open the certification
    Then activate the certification
    And validate certification page is displayed
    Then search for certification created
    And validate certification is active

  @Application-Configuration-Validation
  Scenario: Perform Certification and validation
    And open the certification
    Then open access review
    Then validate manager access review page
    Then save first user in access review
    
    # open applications and search for your user
    Then click on applications dropdown
    Then select application type from application drop down
    And validate application definition page is displayed
    Then search for application in application definition page
    Then validate searched application is displayed
    Then click on valid application link
    Then click on accounts tab
    And search for the account
    And validate account is active
    
    
    Then click on setup dropdown
    Then select Cetrification from setup drop down
    And validate certification page is displayed
    Then search for certification created
    And validate certification is active
    And open the certification
    Then open access review
    Then validate manager access review page
    And bulk select all accounts
    And bulk approve all accounts
		And revoke first user
		Then save and confirm desision
		Then click confirm sign off 
		
		# open applications and search for your user
    Then click on applications dropdown
    Then select application type from application drop down
    And validate application definition page is displayed
    Then search for application in application definition page
    Then validate searched application is displayed
    Then click on valid application link
    Then click on accounts tab
    And search for the account
    And validate account is disabled
    
