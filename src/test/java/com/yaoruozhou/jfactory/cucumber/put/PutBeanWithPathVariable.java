package com.yaoruozhou.jfactory.cucumber.put;

import com.yaoruozhou.jfactory.Request;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Request(path = "/beans/{foo}", method = "PUT")
public class PutBeanWithPathVariable {

    private String someString;
    private int someInt;
    private boolean someBoolean;

}
