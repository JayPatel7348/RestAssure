Feature: API Testing with Rest Assured and Cucumber


#  Scenario: Get user details
#    Given I have the API endpoint "https://reqres.in/api/users/2"
#    When I send a GET request to the endpoint
#    Then the response status should be 200
#    And the response body should contain "Janet"

  @login
  Scenario: Successful login
    Given I have the login API endpoint "/user/login"
    And I have the request body with email "jay.patel@elaunchinfotech.in" and password "Test@123"
    When I send a POST request to the endpoint
    Then the response status should be 201
    And the response body should contain "access_token" and "refresh_token"

  @login
  Scenario: Failure login
    Given I have the login API endpoint "/user/login"
    And I have the request body with email "jay.patel987@elaunchinfotech.in" and password "Test@123"
    When I send a POST request to the endpoint
    Then the response status should be 400
    And the response body should contain "access_token" and "refresh_token"


  @multipleInput
  Scenario Outline: Successful login with multiple input <email>,<password>
    Given I have the login API endpoint "/user/login"
    And I have the request body with <email>,<password>
    When I send a POST request to the endpoint
    Then the response status should be 201
    And the response body should contain "access_token" and "refresh_token"


    Examples:
      | email                        | password |
      | jay.patel@elaunchinfotech.in | Test@123 |
      | jay.patel1@elaunchinfotech.in | 987654321 |
      | jay.patel3@elaunchinfotech.in |        |
      | jay.patel@elaunchinfotech.in |          |
      | jay.p@elaunchinfotech.in     | Test@123 |
      | jay.p@elaunchinfotech        | Test@123 |
      | jay.patel @elaunchinfotech        | Test@123 |