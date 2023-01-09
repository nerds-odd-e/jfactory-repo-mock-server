Feature: Get/Post/Put request

  Scenario: GET by url only and response json object without setting data with table
    Given Exists 1 api data "Bean"
    Then "/beans" should response:
    """
    : {
      code: 200
      body.json= {
        "someString": "someString#1",
        "someInt": 1,
        "someBoolean": true
      }
    }
    """

  Scenario Outline: <method> by url only and response json object without setting data with table
    Given Exists 1 api data "<factory>"
    When <method> "/beans":
    """
    {}
    """
    Then response should be:
    """
    : {
      code: 200
      body.json= {
        "someString": "someString#1",
        "someInt": 1,
        "someBoolean": true
      }
    }
    """
    Examples:
      | method | factory  |
      | POST   | PostBean |
      | PUT    | PutBean  |

  Scenario: GET by url only and response json object
    Given Exists api data "Bean":
      | someString  | someInt | someBoolean |
      | stringValue | 101     | true        |
    When "/beans" should response:
    """
    : {
      code: 200
      body.json= {
        "someString": "stringValue",
        "someInt": 101,
        "someBoolean": true
      }
    }
    """

  Scenario Outline: <method> by url only and response json object
    Given Exists api data "<factory>":
      | someString  | someInt | someBoolean |
      | stringValue | 101     | true        |
    When <method> "/beans":
    """
    {}
    """
    Then response should be:
    """
    : {
      code: 200
      body.json= {
      "someString": "stringValue",
      "someInt": 101,
      "someBoolean": true
      }
    }
    """
    Examples:
      | method | factory  |
      | POST   | PostBean |
      | PUT    | PutBean  |

  Scenario: GET by url only and response json array without setting data with table
    Given Exists 1 api data "BeanForArray"
    Then "/beans" should response:
    """
    : {
      code: 200
      body.json= [
        {
          "someString": "someString#1",
          "someInt": 1,
          "someBoolean": true
        }
      ]
    }
    """

  Scenario Outline: <method> by url only and response json array without setting data with table
    Given Exists 1 api data "<factory>"
    When <method> "/beans":
    """
    {}
    """
    Then response should be:
    """
    : {
      code: 200
      body.json= [
        {
          "someString": "someString#1",
          "someInt": 1,
          "someBoolean": true
        }
      ]
    }
    """
    Examples:
      | method | factory          |
      | POST   | PostBeanForArray |
      | PUT    | PutBeanForArray  |

  Scenario: GET by url only and response json array
    Given Exists api data "BeanForArray":
      | someString  | someInt | someBoolean |
      | stringValue | 101     | true        |
    Then "/beans" should response:
    """
    : {
      code: 200
      body.json= [
        {
          "someString": "stringValue",
          "someInt": 101,
          "someBoolean": true
        }
      ]
    }
    """

  Scenario Outline: <method> by url only and response json array
    Given Exists api data "<factory>":
      | someString  | someInt | someBoolean |
      | stringValue | 101     | true        |
    When <method> "/beans":
    """
    {}
    """
    Then response should be:
    """
    : {
      code: 200
      body.json= [
        {
          "someString": "stringValue",
          "someInt": 101,
          "someBoolean": true
        }
      ]
    }
    """
    Examples:
      | method | factory          |
      | POST   | PostBeanForArray |
      | PUT    | PutBeanForArray  |

  Scenario: GET by url and params
    Given Exists api data "Bean" with params "foo=bar&name=value1&name=value2":
      | someString  | someInt | someBoolean |
      | stringValue | 102     | false       |
    Then "/beans" should response:
    """
    code= 404
    """
    Then "/beans?foo=bar" should response:
    """
    code= 404
    """
    Then "/beans?foo=bar&name=value1" should response:
    """
    code= 404
    """
    Then "/beans?foo=bar&name=value1&name=value2" should response:
    """
    : {
      code: 200
      body.json= {
        "someString": "stringValue",
        "someInt": 102,
        "someBoolean": false
      }
    }
    """

  Scenario Outline: <method> by url and params
    Given Exists api data "<factory>" with params "foo=bar&name=value1&name=value2":
      | someString  | someInt | someBoolean |
      | stringValue | 102     | false       |
    When <method> "/beans":
    """
    {}
    """
    Then response should be:
    """
    code= 404
    """
    When <method> "/beans?foo=bar":
    """
    {}
    """
    Then response should be:
    """
    code= 404
    """
    When <method> "/beans?foo=bar&name=value1":
    """
    {}
    """
    Then response should be:
    """
    code= 404
    """
    When <method> "/beans?foo=bar&name=value1&name=value2":
    """
    {}
    """
    Then response should be:
    """
    : {
      code: 200
      body.json= {
        "someString": "stringValue",
        "someInt": 102,
        "someBoolean": false
      }
    }
    """
    Examples:
      | method | factory  |
      | POST   | PostBean |
      | PUT    | PutBean  |

  Scenario: Params only impact related step
    Given Exists api data "Bean" with params "foo=bar":
      | someString  | someInt | someBoolean |
      | stringValue | 102     | false       |
    And Exists api data "Bean":
      | someString  | someInt | someBoolean |
      | stringValue | 101     | true        |
    Then "/beans" should response:
    """
    : {
      code: 200
      body.json= {
        "someString": "stringValue",
        "someInt": 101,
        "someBoolean": true
      }
    }
    """

  Scenario Outline: Params only impact related step
    Given Exists api data "<factory>" with params "foo=bar":
      | someString  | someInt | someBoolean |
      | stringValue | 102     | false       |
    And Exists api data "<factory>":
      | someString  | someInt | someBoolean |
      | stringValue | 101     | true        |
    When <method> "/beans":
    """
    {}
    """
    Then response should be:
    """
    : {
      code: 200
      body.json= {
        "someString": "stringValue",
        "someInt": 101,
        "someBoolean": true
      }
    }
    """
    Examples:
      | method | factory  |
      | POST   | PostBean |
      | PUT    | PutBean  |

  Scenario: GET by url with child object
    Given Exists api data "BeanWithChild":
      | someString  | someInt | someBoolean | child.yaString |
      | stringValue | 101     | true        | childValue     |
    Then "/beansWithChild" should response:
    """
    : {
      code: 200
      body.json= {
        "someString": "stringValue",
        "someInt": 101,
        "someBoolean": true,
        "child": {
          "yaString": "childValue"
        }
      }
    }
    """

  Scenario Outline: <method> by url with child object
    Given Exists api data "<factory>":
      | someString  | someInt | someBoolean | child.yaString |
      | stringValue | 101     | true        | childValue     |
    When <method> "/beansWithChild":
    """
    {}
    """
    Then response should be:
    """
    : {
      code: 200
      body.json= {
        "someString": "stringValue",
        "someInt": 101,
        "someBoolean": true,
        "child": {
          "yaString": "childValue"
        }
      }
    }
    """
    Examples:
      | method | factory           |
      | POST   | PostBeanWithChild |
      | PUT    | PutBeanWithChild  |

  Scenario: GET by url with params and child object
    Given Exists api data "BeanWithChild" with params "foo=bar&name=value1&name=value2":
      | someString  | someInt | someBoolean | child.yaString |
      | stringValue | 102     | false       | childValue     |
    Then "/beansWithChild?foo=bar&name=value1&name=value2" should response:
    """
    : {
      code: 200
      body.json= {
        "someString": "stringValue",
        "someInt": 102,
        "someBoolean": false,
        "child": {
          "yaString": "childValue"
        }
      }
    }
    """

  Scenario Outline: <method> by url with params and child object
    Given Exists api data "<factory>" with params "foo=bar&name=value1&name=value2":
      | someString  | someInt | someBoolean | child.yaString |
      | stringValue | 102     | false       | childValue     |
    When <method> "/beansWithChild?foo=bar&name=value1&name=value2":
    """
    {}
    """
    Then response should be:
    """
    : {
      code: 200
      body.json= {
        "someString": "stringValue",
        "someInt": 102,
        "someBoolean": false,
        "child": {
          "yaString": "childValue"
        }
      }
    }
    """
    Examples:
      | method | factory           |
      | POST   | PostBeanWithChild |
      | PUT    | PutBeanWithChild  |

  Scenario: GET by url and path variables
    Given Exists api data "BeanWithPathVariable" with path variables "foo=bar":
      | someString  | someInt | someBoolean |
      | stringValue | 102     | false       |
    Then "/beans" should response:
    """
    code= 404
    """
    Then "/beans/bar" should response:
    """
    : {
      code: 200
      body.json= {
        "someString": "stringValue",
        "someInt": 102,
        "someBoolean": false
      }
    }
    """

  Scenario Outline: <method> by url and path variables
    Given Exists api data "<factory>" with path variables "foo=bar":
      | someString  | someInt | someBoolean |
      | stringValue | 102     | false       |
    When <method> "/beans":
    """
    {}
    """
    Then response should be:
    """
    code= 404
    """
    When <method> "/beans/bar":
    """
    {}
    """
    Then response should be:
    """
    : {
      code: 200
      body.json= {
        "someString": "stringValue",
        "someInt": 102,
        "someBoolean": false
      }
    }
    """
    Examples:
      | method | factory                  |
      | POST   | PostBeanWithPathVariable |
      | PUT    | PutBeanWithPathVariable  |

  Scenario: GET by url and two path variables
    Given Exists api data "BeanWithTwoPathVariables" with path variables "foo=bar&name=value":
      | someString  | someInt | someBoolean |
      | stringValue | 102     | false       |
    Then "/beans" should response:
    """
    code= 404
    """
    Then "/beans/bar/another/value" should response:
    """
    : {
      code: 200
      body.json= {
        "someString": "stringValue",
        "someInt": 102,
        "someBoolean": false
      }
    }
    """

  Scenario Outline: <method> by url and two path variables
    Given Exists api data "<factory>" with path variables "foo=bar&name=value":
      | someString  | someInt | someBoolean |
      | stringValue | 102     | false       |
    When <method> "/beans":
    """
    {}
    """
    Then response should be:
    """
    code= 404
    """
    When <method> "/beans/bar/another/value":
    """
    {}
    """
    Then response should be:
    """
    : {
      code: 200
      body.json= {
        "someString": "stringValue",
        "someInt": 102,
        "someBoolean": false
      }
    }
    """
    Examples:
      | method | factory                      |
      | POST   | PostBeanWithTwoPathVariables |
      | PUT    | PutBeanWithTwoPathVariables  |
