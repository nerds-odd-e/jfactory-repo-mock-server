package com.yaoruozhou.jfactory.cucumber.get;

import com.yaoruozhou.jfactory.Request;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Request(path = "/beans/sub-path")
@Accessors(chain = true)
public class SubPathBean {

    private String someSubString;
    private int someSubInt;
    private boolean someSubBoolean;

}
