package com.odde.jfactory.cucumber.put;

import com.odde.jfactory.Request;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Request(path = "/beansWithChild", method = "PUT")
public class PutBeanWithChild {

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
