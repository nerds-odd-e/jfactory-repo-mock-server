package com.yaoruozhou.jfactory.cucumber.post;

import com.yaoruozhou.jfactory.Request;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Request(path = "/beans", method = "POST")
@Accessors(chain = true)
public class PostBean {

    private String someString;
    private int someInt;
    private boolean someBoolean;

}
