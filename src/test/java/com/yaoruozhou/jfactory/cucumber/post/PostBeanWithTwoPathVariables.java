package com.yaoruozhou.jfactory.cucumber.post;

import com.yaoruozhou.jfactory.Request;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Request(path = "/beans/{foo}/another/{name}", method = "POST")
public class PostBeanWithTwoPathVariables {

    private String someString;
    private int someInt;
    private boolean someBoolean;

}
