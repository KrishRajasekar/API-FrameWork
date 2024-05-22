Feature: ChangeProfilePicture module API documentation
@Changeprofilepic
  Scenario: Verify Change Profile Pic to the application through API
    Given User add headers and bearer authorization for accesing ChangeProfilePic endpoints
    When User addformData and initialize the file location
    And User send "POST" request for change profile picture endpoint
    Then User should verify the status code is 200
    And User verify the Change Profile Picture response message matches with "Profile updated Successfully"
