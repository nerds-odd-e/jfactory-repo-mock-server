Feature: Get/Post/Put request

  Scenario Outline: <method> by url only and response json object without setting data with table
    Given Exists 1 api data "<factory>"
    Then <method> "/beans" response code is 200 and body as below
    """
    {
      "someString": "someString#1",
      "someInt": 1,
      "someBoolean": true
    }
    """
    Examples:
      | method | factory  |
      | GET    | Bean     |
      | POST   | PostBean |
      | PUT    | PutBean  |

  Scenario Outline: <method> by url only and response json object
    Given Exists api data "<factory>":
      | someString  | someInt | someBoolean |
      | stringValue | 101     | true        |
    Then <method> "/beans" response code is 200 and body as below
    """
    {
      "someString": "stringValue",
      "someInt": 101,
      "someBoolean": true
    }
    """
    Examples:
      | method | factory  |
      | GET    | Bean     |
      | POST   | PostBean |
      | PUT    | PutBean  |

  Scenario Outline: <method> by url only and response json array without setting data with table
    Given Exists 1 api data "<factory>"
    Then <method> "/beans" response code is 200 and body as below
    """
    [
      {
        "someString": "someString#1",
        "someInt": 1,
        "someBoolean": true
      }
    ]
    """
    Examples:
      | method | factory          |
      | GET    | BeanForArray     |
      | POST   | PostBeanForArray |
      | PUT    | PutBeanForArray  |

  Scenario Outline: <method> by url only and response json array
    Given Exists api data "<factory>":
      | someString  | someInt | someBoolean |
      | stringValue | 101     | true        |
    Then <method> "/beans" response code is 200 and body as below
    """
    [
      {
        "someString": "stringValue",
        "someInt": 101,
        "someBoolean": true
      }
    ]
    """
    Examples:
      | method | factory          |
      | GET    | BeanForArray     |
      | POST   | PostBeanForArray |
      | PUT    | PutBeanForArray  |

  Scenario Outline: <method> by url and params
    Given Exists api data "<factory>" with params "foo=bar&name=value1&name=value2":
      | someString  | someInt | someBoolean |
      | stringValue | 102     | false       |
    Then <method> "/beans" response code is 404
    Then <method> "/beans?foo=bar" response code is 404
    Then <method> "/beans?foo=bar&name=value1" response code is 404
    Then <method> "/beans?foo=bar&name=value1&name=value2" response code is 200 and body as below
    """
    {
      "someString": "stringValue",
      "someInt": 102,
      "someBoolean": false
    }
    """
    Examples:
      | method | factory  |
      | GET    | Bean     |
      | POST   | PostBean |
      | PUT    | PutBean  |

  Scenario Outline: Params only impact related step
    Given Exists api data "<factory>" with params "foo=bar":
      | someString  | someInt | someBoolean |
      | stringValue | 102     | false       |
    And Exists api data "<factory>":
      | someString  | someInt | someBoolean |
      | stringValue | 101     | true        |
    Then <method> "/beans" response code is 200 and body as below
    """
    {
      "someString": "stringValue",
      "someInt": 101,
      "someBoolean": true
    }
    """
    Examples:
      | method | factory  |
      | GET    | Bean     |
      | POST   | PostBean |
      | PUT    | PutBean  |

  Scenario Outline: <method> by url with child object
    Given Exists api data "<factory>":
      | someString  | someInt | someBoolean | child.yaString |
      | stringValue | 101     | true        | childValue     |
    Then <method> "/beansWithChild" response code is 200 and body as below
    """
    {
      "someString": "stringValue",
      "someInt": 101,
      "someBoolean": true,
      "child": {
        "yaString": "childValue"
      }
    }
    """
    Examples:
      | method | factory           |
      | GET    | BeanWithChild     |
      | POST   | PostBeanWithChild |
      | PUT    | PutBeanWithChild  |

  Scenario Outline: <method> by url with params and child object
    Given Exists api data "<factory>" with params "foo=bar&name=value1&name=value2":
      | someString  | someInt | someBoolean | child.yaString |
      | stringValue | 102     | false       | childValue     |
    Then <method> "/beansWithChild?foo=bar&name=value1&name=value2" response code is 200 and body as below
    """
    {
      "someString": "stringValue",
      "someInt": 102,
      "someBoolean": false,
      "child": {
        "yaString": "childValue"
      }
    }
    """
    Examples:
      | method | factory           |
      | GET    | BeanWithChild     |
      | POST   | PostBeanWithChild |
      | PUT    | PutBeanWithChild  |

  Scenario Outline: <method> by url and path variables
    Given Exists api data "<factory>" with path variables "foo=bar":
      | someString  | someInt | someBoolean |
      | stringValue | 102     | false       |
    Then <method> "/beans" response code is 404
    Then <method> "/beans/bar" response code is 200 and body as below
    """
    {
      "someString": "stringValue",
      "someInt": 102,
      "someBoolean": false
    }
    """
    Examples:
      | method | factory                  |
      | GET    | BeanWithPathVariable     |
      | POST   | PostBeanWithPathVariable |
      | PUT    | PutBeanWithPathVariable  |

  Scenario Outline: <method> by url and two path variables
    Given Exists api data "<factory>" with path variables "foo=bar&name=value":
      | someString  | someInt | someBoolean |
      | stringValue | 102     | false       |
    Then <method> "/beans" response code is 404
    Then <method> "/beans/bar/another/value" response code is 200 and body as below
    """
    {
      "someString": "stringValue",
      "someInt": 102,
      "someBoolean": false
    }
    """
    Examples:
      | method | factory                      |
      | GET    | BeanWithTwoPathVariables     |
      | POST   | PostBeanWithTwoPathVariables |
      | PUT    | PutBeanWithTwoPathVariables  |
