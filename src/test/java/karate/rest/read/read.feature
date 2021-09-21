Feature: Gets resolved issues, issues per tech, and wait time analytics

  Background:
    * url baseUrl = 'http://localhost:8080'
    * def analyticsBase = '/analysis/'

  Scenario: Get all resolved issues
    Given path analyticsBase + 'resolved'
    When method Get
    Then status 200
    And match response == [{"issueID":"2485fbfd-86d7-4011-b55f-9b6e500b888c","issueTitle":"Setup Issue","openedBy":"d00af75e-7176-4b92-bef0-31bed390741f","closedBy":"d00af75e-7176-4b92-bef0-31bed390741f","openTime":1632151848.000000000,"reviewTime":1632151848.000000000,"closedTime":1632153048.000000000,"inQueue":false},{"issueID":"d03ab884-6515-4e63-90b6-1a9c6076a012","issueTitle":"Setup Issue","openedBy":"d00af75e-7176-4b92-bef0-31bed390741f","closedBy":"d00af75e-7176-4b92-bef0-31bed390741f","openTime":1632151848.000000000,"reviewTime":1632151848.000000000,"closedTime":1632153048.000000000,"inQueue":false}]

  Scenario: Get all resolved issues for one tech
    Given path analyticsBase + 'resolved/d00af75e-7176-4b92-bef0-31bed390741f'
    When method Get
    Then status 200
    And match response == [{"issueID":"2485fbfd-86d7-4011-b55f-9b6e500b888c","issueTitle":"Setup Issue","openedBy":"d00af75e-7176-4b92-bef0-31bed390741f","closedBy":"d00af75e-7176-4b92-bef0-31bed390741f","openTime":1632151848.000000000,"reviewTime":1632151848.000000000,"closedTime":1632153048.000000000,"inQueue":false},{"issueID":"d03ab884-6515-4e63-90b6-1a9c6076a012","issueTitle":"Setup Issue","openedBy":"d00af75e-7176-4b92-bef0-31bed390741f","closedBy":"d00af75e-7176-4b92-bef0-31bed390741f","openTime":1632151848.000000000,"reviewTime":1632151848.000000000,"closedTime":1632153048.000000000,"inQueue":false}]

  Scenario: Get average wait time
    Given path analyticsBase + 'wait'
    When method Get
    Then status 200
    And match response == '0'

  Scenario: Get average time to resolution
    Given path analyticsBase + 'resolveTime'
    When method Get
    Then status 200
    And match response == '20'