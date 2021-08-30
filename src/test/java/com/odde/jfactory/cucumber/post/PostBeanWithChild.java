package com.odde.jfactory.cucumber.post;

import com.odde.jfactory.Request;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Request(path = "/beansWithChild", method = "POST")
public class PostBeanWithChild {

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
