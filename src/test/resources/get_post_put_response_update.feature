Feature: Get/Post/Put response update

  Rule: Response update by url only

    Scenario Outline: GET by url only and response update <type> object without setting data with table
      Given Exists 1 api data "<spec>"
      When Exists 1 api data "<spec>"
      Then "/beans" should response:
        """
        : {
          code: 200
          body.<converter>= {
            someString: someString#2
            someInt: 2
            someBoolean: false
          }
        }
        """
      Examples:
        | type      | spec     | converter   |
        | gzip json | GzipBean | ungzip.json |
        | json      | Bean     | json        |

    Scenario Outline: <method> by url only and response update <type> object without setting data with table
      Given Exists 1 api data "<spec>"
      When Exists 1 api data "<spec>"
      When <method> "/beans":
      """
      {}
      """
      Then response should be:
      """
      : {
        code: 200
        body.<converter>= {
          someString: someString#2
          someInt: 2
          someBoolean: false
        }
      }
      """
      Examples:
        | type      | method | spec         | converter   |
        | json      | POST   | PostBean     | json        |
        | json      | PUT    | PutBean      | json        |
        | gzip json | POST   | PostGzipBean | ungzip.json |
        | gzip json | PUT    | PutGzipBean  | ungzip.json |

    Scenario Outline: GET by url only and response update <type> object
      Given Exists api data "<spec>":
        | someString  | someInt | someBoolean |
        | stringValue | 101     | true        |
      When Exists api data "<spec>":
        | someString   | someInt | someBoolean |
        | stringValue2 | 102     | false       |
      Then "/beans" should response:
      """
      : {
        code: 200
        body.<converter>= {
          someString: stringValue2
          someInt: 102
          someBoolean: false
        }
      }
      """
      Examples:
        | type      | spec     | converter   |
        | gzip json | GzipBean | ungzip.json |
        | json      | Bean     | json        |

    Scenario Outline: <method> by url only and response update <type> object
      Given Exists api data "<spec>":
        | someString  | someInt | someBoolean |
        | stringValue | 101     | true        |
      When Exists api data "<spec>":
        | someString   | someInt | someBoolean |
        | stringValue2 | 102     | false       |
      When <method> "/beans":
      """
      {}
      """
      Then response should be:
      """
      : {
        code: 200
        body.<converter>= {
          someString: stringValue2
          someInt: 102
          someBoolean: false
        }
      }
      """
      Examples:
        | type      | method | spec         | converter   |
        | json      | POST   | PostBean     | json        |
        | json      | PUT    | PutBean      | json        |
        | gzip json | POST   | PostGzipBean | ungzip.json |
        | gzip json | PUT    | PutGzipBean  | ungzip.json |

    Scenario Outline: GET by url only and response update <type> array without setting data with table
      Given Exists 1 api data "<spec>"
      When Exists 1 api data "<spec>"
      Then "/beans" should response:
      """
      : {
        code: 200
        body.<converter>= [{
          someString: someString#2
          someInt: 2
          someBoolean: false
        }]
      }
      """
      Examples:
        | type      | spec             | converter   |
        | json      | BeanForArray     | json        |
        | gzip json | GzipBeanForArray | ungzip.json |

    Scenario Outline: <method> by url only and response update <type> array without setting data with table
      Given Exists 1 api data "<spec>"
      When Exists 1 api data "<spec>"
      When <method> "/beans":
      """
      {}
      """
      Then response should be:
      """
      : {
        code: 200
        body.<converter>= [{
          someString: someString#2
          someInt: 2
          someBoolean: false
        }]
      }
      """
      Examples:
        | type      | method | spec                 | converter   |
        | json      | POST   | PostBeanForArray     | json        |
        | json      | PUT    | PutBeanForArray      | json        |
        | gzip json | POST   | PostGzipBeanForArray | ungzip.json |
        | gzip json | PUT    | PutGzipBeanForArray  | ungzip.json |

    Scenario Outline: GET by url only and response update <type> array
      Given Exists api data "<spec>":
        | someString  | someInt | someBoolean |
        | stringValue | 101     | true        |
      When Exists api data "<spec>":
        | someString   | someInt | someBoolean |
        | stringValue2 | 102     | false       |
      Then "/beans" should response:
      """
      : {
        code: 200
        body.<converter>= [{
          someString: stringValue2
          someInt: 102
          someBoolean: false
        }]
      }
      """
      Examples:
        | type      | spec             | converter   |
        | json      | BeanForArray     | json        |
        | gzip json | GzipBeanForArray | ungzip.json |

    Scenario Outline: <method> by url only and response update <type> array
      Given Exists api data "<spec>":
        | someString  | someInt | someBoolean |
        | stringValue | 101     | true        |
      When Exists api data "<spec>":
        | someString   | someInt | someBoolean |
        | stringValue2 | 102     | false       |
      When <method> "/beans":
      """
      {}
      """
      Then response should be:
      """
      : {
        code: 200
        body.<converter>= [{
          someString: stringValue2
          someInt: 102
          someBoolean: false
        }]
      }
      """
      Examples:
        | type      | method | spec                 | converter   |
        | json      | POST   | PostBeanForArray     | json        |
        | json      | PUT    | PutBeanForArray      | json        |
        | gzip json | POST   | PostGzipBeanForArray | ungzip.json |
        | gzip json | PUT    | PutGzipBeanForArray  | ungzip.json |

    Scenario Outline: GET by url only and response update <type> object without setting data with table
      Given Exists 1 api data "<spec>"
      When Exists 1 api data "<spec>"
      Then "/beans" should response:
      """
      : {
        code: 200
        body.<converter>= {
          someString: someString#2
          someInt: '2'
          someBoolean: 'false'
        }
      }
      """
      Examples:
        | type     | spec           | converter  |
        | xml      | BeanForXml     | xml        |
        | gzip xml | GzipBeanForXml | ungzip.xml |

    Scenario Outline: <method> by url only and response update <type> object without setting data with table
      Given Exists 1 api data "<spec>"
      When Exists 1 api data "<spec>"
      When <method> "/beans":
      """
      {}
      """
      Then response should be:
      """
      : {
        code: 200
        body.<converter>= {
          someString: someString#2
          someInt: '2'
          someBoolean: 'false'
        }
      }
      """
      Examples:
        | type     | method | spec               | converter  |
        | xml      | POST   | PostBeanForXml     | xml        |
        | xml      | PUT    | PutBeanForXml      | xml        |
        | gzip xml | POST   | PostGzipBeanForXml | ungzip.xml |
        | gzip xml | PUT    | PutGzipBeanForXml  | ungzip.xml |

    Scenario Outline: GET by url only and response update <type> object
      Given Exists api data "<spec>":
        | someString  | someInt | someBoolean |
        | stringValue | 101     | true        |
      When Exists api data "<spec>":
        | someString   | someInt | someBoolean |
        | stringValue2 | 102     | false       |
      When "/beans" should response:
      """
      : {
        code: 200
        body.<converter>= {
          someString: stringValue2
          someInt: '102'
          someBoolean: 'false'
        }
      }
      """
      Examples:
        | type     | spec           | converter  |
        | xml      | BeanForXml     | xml        |
        | gzip xml | GzipBeanForXml | ungzip.xml |

    Scenario Outline: <method> by url only and response update <type> object
      Given Exists api data "<spec>":
        | someString  | someInt | someBoolean |
        | stringValue | 101     | true        |
      When Exists api data "<spec>":
        | someString   | someInt | someBoolean |
        | stringValue2 | 102     | false       |
      When <method> "/beans":
      """
      {}
      """
      Then response should be:
      """
      : {
        code: 200
        body.<converter>= {
          someString: stringValue2
          someInt: '102'
          someBoolean: 'false'
        }
      }
      """
      Examples:
        | type     | method | spec               | converter  |
        | xml      | POST   | PostBeanForXml     | xml        |
        | xml      | PUT    | PutBeanForXml      | xml        |
        | gzip xml | POST   | PostGzipBeanForXml | ungzip.xml |
        | gzip xml | PUT    | PutGzipBeanForXml  | ungzip.xml |

    Scenario Outline: No impact on sub path - GET by url only and response update <type> object without setting data with table
      Given Exists 1 api data "<subPathSpec>"
      When Exists 1 api data "<spec>"
      Then "/beans/sub-path" should response:
        """
        : {
          code: 200
          body.<converter>= {
            someSubString: someSubString#1
            someSubInt: 1
            someSubBoolean: true
          }
        }
        """
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
        | type      | spec     | subPathSpec     | converter   |
        | gzip json | GzipBean | SubPathGzipBean | ungzip.json |
        | json      | Bean     | SubPathBean     | json        |

    Scenario Outline: No impact on parent path - GET by url only and response update <type> object without setting data with table
      Given Exists 1 api data "<spec>"
      When Exists 1 api data "<subPathSpec>"
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
      Then "/beans/sub-path" should response:
        """
        : {
          code: 200
          body.<converter>= {
            someSubString: someSubString#1
            someSubInt: 1
            someSubBoolean: true
          }
        }
        """
      Examples:
        | type      | spec     | subPathSpec     | converter   |
        | gzip json | GzipBean | SubPathGzipBean | ungzip.json |
        | json      | Bean     | SubPathBean     | json        |

    Scenario Outline: No impact on sub path - <method> by url only and response update <type> object without setting data with table
      Given Exists 1 api data "<subPathSpec>"
      When Exists 1 api data "<spec>"
      When <method> "/beans/sub-path":
      """
      {}
      """
      Then response should be:
      """
      : {
        code: 200
        body.<converter>= {
          someSubString: someSubString#1
          someSubInt: 1
          someSubBoolean: true
        }
      }
      """
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
        | type      | method | spec         | subPathSpec         | converter   |
        | json      | POST   | PostBean     | SubPathPostBean     | json        |
        | json      | PUT    | PutBean      | SubPathPutBean      | json        |
        | gzip json | POST   | PostGzipBean | SubPathPostGzipBean | ungzip.json |
        | gzip json | PUT    | PutGzipBean  | SubPathPutGzipBean  | ungzip.json |

    Scenario Outline: No impact on parent path - <method> by url only and response update <type> object without setting data with table
      Given Exists 1 api data "<spec>"
      When Exists 1 api data "<subPathSpec>"
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
      When <method> "/beans/sub-path":
      """
      {}
      """
      Then response should be:
      """
      : {
        code: 200
        body.<converter>= {
          someSubString: someSubString#1
          someSubInt: 1
          someSubBoolean: true
        }
      }
      """
      Examples:
        | type      | method | spec         | subPathSpec         | converter   |
        | json      | POST   | PostBean     | SubPathPostBean     | json        |
        | json      | PUT    | PutBean      | SubPathPutBean      | json        |
        | gzip json | POST   | PostGzipBean | SubPathPostGzipBean | ungzip.json |
        | gzip json | PUT    | PutGzipBean  | SubPathPutGzipBean  | ungzip.json |

  Rule: Response update by url and params

    Scenario: GET by url and params without setting data with table
      Given Exists 1 api data "Bean" with params "foo=bar&name=value1&name=value2"
      When Exists 1 api data "Bean" with params "foo=bar&name=value1&name=value2"
      Then "/beans?foo=bar&name=value1&name=value2" should response:
      """
      : {
        code: 200
        body.json= {
          someString: someString#2
          someInt: 2
          someBoolean: false
        }
      }
      """

    Scenario Outline: <method> by url and params without setting data with table
      Given Exists 1 api data "<factory>" with params "foo=bar&name=value1&name=value2"
      When Exists 1 api data "<factory>" with params "foo=bar&name=value1&name=value2"
      When <method> "/beans?foo=bar&name=value1&name=value2":
      """
      {}
      """
      Then response should be:
      """
      : {
        code: 200
        body.json= {
          someString: someString#2
          someInt: 2
          someBoolean: false
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
      When Exists api data "Bean" with params "foo=bar&name=value1&name=value2":
        | someString   | someInt | someBoolean |
        | stringValue2 | 103     | true        |
      Then "/beans?foo=bar&name=value1&name=value2" should response:
      """
      : {
      code: 200
        body.json= {
          someString: stringValue2
          someInt: 103
          someBoolean: true
        }
      }
      """

    Scenario Outline: <method> by url and params
      Given Exists api data "<factory>" with params "foo=bar&name=value1&name=value2":
        | someString  | someInt | someBoolean |
        | stringValue | 102     | false       |
      When Exists api data "<factory>" with params "foo=bar&name=value1&name=value2":
        | someString   | someInt | someBoolean |
        | stringValue2 | 103     | true        |
      When <method> "/beans?foo=bar&name=value1&name=value2":
      """
      {}
      """
      Then response should be:
      """
      : {
        code: 200
        body.json= {
          someString: stringValue2
          someInt: 103
          someBoolean: true
        }
      }
      """
      Examples:
        | method | factory  |
        | POST   | PostBean |
        | PUT    | PutBean  |

  Rule: Same path without params always win - response update by url and params

    Scenario: with params set later - GET by url and params without setting data with table
      Given Exists 1 api data "Bean"
      When Exists 1 api data "SamePathBean" with params "foo=bar&name=value1&name=value2"
      Then "/beans" should response:
        """
        : {
          code: 200
          body.json= {
            someString: someString#1
            someInt: 1
            someBoolean: true
          }
        }
        """
      Then "/beans?foo=bar&name=value1&name=value2" should response:
        """
        : {
          code: 200
          body.json= {
            someString: someString#1
            someInt: 1
            someBoolean: true
          }
        }
        """

    Scenario: with params set first - GET by url and params without setting data with table
      Given Exists 1 api data "SamePathBean" with params "foo=bar&name=value1&name=value2"
      When Exists 1 api data "Bean"
      Then "/beans" should response:
        """
        : {
          code: 200
          body.json= {
            someString: someString#1
            someInt: 1
            someBoolean: true
          }
        }
        """
      Then "/beans?foo=bar&name=value1&name=value2" should response:
        """
        : {
          code: 200
          body.json= {
            someString: someString#1
            someInt: 1
            someBoolean: true
          }
        }
        """

    Scenario Outline: with params set later - <method> by url and params without setting data with table
      Given Exists 1 api data "<spec>"
      When Exists 1 api data "<anotherSpec>" with params "foo=bar&name=value1&name=value2"
      When <method> "/beans":
        """
        {}
        """
      Then response should be:
        """
        : {
          code: 200
          body.json= {
            someString: someString#1
            someInt: 1
            someBoolean: true
          }
        }
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
            someString: someString#1
            someInt: 1
            someBoolean: true
          }
        }
        """
      Examples:
        | method | spec     | anotherSpec      |
        | POST   | PostBean | SamePathPostBean |
        | PUT    | PutBean  | SamePathPutBean  |

    Scenario Outline: with params set first - <method> by url and params without setting data with table
      Given Exists 1 api data "<anotherSpec>" with params "foo=bar&name=value1&name=value2"
      When Exists 1 api data "<spec>"
      When <method> "/beans":
        """
        {}
        """
      Then response should be:
        """
        : {
          code: 200
          body.json= {
            someString: someString#1
            someInt: 1
            someBoolean: true
          }
        }
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
            someString: someString#1
            someInt: 1
            someBoolean: true
          }
        }
        """
      Examples:
        | method | spec     | anotherSpec      |
        | POST   | PostBean | SamePathPostBean |
        | PUT    | PutBean  | SamePathPutBean  |

  Rule: Same path with less params always win - response update by url and params

    Scenario: with params set later - GET by url and params without setting data with table
      Given Exists 1 api data "Bean" with params "foo=bar"
      When Exists 1 api data "SamePathBean" with params "foo=bar&name=value1&name=value2"
      Then "/beans?foo=bar" should response:
        """
        : {
          code: 200
          body.json= {
            someString: someString#1
            someInt: 1
            someBoolean: true
          }
        }
        """
      Then "/beans?foo=bar&name=value1&name=value2" should response:
        """
        : {
          code: 200
          body.json= {
            someString: someString#1
            someInt: 1
            someBoolean: true
          }
        }
        """

    Scenario: with params set first - GET by url and params without setting data with table
      Given Exists 1 api data "SamePathBean" with params "foo=bar&name=value1&name=value2"
      When Exists 1 api data "Bean" with params "foo=bar"
      Then "/beans?foo=bar" should response:
        """
        : {
          code: 200
          body.json= {
            someString: someString#1
            someInt: 1
            someBoolean: true
          }
        }
        """
      Then "/beans?foo=bar&name=value1&name=value2" should response:
        """
        : {
          code: 200
          body.json= {
            someString: someString#1
            someInt: 1
            someBoolean: true
          }
        }
        """

    Scenario Outline: with params set later - <method> by url and params without setting data with table
      Given Exists 1 api data "<spec>" with params "foo=bar"
      When Exists 1 api data "<anotherSpec>" with params "foo=bar&name=value1&name=value2"
      When <method> "/beans?foo=bar":
        """
        {}
        """
      Then response should be:
        """
        : {
        code: 200
          body.json= {
            someString: someString#1
            someInt: 1
            someBoolean: true
          }
        }
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
            someString: someString#1
            someInt: 1
            someBoolean: true
          }
        }
        """
      Examples:
        | method | spec     | anotherSpec      |
        | POST   | PostBean | SamePathPostBean |
        | PUT    | PutBean  | SamePathPutBean  |

    Scenario Outline: with params set first - <method> by url and params without setting data with table
      Given Exists 1 api data "<anotherSpec>" with params "foo=bar&name=value1&name=value2"
      When Exists 1 api data "<spec>" with params "foo=bar"
      When <method> "/beans?foo=bar":
        """
        {}
        """
      Then response should be:
        """
        : {
        code: 200
          body.json= {
            someString: someString#1
            someInt: 1
            someBoolean: true
          }
        }
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
            someString: someString#1
            someInt: 1
            someBoolean: true
          }
        }
        """
      Examples:
        | method | spec     | anotherSpec      |
        | POST   | PostBean | SamePathPostBean |
        | PUT    | PutBean  | SamePathPutBean  |

  Rule: Not impact on same path with different params - response update by url and params

    Scenario: with params set later - GET by url and params without setting data with table
      Given Exists 1 api data "Bean" with params "foo=bar&bus=car"
      When Exists 1 api data "SamePathBean" with params "foo=bar&name=value1&name=value2"
      Then "/beans?foo=bar&bus=car" should response:
        """
        : {
          code: 200
          body.json= {
            someString: someString#1
            someInt: 1
            someBoolean: true
          }
        }
        """
      Then "/beans?foo=bar&name=value1&name=value2" should response:
        """
        : {
          code: 200
          body.json= {
            anotherString: anotherString#1
            anotherInt: 1
            anotherBoolean: true
          }
        }
        """

    Scenario: with params set first - GET by url and params without setting data with table
      Given Exists 1 api data "SamePathBean" with params "foo=bar&name=value1&name=value2"
      When Exists 1 api data "Bean" with params "foo=bar&bus=car"
      Then "/beans?foo=bar&bus=car" should response:
        """
        : {
          code: 200
          body.json= {
            someString: someString#1
            someInt: 1
            someBoolean: true
          }
        }
        """
      Then "/beans?foo=bar&name=value1&name=value2" should response:
        """
        : {
          code: 200
          body.json= {
            anotherString: anotherString#1
            anotherInt: 1
            anotherBoolean: true
          }
        }
        """

    Scenario Outline: with params set later - <method> by url and params without setting data with table
      Given Exists 1 api data "<spec>" with params "foo=bar&bus=car"
      When Exists 1 api data "<anotherSpec>" with params "foo=bar&name=value1&name=value2"
      When <method> "/beans?foo=bar&bus=car":
        """
        {}
        """
      Then response should be:
        """
        : {
        code: 200
          body.json= {
            someString: someString#1
            someInt: 1
            someBoolean: true
          }
        }
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
            anotherString: anotherString#1
            anotherInt: 1
            anotherBoolean: true
          }
        }
        """
      Examples:
        | method | spec     | anotherSpec      |
        | POST   | PostBean | SamePathPostBean |
        | PUT    | PutBean  | SamePathPutBean  |

    Scenario Outline: with params set first - <method> by url and params without setting data with table
      Given Exists 1 api data "<anotherSpec>" with params "foo=bar&name=value1&name=value2"
      When Exists 1 api data "<spec>" with params "foo=bar&bus=car"
      When <method> "/beans?foo=bar&bus=car":
        """
        {}
        """
      Then response should be:
        """
        : {
        code: 200
          body.json= {
            someString: someString#1
            someInt: 1
            someBoolean: true
          }
        }
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
            anotherString: anotherString#1
            anotherInt: 1
            anotherBoolean: true
          }
        }
        """
      Examples:
        | method | spec     | anotherSpec      |
        | POST   | PostBean | SamePathPostBean |
        | PUT    | PutBean  | SamePathPutBean  |

  Rule: Response update by url and path variables

    Scenario: GET by url and path variables without setting data with table
      Given Exists 1 api data "BeanWithPathVariable" with path variables "foo=bar"
      When Exists 1 api data "BeanWithPathVariable" with path variables "foo=bar"
      Then "/beans/bar" should response:
      """
      : {
      code: 200
        body.json= {
          someString: someString#2
          someInt: 2
          someBoolean: false
        }
      }
      """

    Scenario Outline: <method> by url and path variables without setting data with table
      Given Exists 1 api data "<factory>" with path variables "foo=bar"
      When Exists 1 api data "<factory>" with path variables "foo=bar"
      When <method> "/beans/bar":
      """
      {}
      """
      Then response should be:
      """
      : {
      code: 200
        body.json= {
          someString: someString#2
          someInt: 2
          someBoolean: false
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
      When Exists api data "BeanWithTwoPathVariables" with path variables "foo=bar&name=value":
        | someString   | someInt | someBoolean |
        | stringValue2 | 103     | true        |
      Then "/beans/bar/another/value" should response:
      """
      : {
      code: 200
        body.json= {
          someString: stringValue2
          someInt: 103
          someBoolean: true
        }
      }
      """

    Scenario Outline: <method> by url and two path variables
      Given Exists api data "<factory>" with path variables "foo=bar&name=value":
        | someString  | someInt | someBoolean |
        | stringValue | 102     | false       |
      When Exists api data "<factory>" with path variables "foo=bar&name=value":
        | someString   | someInt | someBoolean |
        | stringValue2 | 103     | true        |
      When <method> "/beans/bar/another/value":
      """
      {}
      """
      Then response should be:
      """
      : {
      code: 200
        body.json= {
          someString: stringValue2
          someInt: 103
          someBoolean: true
        }
      }
      """
      Examples:
        | method | factory                      |
        | POST   | PostBeanWithTwoPathVariables |
        | PUT    | PutBeanWithTwoPathVariables  |

    Scenario: Not impact on sub path - GET by url and path variables without setting data with table
      Given Exists 1 api data "SubPathBeanWithPathVariable" with path variables "foo=bar"
      When Exists 1 api data "BeanWithPathVariable" with path variables "foo=bar"
      Then "/beans/bar/sub-path" should response:
      """
      : {
      code: 200
        body.json= {
          someSubString: someSubString#1
          someSubInt: 1
          someSubBoolean: true
        }
      }
      """
      Then "/beans/bar" should response:
      """
      : {
      code: 200
        body.json= {
          someString: someString#1
          someInt: 1
          someBoolean: true
        }
      }
      """

    Scenario: Not impact on parent path - GET by url and path variables without setting data with table
      Given Exists 1 api data "BeanWithPathVariable" with path variables "foo=bar"
      When Exists 1 api data "SubPathBeanWithPathVariable" with path variables "foo=bar"
      Then "/beans/bar" should response:
      """
      : {
      code: 200
        body.json= {
          someString: someString#1
          someInt: 1
          someBoolean: true
        }
      }
      """
      Then "/beans/bar/sub-path" should response:
      """
      : {
      code: 200
        body.json= {
          someSubString: someSubString#1
          someSubInt: 1
          someSubBoolean: true
        }
      }
      """

    Scenario Outline: No impact on sub path - <method> by url and path variables without setting data with table
      Given Exists 1 api data "<subPathSpec>" with path variables "foo=bar"
      When Exists 1 api data "<spec>" with path variables "foo=bar"
      When <method> "/beans/bar/sub-path":
      """
      {}
      """
      Then response should be:
      """
      : {
      code: 200
        body.json= {
          someSubString: someSubString#1
          someSubInt: 1
          someSubBoolean: true
        }
      }
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
          someString: someString#1
          someInt: 1
          someBoolean: true
        }
      }
      """
      Examples:
        | method | spec                     | subPathSpec                     |
        | POST   | PostBeanWithPathVariable | SubPathPostBeanWithPathVariable |
        | PUT    | PutBeanWithPathVariable  | SubPathPutBeanWithPathVariable  |

    Scenario Outline: No impact on parent path - <method> by url and path variables without setting data with table
      When Exists 1 api data "<spec>" with path variables "foo=bar"
      Given Exists 1 api data "<subPathSpec>" with path variables "foo=bar"
      When <method> "/beans/bar":
      """
      {}
      """
      Then response should be:
      """
      : {
      code: 200
        body.json= {
          someString: someString#1
          someInt: 1
          someBoolean: true
        }
      }
      """
      When <method> "/beans/bar/sub-path":
      """
      {}
      """
      Then response should be:
      """
      : {
      code: 200
        body.json= {
          someSubString: someSubString#1
          someSubInt: 1
          someSubBoolean: true
        }
      }
      """
      Examples:
        | method | spec                     | subPathSpec                     |
        | POST   | PostBeanWithPathVariable | SubPathPostBeanWithPathVariable |
        | PUT    | PutBeanWithPathVariable  | SubPathPutBeanWithPathVariable  |

  Rule: Response update with specified times

    Scenario Outline: GET by url and response <type> object only one time
      Given Exists 1 api data "<spec>"
      When Exists 1 api data "<spec>"
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
        code: 200
        body.<converter>.someString= someString#2
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
      When Exists 1 api data "<spec>"
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
      : {
        code: 200
        body.<converter>.someString= someString#2
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
      When Exists 1 api data "<spec>"
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
      : {
        code: 200
        body.<converter>= [{
          someString: someString#2
          someInt: 2
          someBoolean: false
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
      When Exists 1 api data "<spec>"
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
      : {
        code: 200
        body.<converter>= [{
          someString: someString#2
          someInt: 2
          someBoolean: false
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

    Scenario Outline: overwrite unlimited times - GET by url and response <type> object only one time
      Given Exists 1 api data "<unlimitedTimesSpec>"
      When Exists 1 api data "<spec>"
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
        | type      | spec                      | converter   | unlimitedTimesSpec |
        | json      | BeanOnlyOneTime           | json        | Bean               |
        | gzip json | GzipBeanOnlyOneTime       | ungzip.json | GzipBean           |
        | xml       | BeanForXmlOnlyOneTime     | xml         | BeanForXml         |
        | gzip xml  | GzipBeanForXmlOnlyOneTime | ungzip.xml  | GzipBeanForXml     |

    Scenario Outline: overwrite unlimited times - <method> by url only and response <type> object only one time
      Given Exists 1 api data "<unlimitedTimesSpec>"
      When Exists 1 api data "<spec>"
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
        | type      | method | spec                          | converter   | unlimitedTimesSpec |
        | json      | POST   | PostBeanOnlyOneTime           | json        | PostBean           |
        | json      | PUT    | PutBeanOnlyOneTime            | json        | PutBean            |
        | gzip json | POST   | PostGzipBeanOnlyOneTime       | ungzip.json | PostGzipBean       |
        | gzip json | PUT    | PutGzipBeanOnlyOneTime        | ungzip.json | PutGzipBean        |
        | xml       | POST   | PostBeanForXmlOnlyOneTime     | xml         | PostBeanForXml     |
        | gzip xml  | POST   | PostGzipBeanForXmlOnlyOneTime | ungzip.xml  | PostGzipBeanForXml |
        | xml       | PUT    | PutBeanForXmlOnlyOneTime      | xml         | PutBeanForXml      |
        | gzip xml  | PUT    | PutGzipBeanForXmlOnlyOneTime  | ungzip.xml  | PutGzipBeanForXml  |

    Scenario Outline: one time and then unlimited times - GET by url and response <type> object only one time
      Given Exists 1 api data "<spec>"
      When Exists api data "<unlimitedTimesSpec>":
        | someString |
        | unlimited  |
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
        code: 200
        body.<converter>.someString= unlimited
      }
      """
      Then "/beans" should response:
      """
      : {
        code: 200
        body.<converter>.someString= unlimited
      }
      """
      Examples:
        | type      | spec                      | converter   | unlimitedTimesSpec |
        | json      | BeanOnlyOneTime           | json        | Bean               |
        | gzip json | GzipBeanOnlyOneTime       | ungzip.json | GzipBean           |
        | xml       | BeanForXmlOnlyOneTime     | xml         | BeanForXml         |
        | gzip xml  | GzipBeanForXmlOnlyOneTime | ungzip.xml  | GzipBeanForXml     |

    Scenario Outline: one time and then unlimited times - <method> by url only and response <type> object only one time
      Given Exists 1 api data "<spec>"
      When Exists api data "<unlimitedTimesSpec>":
        | someString |
        | unlimited  |
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
      : {
        code: 200
        body.<converter>.someString= unlimited
      }
      """
      When <method> "/beans":
      """
      {}
      """
      Then response should be:
      """
      : {
        code: 200
        body.<converter>.someString= unlimited
      }
      """
      Examples:
        | type      | method | spec                          | converter   | unlimitedTimesSpec |
        | json      | POST   | PostBeanOnlyOneTime           | json        | PostBean           |
        | json      | PUT    | PutBeanOnlyOneTime            | json        | PutBean            |
        | gzip json | POST   | PostGzipBeanOnlyOneTime       | ungzip.json | PostGzipBean       |
        | gzip json | PUT    | PutGzipBeanOnlyOneTime        | ungzip.json | PutGzipBean        |
        | xml       | POST   | PostBeanForXmlOnlyOneTime     | xml         | PostBeanForXml     |
        | gzip xml  | POST   | PostGzipBeanForXmlOnlyOneTime | ungzip.xml  | PostGzipBeanForXml |
        | xml       | PUT    | PutBeanForXmlOnlyOneTime      | xml         | PutBeanForXml      |
        | gzip xml  | PUT    | PutGzipBeanForXmlOnlyOneTime  | ungzip.xml  | PutGzipBeanForXml  |

