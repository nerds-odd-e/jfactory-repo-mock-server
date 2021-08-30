package com.odde.jfactory.cucumber;

import com.odde.jfactory.Request;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Request(path = "/beans/{foo}")
public class BeanWithPathVariable {

    private String someString;
    private int someInt;
    private boolean someBoolean;

}
