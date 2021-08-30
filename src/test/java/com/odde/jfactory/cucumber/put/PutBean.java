package com.odde.jfactory.cucumber.put;

import com.odde.jfactory.Request;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Request(path = "/beans", method = "PUT")
@Accessors(chain = true)
public class PutBean {

    private String someString;
    private int someInt;
    private boolean someBoolean;

}
