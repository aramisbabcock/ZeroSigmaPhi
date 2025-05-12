Feature: Application Definition and Aggrigation

  @Login
  Scenario: Login into Sailpoint
    Given launch application
    When enter username and password
    Then click on login button
    And validate home page is displayed

  #@Application-Configuration-Validation
  #Scenario: Validate Application Configuration
    #Then click on applications dropdown
    #Then select application type from application drop down
    #And validate application definition page is displayed
    #Then search for application in application definition page
    #Then validate searched application is displayed
    #Then click on valid application link
    #And validate application name on application page is displayed correctly
    #And validate application type on application page is displayed correctly
    #And validate application owner on application page is displayed correctly
    #Then click on configuration tab
    #Then click on test connection button
    #And validate if test connection is successful

  #Scenario: Validate Application Preview Table
    #Then click on schema tab
    #Then click on preview button
    #And validate preview table is displayed
    #And close preview table

#Scenario: Search For Task
    #Then click on setup link
    #Then select tasks link from dropdown
    #Then click on task tab
    #And validate task page is displayed
    #Then search task in tasks tab
    #And validate task appeared in search table

  #Scenario: Execute The  Task
    #Then click on task link
    #And validate edit task is displayed
    #Then execute task and validate task message is displayed
    #Then click on ok button
    #Then click on task results tab
    #Then search the task in task results tab
    #And validate task is successful
  
  