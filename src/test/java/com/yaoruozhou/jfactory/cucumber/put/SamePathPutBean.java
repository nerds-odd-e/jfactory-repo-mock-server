package com.yaoruozhou.jfactory.cucumber.put;

import com.yaoruozhou.jfactory.Request;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Request(path = "/beans", method = "PUT")
@Accessors(chain = true)
public class SamePathPutBean {

    private String anotherString;
    private int anotherInt;
    private boolean anotherBoolean;

}
