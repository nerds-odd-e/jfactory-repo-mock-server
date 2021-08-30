package com.odde.jfactory.cucumber.put;

import com.odde.jfactory.Request;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Request(path = "/beans/{foo}/another/{name}", method = "PUT")
public class PutBeanWithTwoPathVariables {

    private String someString;
    private int someInt;
    private boolean someBoolean;

}
