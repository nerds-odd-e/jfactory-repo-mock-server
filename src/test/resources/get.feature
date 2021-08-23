Feature: Get request

  Scenario: Get by url only
    Given 存在"Bean":
      | aString     | anInt | aBoolean |
      | stringValue | 101   | true     |
    Then get "/beans" response code is 200 and body as below
    """
    {
      "aString": "stringValue",
      "anInt": 101,
      "aBoolean": true
    }
    """