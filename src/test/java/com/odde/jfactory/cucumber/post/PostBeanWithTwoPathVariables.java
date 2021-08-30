package com.odde.jfactory.cucumber.post;

import com.odde.jfactory.Request;
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
