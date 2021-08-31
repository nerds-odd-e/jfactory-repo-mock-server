package com.yaoruozhou.jfactory.cucumber.get;

import com.yaoruozhou.jfactory.Request;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Request(path = "/beans/{foo}/another/{name}")
public class BeanWithTwoPathVariables {

    private String someString;
    private int someInt;
    private boolean someBoolean;

}
