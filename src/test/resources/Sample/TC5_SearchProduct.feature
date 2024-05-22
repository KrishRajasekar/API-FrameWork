Feature: SearchProduct module API documentation

  @search
  Scenario: Verify add user address to the application through API
    Given User add headers for searching the product
    When User add request body to search "nuts"
    And User send "POST" request for search product endpoint
    Then User should verify the status code is 200
    And User should verify Searchproduct response message matches "OK"
