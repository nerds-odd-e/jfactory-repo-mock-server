package com.odde.jfactory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Request(path = "/beans")
public class Bean {

    private String someString;
    private int someInt;
    private boolean someBoolean;

}
