# language: zh-CN
功能: Get/Post/Put request with Chinese steps

  场景: GET by url only and response json object without setting data with table
    假如存在1个接口数据"Bean"
    那么"/beans" should response:
    """
    : {
      code= 200
      body.json= {
        "someString": "someString#1",
        "someInt": 1,
        "someBoolean": true
      }
    }
    """

  场景大纲: <method> by url only and response json object without setting data with table
    假如存在1个接口数据"<factory>"
    当<method> "/beans":
    """
    {}
    """
    那么response should be:
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
    例子:
      | method | factory  |
      | POST   | PostBean |
      | PUT    | PutBean  |

  场景: GET by url only and response json object
    假如存在接口数据"Bean":
      | someString  | someInt | someBoolean |
      | stringValue | 101     | true        |
    那么"/beans" should response:
    """
    : {
      code= 200
      body.json= {
        "someString": "stringValue",
        "someInt": 101,
        "someBoolean": true
      }
    }
    """

  场景大纲: <method> by url only and response json object
    假如存在接口数据"<factory>":
      | someString  | someInt | someBoolean |
      | stringValue | 101     | true        |
    当<method> "/beans":
    """
    {}
    """
    那么response should be:
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
    例子:
      | method | factory  |
      | POST   | PostBean |
      | PUT    | PutBean  |

  场景: GET by url and params
    假如存在接口数据"Bean"并匹配查询参数"foo=bar&name=value1&name=value2":
      | someString  | someInt | someBoolean |
      | stringValue | 102     | false       |
    那么"/beans" should response:
    """
    code= 404
    """
    那么"/beans?foo=bar" should response:
    """
    code= 404
    """
    那么"/beans?foo=bar&name=value1" should response:
    """
    code= 404
    """
    那么"/beans?foo=bar&name=value1&name=value2" should response:
    """
    : {
      code= 200
      body.json= {
        "someString": "stringValue",
        "someInt": 102,
        "someBoolean": false
      }
    }
    """

  场景大纲: <method> by url and params
    假如存在接口数据"<factory>"并匹配查询参数"foo=bar&name=value1&name=value2":
      | someString  | someInt | someBoolean |
      | stringValue | 102     | false       |
    当<method> "/beans":
    """
    {}
    """
    那么response should be:
    """
    code: 404
    """
    当<method> "/beans?foo=bar":
    """
    {}
    """
    那么response should be:
    """
    code: 404
    """
    当<method> "/beans?foo=bar&name=value1":
    """
    {}
    """
    那么response should be:
    """
    code: 404
    """
    当<method> "/beans?foo=bar&name=value1&name=value2":
    """
    {}
    """
    那么response should be:
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
    例子:
      | method | factory  |
      | POST   | PostBean |
      | PUT    | PutBean  |

  场景: GET by url and two path variables
    假如存在接口数据"BeanWithTwoPathVariables"并匹配路径变量"foo=bar&name=value":
      | someString  | someInt | someBoolean |
      | stringValue | 102     | false       |
    那么"/beans" should response:
    """
    code= 404
    """
    那么"/beans/bar/another/value" should response:
    """
    : {
      code= 200
      body.json= {
        "someString": "stringValue",
        "someInt": 102,
        "someBoolean": false
      }
    }
    """

  场景大纲: <method> by url and two path variables
    假如存在接口数据"<factory>"并匹配路径变量"foo=bar&name=value":
      | someString  | someInt | someBoolean |
      | stringValue | 102     | false       |
    当<method> "/beans":
    """
    {}
    """
    那么response should be:
    """
    code: 404
    """
    当<method> "/beans/bar/another/value":
    """
    {}
    """
    那么response should be:
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
    例子:
      | method | factory                      |
      | POST   | PostBeanWithTwoPathVariables |
      | PUT    | PutBeanWithTwoPathVariables  |
