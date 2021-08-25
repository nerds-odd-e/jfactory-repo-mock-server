Feature: Get request

  Scenario: Get by url only and response json object
    Given Exists api data "Bean":
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

  Scenario: Get by url only and response json array
    Given Exists api data "BeanForArray":
      | someString  | someInt | someBoolean |
      | stringValue | 101     | true        |
    Then Get "/beans" response code is 200 and body as below
    """
    [
      {
        "someString": "stringValue",
        "someInt": 101,
        "someBoolean": true
      }
    ]
    """

#  Scenario: Get by url and params
#    Given Exists data "Bean" with params "foo=bar"
#      | someString  |
#      | stringValue |
#    Then Get "/beans" response code is 404
#    Then Get "/beans?foo=bar" response code is 200 and body as below
#    """
#    {
#      "someString": "stringValue",
#      "someInt": 1,
#      "someBoolean": true
#    }
#    """
