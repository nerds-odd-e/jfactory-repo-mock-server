Feature: Post request

  Scenario: Post by url only and response json object
    Given Exists api data "PostBean":
      | someString  | someInt | someBoolean |
      | stringValue | 101     | true        |
    Then Post "/beans" response code is 200 and body as below
    """
    {
      "someString": "stringValue",
      "someInt": 101,
      "someBoolean": true
    }
    """
