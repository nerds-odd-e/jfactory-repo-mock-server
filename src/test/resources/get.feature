Feature: Get request

  Scenario: Get by url only
    Given Exists data "Bean":
      | someString  | someInt | someBoolean |
      | stringValue | 101     | true        |
    Then Get "/beans" response code is 200 and body as below
    """
    {
      "someString": "stringValue",
      "someInt": 101,
      "someBoolean": true
    }
    """