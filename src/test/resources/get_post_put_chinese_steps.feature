# language: zh-CN
功能: Get/Post/Put request with Chinese steps

  场景大纲: <method> by url only and response json object without setting data with table
    假如存在1个接口数据"<factory>"
    那么<method> "/beans" response code is 200 and body as below
    """
    {
      "someString": "someString#1",
      "someInt": 1,
      "someBoolean": true
    }
    """
    例子:
      | method | factory  |
      | GET    | Bean     |
      | POST   | PostBean |
      | PUT    | PutBean  |

  场景大纲: <method> by url only and response json object
    假如存在接口数据"<factory>":
      | someString  | someInt | someBoolean |
      | stringValue | 101     | true        |
    那么<method> "/beans" response code is 200 and body as below
    """
    {
      "someString": "stringValue",
      "someInt": 101,
      "someBoolean": true
    }
    """
    例子:
      | method | factory  |
      | GET    | Bean     |
      | POST   | PostBean |
      | PUT    | PutBean  |

  场景大纲: <method> by url and params
    假如存在接口数据"<factory>"并匹配查询参数"foo=bar&name=value1&name=value2":
      | someString  | someInt | someBoolean |
      | stringValue | 102     | false       |
    那么<method> "/beans" response code is 404
    那么<method> "/beans?foo=bar" response code is 404
    那么<method> "/beans?foo=bar&name=value1" response code is 404
    那么<method> "/beans?foo=bar&name=value1&name=value2" response code is 200 and body as below
    """
    {
      "someString": "stringValue",
      "someInt": 102,
      "someBoolean": false
    }
    """
    例子:
      | method | factory  |
      | GET    | Bean     |
      | POST   | PostBean |
      | PUT    | PutBean  |

  场景大纲: <method> by url and two path variables
    假如存在接口数据"<factory>"并匹配路径变量"foo=bar&name=value":
      | someString  | someInt | someBoolean |
      | stringValue | 102     | false       |
    那么<method> "/beans" response code is 404
    那么<method> "/beans/bar/another/value" response code is 200 and body as below
    """
    {
      "someString": "stringValue",
      "someInt": 102,
      "someBoolean": false
    }
    """
    例子:
      | method | factory                      |
      | GET    | BeanWithTwoPathVariables     |
      | POST   | PostBeanWithTwoPathVariables |
      | PUT    | PutBeanWithTwoPathVariables  |
