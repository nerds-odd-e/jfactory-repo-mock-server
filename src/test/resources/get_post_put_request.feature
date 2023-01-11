Feature: Get/Post/Put request

  Scenario: verify GET request with query parameters
    When GET "/api?name=John&place=Amsterdam&place=Shanghai"
    Then all api data "GetRequest" should be:
    """
    queryParams[]: [{
        name: [John]
        place: [Amsterdam, Shanghai]
    }]
    """

  Scenario Outline: verify <method> request with query parameters
    When <method> "/api?name=John&place=Amsterdam&place=Shanghai":
    """
    {}
    """
    Then all api data "<spec>" should be:
    """
    queryParams[]: [{
        name: [John]
        place: [Amsterdam, Shanghai]
    }]
    """
    Examples:
      | method | spec        |
      | POST   | PostRequest |
      | PUT    | PutRequest  |
