package com.yaoruozhou.jfactory.cucumber.put;

import com.yaoruozhou.jfactory.Request;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Request(path = "/beans/{foo}/sub-path", method = "PUT")
public class SubPathPutBeanWithPathVariable {

    private String someSubString;
    private int someSubInt;
    private boolean someSubBoolean;

}
