package com.yaoruozhou.jfactory.cucumber.get;

import com.yaoruozhou.jfactory.Request;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Request(path = "/beans")
@Accessors(chain = true)
public class SamePathBean {

    private String anotherString;
    private int anotherInt;
    private boolean anotherBoolean;

}
