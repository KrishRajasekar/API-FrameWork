Feature: Address module API documentation

  @Address
  Scenario Outline: Verify add user address to the application through API
    Given User add headers and bearer authorization for accesing adddress endpoints
    When User add requestbody for AddNewAddress "<first_name>","<last_name>","<mobile>","<apartment>","<state>","<city>","<country>","<zipcode>","<address>","<address_type>"
    And User send "POST" request for adduseraddress endpoint
    Then User should verify the status code is 200
    And User should verify adduseraddress response message matches "Address added successfully"

    Examples: 
      | first_name | last_name | mobile     | apartment | state | city | country | zipcode | address       | address_type |
      | Raja       | Sekar     | 8760836156 | apartment |    35 | 3857 |     101 |  638183 | Komarapalayam | Home         |

  @Address
  Scenario Outline: Verify UpdateUserAddress to the application through API
    Given User add headers and bearer authorization for accesing Updateadddress endpoints
    When User add requestbody for UpdateNewAddress "<AddAddressId>","<first_name>","<last_name>","<mobile>","<apartment>","<state>","<city>","<country>","<zipcode>","<address>","<address_type>"
    And User send "PUT" request for AddUserAddress endpoint
    Then User should verify the status code is 200
    Then User should verify updateaddress response message matches "Address updated successfully"

    Examples: 
      | first_name | last_name | mobile     | apartment | state | city | country | zipcode | address | address_type |
      | Dharma     | Prakash   | 6358248225 | apartment |    35 | 3857 |     101 |  638183 | Chennai | Home         |

  @Address
  Scenario Outline: Verify GetUserAddress to the application through API
    Given User add headers and bearer authorization for accesing Getadddress endpoints
    When User send "GET" request for GetUserAddress endpoint
    Then User should verify the status code is 200
    Then User should verify getuseraddress response message matches "OK"

  @Address
  Scenario Outline: Verify DeleteUserAddress to the application through API
    Given User add headers and bearer authorization for accesing Deleteadddress endpoints
    When User add request body for address ID
    And User send "DELETE" request for DeleteAddress endpoint
    Then User should verify the status code is 200
    Then User should verify DeleteAddress response message matches "Address deleted successfully"
