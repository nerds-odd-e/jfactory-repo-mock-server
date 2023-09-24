package com.yaoruozhou.jfactory.cucumber.post;

import com.yaoruozhou.jfactory.Request;
import com.yaoruozhou.jfactory.Response;

import static com.yaoruozhou.jfactory.Response.Type.JsonArray;

@Request(path = "/beans", method = "POST")
@Response(type = JsonArray, times = 1)
public class PostBeanForArrayOnlyOneTime extends PostBean {
}
