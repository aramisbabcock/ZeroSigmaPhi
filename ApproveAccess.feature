Feature: Access Request Framework

  @Login
  Scenario: Login into Sailpoint
    Given launch application
    Then click on agree button
    When enter username and password
    Then click on login button
    And validate home page is displayed
 
  @Request-For-Access
  Scenario: Request to approve access for a user
    Then click on quickinks
    Then open Manage User Tasks
    #Then click on Approve All button
    Then click on complete access button
  #  And validate manage user access page is displayed
   # Then search for user
   # And validate user in search result
   # Then click on next
   # And choose add or remove option
   # Then Search and click on entitlement
   # And click on next
   #  Then submit access request
   #  Then click on complete form
   #  And enter phonecomm
   #  And enter rank
   #  And enter base
    # Then click on ok form button
    # And enter manager
   # And enter security manager
  #  Then click on Submit form button
     
  # Then click on comment button
  # And enter a comment and save   
  # Then  input user mail address
   # Then click on ok button
     
     
     #And enter phonecomm
    #And enter rank
    #And enter base
    #Then click ok
    #And enter security manager
    #Then click submit
    #Then click approve all
    #Then click complete
    #Then click approve all
    #Then click complete
    #Then submit access request
    #And save access request id for further validation  
#
#	
#		Then manager and role owner approve request
		
 
  #@Login
  #Scenario: Login into Sailpoint
    #Given launch application
    #When enter username and password
    #Then click on login button
    #And validate home page is displayed
    #
#
  #Scenario: Validate Request
    #Then click on identities
    #Then click on identity warehouse
    #And validate identity warehouse page is displayed
    #Then search user in identity cube
    #Then click on valid identity
    #And validate identity page is displayed
    #Then click on entitlements tab
    #Then validate access present on account
    
   