@CityList
Feature: City module API documentation

  Scenario: Verify user Get citylist through API
    Given User add headers for to get citylist
    When User add request bodu stateid for get city list
    And User send "POST" request for citylist endpoint
    Then User should verify the status code is 200
    And User berify the citylist response message matches "Komarapalayam" and saved city ID

