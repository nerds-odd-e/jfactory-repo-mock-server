package com.yaoruozhou.jfactory.cucumber.post;

import com.yaoruozhou.jfactory.Request;
import com.yaoruozhou.jfactory.Response;

@Request(path = "/beans", method = "POST")
@Response(times = 1)
public class PostBeanOnlyOneTime extends PostBean {
}
