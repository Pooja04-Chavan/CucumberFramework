Feature: Validating Place API's

  @AddPlace @Regression
  Scenario Outline: Verify if place is being successfully added using AddPlace API
    Given Add place payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "post" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"
    Examples:
    |name    |language    |address          |
    |AAhouse |English     |word cross center|
  #  |bbhouse |french      |England          |
  #  |cchuse  |spanish     |America          |

  @DeletePlace @Regression
  Scenario: Verify if delete place functionality is working
    Given Delete Place Payload
    When user calls "deletePlaceAPI" with "post" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
