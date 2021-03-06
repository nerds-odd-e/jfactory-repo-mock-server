package com.yaoruozhou.jfactory.cucumber.get;

import com.yaoruozhou.jfactory.Request;
import lombok.Getter;
import lombok.Setter;

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
