Feature: Get/Post/Put request

  Rule: Get request

    Scenario: verify GET request with query parameters
      When GET "/api?name=John&place=Amsterdam&place=Shanghai"
      Then all api data "GetRequest" should be:
    """
    queryParams[]: [{
        name: [John]
        place: [Amsterdam, Shanghai]
    }]
    """

    Scenario: verify GET request with path variables
      When GET "/api/path-variables/orderNum001/shippingLabel001"
      Then all api data "GetRequestWithPV" should be:
    """
    pathVariables[]: [{
        orderNum: orderNum001
        shippingLabel: shippingLabel001
    }]
    """

    Scenario: verify GET request with path variables and query parameters
      When GET "/api/path-variables/orderNum001/shippingLabel001?name=John&place=Shanghai"
      Then all api data "GetRequestWithPV" should be:
    """
    : {
      queryParams[]: [{
          name: [John]
          place: [Shanghai]
      }]
      pathVariables[]: [{
          orderNum: orderNum001
          shippingLabel: shippingLabel001
      }]
    }
    """

  Rule: Post and Put request

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

    Scenario Outline: verify <method> request with path variables
      When <method> "/api/path-variables/orderNum001/shippingLabel001":
    """
    {}
    """
      Then all api data "<spec>" should be:
    """
    pathVariables[]: [{
        orderNum: orderNum001
        shippingLabel: shippingLabel001
    }]
    """
      Examples:
        | method | spec              |
        | POST   | PostRequestWithPV |
        | PUT    | PutRequestWithPV  |

    Scenario Outline: verify <method> request with path variables and query parameters
      When <method> "/api/path-variables/orderNum001/shippingLabel001?name=John&place=Shanghai":
    """
    {}
    """
      Then all api data "<spec>" should be:
    """
    : {
      queryParams[]: [{
          name: [John]
          place: [Shanghai]
      }]
      pathVariables[]: [{
          orderNum: orderNum001
          shippingLabel: shippingLabel001
      }]
    }
    """
      Examples:
        | method | spec              |
        | POST   | PostRequestWithPV |
        | PUT    | PutRequestWithPV  |

