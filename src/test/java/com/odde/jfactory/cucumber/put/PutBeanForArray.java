package com.odde.jfactory.cucumber.put;

import com.odde.jfactory.Request;
import com.odde.jfactory.Response;

import static com.odde.jfactory.Response.Type.JsonArray;

@Request(path = "/beans", method = "PUT")
@Response(type = JsonArray)
public class PutBeanForArray extends PutBean {
}
