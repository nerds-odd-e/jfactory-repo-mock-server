package com.yaoruozhou.jfactory.cucumber.get;

import com.yaoruozhou.jfactory.Request;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Request(path = "/beans/{foo}/sub-path")
public class SubPathBeanWithPathVariable {

    private String someSubString;
    private int someSubInt;
    private boolean someSubBoolean;

}
