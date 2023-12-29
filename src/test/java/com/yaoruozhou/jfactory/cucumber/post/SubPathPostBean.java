package com.yaoruozhou.jfactory.cucumber.post;

import com.yaoruozhou.jfactory.Request;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Request(path = "/beans/sub-path", method = "POST")
@Accessors(chain = true)
public class SubPathPostBean {

    private String someSubString;
    private int someSubInt;
    private boolean someSubBoolean;

}
