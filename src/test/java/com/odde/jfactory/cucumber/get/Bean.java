package com.odde.jfactory.cucumber.get;

import com.odde.jfactory.Request;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Request(path = "/beans")
@Accessors(chain = true)
public class Bean {

    private String someString;
    private int someInt;
    private boolean someBoolean;

}
