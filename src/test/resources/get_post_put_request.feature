Feature: Get/Post/Put request

  Scenario: verify GET request with query parameters
    When GET "/api?name=John&place=Amsterdam"
    Then all api data "GetRequest" should be:
    """
    queryParams[]: [{
        name: [John]
        place: [Amsterdam]
    }]
    """

