package com.odde.jfactory.cucumber;

import com.odde.jfactory.Request;
import com.odde.jfactory.Response;

import static com.odde.jfactory.Response.Type.JsonArray;

@Request(path = "/beans")
@Response(type = JsonArray)
public class BeanForArray extends Bean {
}
