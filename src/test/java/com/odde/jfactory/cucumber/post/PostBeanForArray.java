package com.odde.jfactory.cucumber.post;

import com.odde.jfactory.Request;
import com.odde.jfactory.Response;

import static com.odde.jfactory.Response.Type.JsonArray;

@Request(path = "/beans", method = "POST")
@Response(type = JsonArray)
public class PostBeanForArray extends PostBean {
}
