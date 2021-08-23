Feature: Get request

  Scenario: Get by url only
    Given 存在"Bean":
      | someString  | someInt | someBoolean |
      | stringValue | 101     | true        |
    Then get "/beans" response code is 200 and body as below
    """
    {
      "someString": "stringValue",
      "someInt": 101,
      "someBoolean": true
    }
    """