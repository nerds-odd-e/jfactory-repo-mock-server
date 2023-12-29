package com.yaoruozhou.jfactory.cucumber.put;

import com.yaoruozhou.jfactory.Request;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Request(path = "/beans/sub-path", method = "PUT")
@Accessors(chain = true)
public class SubPathPutBean {

    private String someSubString;
    private int someSubInt;
    private boolean someSubBoolean;

}
