package com.yaoruozhou.jfactory.cucumber.post;

import com.yaoruozhou.jfactory.Request;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Request(path = "/beans/{foo}/sub-path", method = "POST")
public class SubPathPostBeanWithPathVariable {

    private String someSubString;
    private int someSubInt;
    private boolean someSubBoolean;

}
