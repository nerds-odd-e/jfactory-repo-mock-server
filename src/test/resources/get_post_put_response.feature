Feature: Get/Post/Put response

  Rule: Response by url only

    Scenario Outline: GET by url only and response <type> object without setting data with table
      Given Exists 1 api data "<spec>"
      Then "/beans" should response:
      """
      : {
        code: 200
        body.<converter>= {
          someString: someString#1
          someInt: 1
          someBoolean: true
        }
      }
      """
      Examples:
        | type      | spec     | converter   |
        | gzip json | GzipBean | ungzip.json |
        | json      | Bean     | json        |

    Scenario Outline: <method> by url only and response <type> object without setting data with table
      Given Exists 1 api data "<spec>"
      When <method> "/beans":
      """
      {}
      """
      Then response should be:
      """
      : {
        code: 200
        body.<converter>= {
          someString: someString#1
          someInt: 1
          someBoolean: true
        }
      }
      """
      Examples:
        | type      | method | spec         | converter   |
        | json      | POST   | PostBean     | json        |
        | json      | PUT    | PutBean      | json        |
        | gzip json | POST   | PostGzipBean | ungzip.json |
        | gzip json | PUT    | PutGzipBean  | ungzip.json |

    Scenario Outline: GET by url only and response <type> object
      Given Exists api data "<spec>":
        | someString  | someInt | someBoolean |
        | stringValue | 101     | true        |
      When "/beans" should response:
      """
      : {
        code: 200
        body.<converter>= {
          someString: stringValue
          someInt: 101
          someBoolean: true
        }
      }
      """
      Examples:
        | type      | spec     | converter   |
        | gzip json | GzipBean | ungzip.json |
        | json      | Bean     | json        |

    Scenario Outline: <method> by url only and response <type> object
      Given Exists api data "<spec>":
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
        body.<converter>= {
          someString: stringValue
          someInt: 101
          someBoolean: true
        }
      }
      """
      Examples:
        | type      | method | spec         | converter   |
        | json      | POST   | PostBean     | json        |
        | json      | PUT    | PutBean      | json        |
        | gzip json | POST   | PostGzipBean | ungzip.json |
        | gzip json | PUT    | PutGzipBean  | ungzip.json |

    Scenario Outline: GET by url only and response <type> array without setting data with table
      Given Exists 1 api data "<spec>"
      Then "/beans" should response:
      """
      : {
        code: 200
        body.<converter>= [{
          someString: someString#1
          someInt: 1
          someBoolean: true
        }]
      }
      """
      Examples:
        | type      | spec             | converter   |
        | json      | BeanForArray     | json        |
        | gzip json | GzipBeanForArray | ungzip.json |

    Scenario Outline: <method> by url only and response <type> array without setting data with table
      Given Exists 1 api data "<spec>"
      When <method> "/beans":
      """
      {}
      """
      Then response should be:
      """
      : {
        code: 200
        body.<converter>= [{
          someString: someString#1
          someInt: 1
          someBoolean: true
        }]
      }
      """
      Examples:
        | type      | method | spec                 | converter   |
        | json      | POST   | PostBeanForArray     | json        |
        | json      | PUT    | PutBeanForArray      | json        |
        | gzip json | POST   | PostGzipBeanForArray | ungzip.json |
        | gzip json | PUT    | PutGzipBeanForArray  | ungzip.json |

    Scenario Outline: GET by url only and response <type> array
      Given Exists api data "<spec>":
        | someString  | someInt | someBoolean |
        | stringValue | 101     | true        |
      Then "/beans" should response:
      """
      : {
        code: 200
        body.<converter>= [{
          someString: stringValue
          someInt: 101
          someBoolean: true
        }]
      }
      """
      Examples:
        | type      | spec             | converter   |
        | json      | BeanForArray     | json        |
        | gzip json | GzipBeanForArray | ungzip.json |

    Scenario Outline: <method> by url only and response <type> array
      Given Exists api data "<spec>":
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
        body.<converter>= [{
          someString: stringValue
          someInt: 101
          someBoolean: true
        }]
      }
      """
      Examples:
        | type      | method | spec                 | converter   |
        | json      | POST   | PostBeanForArray     | json        |
        | json      | PUT    | PutBeanForArray      | json        |
        | gzip json | POST   | PostGzipBeanForArray | ungzip.json |
        | gzip json | PUT    | PutGzipBeanForArray  | ungzip.json |

    Scenario: GET by url with child object
      Given Exists api data "BeanWithChild":
        | someString  | someInt | someBoolean | child.yaString |
        | stringValue | 101     | true        | childValue     |
      Then "/beansWithChild" should response:
      """
      : {
        code: 200
        body.json= {
          someString: stringValue
          someInt: 101
          someBoolean: true
          child: {
            yaString: childValue
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
          someString: stringValue
          someInt: 101
          someBoolean: true
          child: {
            yaString: childValue
          }
        }
      }
      """
      Examples:
        | method | factory           |
        | POST   | PostBeanWithChild |
        | PUT    | PutBeanWithChild  |

    Scenario Outline: GET by url only and response <type> object without setting data with table
      Given Exists 1 api data "<spec>"
      Then "/beans" should response:
      """
      : {
        code: 200
        body.<converter>= {
          someString: someString#1
          someInt: '1'
          someBoolean: 'true'
        }
      }
      """
      Examples:
        | type     | spec           | converter  |
        | xml      | BeanForXml     | xml        |
        | gzip xml | GzipBeanForXml | ungzip.xml |

    Scenario Outline: <method> by url only and response <type> object without setting data with table
      Given Exists 1 api data "<spec>"
      When <method> "/beans":
      """
      {}
      """
      Then response should be:
      """
      : {
        code: 200
        body.<converter>= {
          someString: someString#1
          someInt: '1'
          someBoolean: 'true'
        }
      }
      """
      Examples:
        | type     | method | spec               | converter  |
        | xml      | POST   | PostBeanForXml     | xml        |
        | xml      | PUT    | PutBeanForXml      | xml        |
        | gzip xml | POST   | PostGzipBeanForXml | ungzip.xml |
        | gzip xml | PUT    | PutGzipBeanForXml  | ungzip.xml |

    Scenario Outline: GET by url only and response <type> object
      Given Exists api data "<spec>":
        | someString  | someInt | someBoolean |
        | stringValue | 101     | true        |
      When "/beans" should response:
      """
      : {
        code: 200
        body.<converter>= {
          someString: stringValue
          someInt: '101'
          someBoolean: 'true'
        }
      }
      """
      Examples:
        | type     | spec           | converter  |
        | xml      | BeanForXml     | xml        |
        | gzip xml | GzipBeanForXml | ungzip.xml |

    Scenario Outline: <method> by url only and response <type> object
      Given Exists api data "<spec>":
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
        body.<converter>= {
          someString: stringValue
          someInt: '101'
          someBoolean: 'true'
        }
      }
      """
      Examples:
        | type     | method | spec               | converter  |
        | xml      | POST   | PostBeanForXml     | xml        |
        | xml      | PUT    | PutBeanForXml      | xml        |
        | gzip xml | POST   | PostGzipBeanForXml | ungzip.xml |
        | gzip xml | PUT    | PutGzipBeanForXml  | ungzip.xml |

    Scenario: GET by url with xml child object
      Given Exists api data "BeanWithChildForXml":
        | someString  | someInt | someBoolean | child.yaString |
        | stringValue | 101     | true        | childValue     |
      Then "/beansWithChild" should response:
      """
      : {
        code: 200
        body.xml= {
          someString: stringValue
          someInt: '101'
          someBoolean: 'true'
          child: {
            yaString: childValue
          }
        }
      }
      """

    Scenario Outline: <method> by url with xml child object
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
        body.xml= {
          someString: stringValue
          someInt: '101'
          someBoolean: 'true'
          child: {
            yaString: childValue
          }
        }
      }
      """
      Examples:
        | method | factory                 |
        | POST   | PostBeanWithChildForXml |
        | PUT    | PutBeanWithChildForXml  |

  Rule: Response by url and params

    Scenario: GET by url and params without setting data with table
      Given Exists 1 api data "Bean" with params "foo=bar&name=value1&name=value2"
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
          "someString": "someString#1",
          "someInt": 1,
          "someBoolean": true
        }
      }
      """

    Scenario Outline: <method> by url and params without setting data with table
      Given Exists 1 api data "<factory>" with params "foo=bar&name=value1&name=value2"
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

  Rule: Response by url and path variables

    Scenario: GET by url and path variables without setting data with table
      Given Exists 1 api data "BeanWithPathVariable" with path variables "foo=bar"
      Then "/beans" should response:
      """
      code= 404
      """
      Then "/beans/bar" should response:
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

    Scenario Outline: <method> by url and path variables without setting data with table
      Given Exists 1 api data "<factory>" with path variables "foo=bar"
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
          "someString": "someString#1",
          "someInt": 1,
          "someBoolean": true
        }
      }
      """
      Examples:
        | method | factory                  |
        | POST   | PostBeanWithPathVariable |
        | PUT    | PutBeanWithPathVariable  |

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

  Rule: Response with trait and spec

    Scenario: response without table but with trait and spec split by space
      Given Exists 1 api data "intValue strValue Bean"
      Then "/beans" should response:
      """
      : {
        code: 200
        body.json= {
        "someString": "someStringValue",
        "someInt": 42,
        "someBoolean": true
        }
      }
      """

    Scenario: response without table but with trait and spec split by comma
      Given Exists 1 api data "intValue,strValue,Bean"
      Then "/beans" should response:
      """
      : {
        code: 200
        body.json= {
        "someString": "someStringValue",
        "someInt": 42,
        "someBoolean": true
        }
      }
      """

  Rule: Response with specified times

    Scenario Outline: GET by url and response <type> object only one time
      Given Exists 1 api data "<spec>"
      Then "/beans" should response:
      """
      : {
        code: 200
        body.<converter>.someString= someString#1
      }
      """
      Then "/beans" should response:
      """
      : {
        code: 404
      }
      """
      Examples:
        | type      | spec                      | converter   |
        | json      | BeanOnlyOneTime           | json        |
        | gzip json | GzipBeanOnlyOneTime       | ungzip.json |
        | xml       | BeanForXmlOnlyOneTime     | xml         |
        | gzip xml  | GzipBeanForXmlOnlyOneTime | ungzip.xml  |

    Scenario Outline: <method> by url only and response <type> object only one time
      Given Exists 1 api data "<spec>"
      When <method> "/beans":
      """
      {}
      """
      Then response should be:
      """
      : {
        code: 200
        body.<converter>.someString= someString#1
      }
      """
      When <method> "/beans":
      """
      {}
      """
      Then response should be:
      """
      code: 404
      """
      Examples:
        | type      | method | spec                          | converter   |
        | json      | POST   | PostBeanOnlyOneTime           | json        |
        | json      | PUT    | PutBeanOnlyOneTime            | json        |
        | gzip json | POST   | PostGzipBeanOnlyOneTime       | ungzip.json |
        | gzip json | PUT    | PutGzipBeanOnlyOneTime        | ungzip.json |
        | xml       | POST   | PostBeanForXmlOnlyOneTime     | xml         |
        | gzip xml  | POST   | PostGzipBeanForXmlOnlyOneTime | ungzip.xml  |
        | xml       | PUT    | PutBeanForXmlOnlyOneTime      | xml         |
        | gzip xml  | PUT    | PutGzipBeanForXmlOnlyOneTime  | ungzip.xml  |

    Scenario Outline: GET by url only and response <type> array only one time
      Given Exists 1 api data "<spec>"
      Then "/beans" should response:
      """
      : {
        code: 200
        body.<converter>= [{
          someString: someString#1
          someInt: 1
          someBoolean: true
        }]
      }
      """
      Then "/beans" should response:
      """
      code: 404
      """
      Examples:
        | type      | spec                        | converter   |
        | json      | BeanForArrayOnlyOneTime     | json        |
        | gzip json | GzipBeanForArrayOnlyOneTime | ungzip.json |

    Scenario Outline: <method> by url only and response <type> array only one time
      Given Exists 1 api data "<spec>"
      When <method> "/beans":
      """
      {}
      """
      Then response should be:
      """
      : {
        code: 200
        body.<converter>= [{
          someString: someString#1
          someInt: 1
          someBoolean: true
        }]
      }
      """
      When <method> "/beans":
      """
      {}
      """
      Then response should be:
      """
      code: 404
      """
      Examples:
        | type      | method | spec                            | converter   |
        | json      | POST   | PostBeanForArrayOnlyOneTime     | json        |
        | json      | PUT    | PutBeanForArrayOnlyOneTime      | json        |
        | gzip json | POST   | PostGzipBeanForArrayOnlyOneTime | ungzip.json |
        | gzip json | PUT    | PutGzipBeanForArrayOnlyOneTime  | ungzip.json |

