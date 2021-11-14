# JFactory Repo MockServer

JFactory Repo MockServer 有两个作用：

* 提供了一个[JFactory](https://github.com/leeonky/jfactory/blob/master/README.md)
  中定义的[DataRepository](https://github.com/leeonky/jfactory/blob/master/src/main/java/com/github/leeonky/jfactory/DataRepository.java)
  的一个实现，将数据存入[MockServer](https://www.mock-server.com/)
* 提供了一系列 [Cucumber](https://cucumber.io/docs/installation/java/) Step 来准备 MockServer
  的数据，以此来区分[JFactory-Cucumber](https://github.com/leeonky/jfactory-cucumber/blob/master/README.md) 提供的 Step，实现在同一个
  Scenario 里面同时准备数据库和 MockServer 数据的效果

## 安装

通过Gradle添加依赖

```groovy
testImplementation 'com.yaoruozhou:jfactory-repo-mock-server:0.1.0'
```

## 单独使用

使用下面的代码可以创建一个将数据存入 MockServer 的 JFactory 实例。

```java
        JFactory jfactory=new JFactory(new MockServerDataRepositoryImpl(ClientAndServer.startClientAndServer(9081)));
        jfactory.register(YourMockServerApiResponses.Response.class);
```

## 与 [jfactory-repo-jpa](https://github.com/leeonky/jfactory-repo-jpa/blob/master/README.md) 一起使用（更加常见的场景）

JFactory Repo JPA 提供了一个将数据存入数据库的 DataRepository 实现，如果要同时使用这两个实现，那么需要实现一个 `CombinedDataRepository` 类，如下所示：

```java
public class CombinedDataRepository implements MockServerDataRepository {

  private final JPADataRepository jpaDataRepository;
  private final MockServerDataRepositoryImpl mockServerDataRepository;

  public CombinedDataRepository(EntityManager entityManager, MockServerClient mockServerClient) {
    jpaDataRepository = new JPADataRepository(entityManager);
    mockServerDataRepository = new MockServerDataRepositoryImpl(mockServerClient);
  }

  @Override
  public void setUrlParams(String urlParams) {
    mockServerDataRepository.setUrlParams(urlParams);
  }

  @Override
  public void setRootClass(Class<?> aClass) {
    mockServerDataRepository.setRootClass(aClass);
  }

  @Override
  public void setPathVariables(String pathVariables) {
    mockServerDataRepository.setPathVariables(pathVariables);
  }

  @Override
  public <T> Collection<T> queryAll(Class<T> aClass) {
    if (aClass.getPackage().getName().equals("your.mockserver.class.package")) {
      return mockServerDataRepository.queryAll(aClass);
    } else {
      return jpaDataRepository.queryAll(aClass);
    }
  }

  @Override
  public void save(Object o) {
    if (o.getClass().getPackage().getName().equals("your.mockserver.class.package")) {
      mockServerDataRepository.save(o);
    } else {
      jpaDataRepository.save(o);
    }
  }

  @Override
  public void clear() {
    jpaDataRepository.clear();
  }
}
```

关于这个 `CombinedDataRepository` 类有几点需要说明：

* 这个类实现了`MockServerDataRepository` 这个接口。该接口在 DataRepository
  的基础上增加了几个方法，这些方式会被[MockServerData](https://github.com/nerds-odd-e/jfactory-repo-mock-server/blob/master/src/main/java/com/yaoruozhou/jfactory/MockServerDataRepository.java)
  调用，提供MockServer数据准备所需要的额外信息
* 这个类通过 MockServer 实体类与 JPA 实体类所在不同的包来确定，哪些数据对象需要用 MockServerDataRepository 来 save 和 queryAll

有了上面的 `CombinedDataRepository`，就可以使用下面的代码可以创建一个能将数据存入 MockServer 和数据库的 JFactory 实例。

```java
        JFactory jfactory=new JFactory(new CombinedDataRepository(entityManagerFactory.createEntityManager(),ClientAndServer.startClientAndServer(9081)));
        jfactory.register(YourMockServerApiResponses.Response.class);
```

## MockServer Cucumber Step 使用说明

为 MockServer 打桩的 Api 准备数据，与在数据库中准备数据并不完全一样，因此 JFactory Repo MockServer 提供了一系列准备 Api 数据的 Step，其用法与 JFactory-Cucumber 提供的
Step 类似。

### 根据 MockServer 打桩的 Api Url 准备响应体数据

假如有下面的实体类，并且工厂类已经注册：

```java

@Getter
@Setter
@Request(path = "/beans")
public class Bean {

  private String someString;
  private int someInt;
  private boolean someBoolean;

}

public class Beans {

  public static class Factory extends Spec<Bean> {
    @Override
    protected String getName() {
      return "Bean";
    }
  }
}
```

值得注意的是，在`Bean`类定义中有一个`Request`注解，其`path`属性值为`/beans`。通过这个注解，JFactory Repo MockServer 会在 MockServer 上产生一个 /beans 的 Get
请求，并返回根据`Bean`生成的数据。

当用如下的 Cucumber Step 来创建对象时：

```gherkin
Exists api data "Bean":
| someString  | someInt | someBoolean |
| stringValue | 101     | true        |
```

那么向 /beans 发送 Get 请求会得到 200 的响应以及如下所示的响应体：

```json
{
  "someString": "stringValue",
  "someInt": 101,
  "someBoolean": true
}
```

### 根据 MockServer 打桩的 Api Url 准备响应体数据并且不需要设置数据

假如有下面的实体类，并且工厂类已经注册：

```java

@Getter
@Setter
@Request(path = "/beans")
public class Bean {

  private String someString;
  private int someInt;
  private boolean someBoolean;

}

public class Beans {

  public static class Factory extends Spec<Bean> {
    @Override
    protected String getName() {
      return "Bean";
    }
  }
}
```

当用如下的 Cucumber Step 来创建对象时：

```gherkin
Exists 1 api data "Bean"
```

那么向 /beans 发送 Get 请求会得到 200 的响应以及如下所示的响应体：

```json
{
  "someString": "someString#1",
  "someInt": 1,
  "someBoolean": true
}
```

值得注意的是，这里返回的数据都是由 JFactory 产生的默认值。

### 根据 MockServer 打桩的 Api Url 准备响应体数组数据

假如有下面的实体类，并且工厂类已经注册：

```java

@Getter
@Setter
@Request(path = "/beans")
@Response(type = JsonArray)
public class Bean {

  private String someString;
  private int someInt;
  private boolean someBoolean;

}

public class Beans {

  public static class Factory extends Spec<Bean> {
    @Override
    protected String getName() {
      return "Bean";
    }
  }
}
```

值得注意的是，在`Bean`类定义中有一个`Request`注解，其`path`属性值为`/beans`，以及另一个`Response`注解，其`type`属性值为`JsonArray`。通过这两个注解，JFactory Repo
MockServer 会在 MockServer 上产生一个 /beans 的 Get 请求，并返回根据`Bean`生成的数组数据。

当用如下的 Cucumber Step 来创建对象时：

```gherkin
Exists api data "Bean":
| someString  | someInt | someBoolean |
| stringValue | 101     | true        |
```

那么向 /beans 发送 Get 请求会得到 200 的响应以及如下所示的响应体：

```json
[
  {
    "someString": "stringValue",
    "someInt": 101,
    "someBoolean": true
  }
]
```

### 根据 MockServer 打桩的 Api Url 准备响应体数组数据并且不需要设置数据

假如有下面的实体类，并且工厂类已经注册：

```java

@Getter
@Setter
@Request(path = "/beans")
@Response(type = JsonArray)
public class Bean {

  private String someString;
  private int someInt;
  private boolean someBoolean;

}

public class Beans {

  public static class Factory extends Spec<Bean> {
    @Override
    protected String getName() {
      return "Bean";
    }
  }
}
```

当用如下的 Cucumber Step 来创建对象时：

```gherkin
Exists 1 api data "Bean"
```

那么向 /beans 发送 Get 请求会得到 200 的响应以及如下所示的响应体：

```json
[
  {
    "someString": "someString#1",
    "someInt": 1,
    "someBoolean": true
  }
]
```

### 根据 MockServer 打桩的 Api Url 和 QueryParam 准备响应体数据

假如有下面的实体类，并且工厂类已经注册：

```java

@Getter
@Setter
@Request(path = "/beans")
public class Bean {

  private String someString;
  private int someInt;
  private boolean someBoolean;

}

public class Beans {

  public static class Factory extends Spec<Bean> {
    @Override
    protected String getName() {
      return "Bean";
    }
  }
}
```

当用如下的 Cucumber Step 来创建对象时：

```gherkin
Exists api data "Bean" with params "foo=bar"
| someString  | someInt | someBoolean |
| stringValue | 102     | false       |
```

那么向 /beans?foo=bar 发送 Get 请求会得到 200 的响应以及如下所示的响应体：

```json
{
  "someString": "stringValue",
  "someInt": 102,
  "someBoolean": false
}
```

### 根据 MockServer 打桩的 Api Url 准备响应体多级数据

假如有下面的实体类，并且工厂类已经注册：

```java

@Getter
@Setter
@Request(path = "/beansWithChild")
public class BeanWithChild {

  private String someString;
  private int someInt;
  private boolean someBoolean;

  private ChildBean child;

  @Getter
  @Setter
  public static class ChildBean {
    private String yaString;
  }
}

public class BeansWithChild {

  public static class Factory extends Spec<BeanWithChild> {
    @Override
    protected String getName() {
      return "BeanWithChild";
    }
  }
}
```

当用如下的 Cucumber Step 来创建对象时：

```gherkin
Exists api data "BeanWithChild":
| someString  | someInt | someBoolean | child.yaString |
| stringValue | 101     | true        | childValue     |
```

那么向 /beansWithChild 发送 Get 请求会得到 200 的响应以及如下所示的响应体：

```json
{
  "someString": "stringValue",
  "someInt": 101,
  "someBoolean": true,
  "child": {
    "yaString": "childValue"
  }
}
```

### 根据 MockServer 打桩的 Api Url 和 PathVariable 准备响应体数据

假如有下面的实体类，并且工厂类已经注册：

```java

@Getter
@Setter
@Request(path = "/beans/{foo}")
public class Bean {

  private String someString;
  private int someInt;
  private boolean someBoolean;

}

public class Beans {

  public static class Factory extends Spec<Bean> {
    @Override
    protected String getName() {
      return "Bean";
    }
  }
}
```

当用如下的 Cucumber Step 来创建对象时：

```gherkin
Exists api data "Bean" with path variables "foo=bar"
| someString  | someInt | someBoolean |
| stringValue | 102     | false       |
```

那么向 /beans/bar 发送 Get 请求会得到 200 的响应以及如下所示的响应体：

```json
{
  "someString": "stringValue",
  "someInt": 102,
  "someBoolean": false
}
```

## 注解使用说明

### Request

该注解用来定义 MockServer 打桩的 Api 请求，属性如下：

| 属性名 | 属性值 | 
| --- | --- |
| path | 必填，如 /beans，不能包含 query param |
| method | 选填，默认值为 GET，有效值为 GET，POST，PUT，不区分大小写 |

此注解必须定义。

### Response

该注解用来定义 MockServer 打桩的 Api 响应体，属性如下：

| 属性名 | 属性值 | 
| --- | --- |
| type | 选填，默认值为 JsonObject，有效值为 JsonObject 和 JsonArray |

此注解可以不定义。如果不定义，响应体默认返回 JsonObject。

## 更多文档请看下面

* [请先阅读 JFactory-Cucumber 的文档](https://github.com/leeonky/jfactory-cucumber/blob/master/README.md)
* [请先阅读 JFactory 的文档](https://github.com/leeonky/jfactory/blob/master/README.md)
* [请先阅读 DAL-java 的文档](https://github.com/leeonky/DAL-java/blob/master/README.md)
