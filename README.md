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

``` groovy
    testImplementation 'com.yaoruozhou:jfactory-repo-mock-server:0.1.0'
```

## 单独使用

使用下面的代码可以创建一个将数据存入 MockServer 的 JFactory 实例。

```java
JFactory jfactory=new JFactory(new MockServerDataRepositoryImpl(ClientAndServer.startClientAndServer(9081)));
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

*

这个类实现了[MockServerDataRepository](https://github.com/nerds-odd-e/jfactory-repo-mock-server/blob/master/src/main/java/com/yaoruozhou/jfactory/MockServerDataRepository.java)
这个接口。该接口在 DataRepository 的基础上增加了几个方法，这些方式会被 MockServer Cucumber
Step [MockServerData](https://github.com/nerds-odd-e/jfactory-repo-mock-server/blob/master/src/main/java/com/yaoruozhou/jfactory/MockServerDataRepository.java)
调用，提供 MockServer 数据准备所需要的额外信息

* 这个类通过 MockServer 实体类与 JPA 实体类所在不同的包来确定，哪些数据对象需要用 MockServerDataRepository 来 save 和 queryAll

有了上面的 `CombinedDataRepository`，就可以使用下面的代码可以创建一个能将数据存入 MockServer 和数据库的 JFactory 实例。

```java
@PersistenceUnit
private EntityManagerFactory entityManagerFactory;

        JFactory jfactory=new JFactory(new CombinedDataRepository(entityManagerFactory.createEntityManager(),ClientAndServer.startClientAndServer(9081)));
```
