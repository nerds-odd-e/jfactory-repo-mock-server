package com.yaoruozhou.jfactory.cucumber.put;

import com.yaoruozhou.jfactory.Request;
import com.yaoruozhou.jfactory.Response;

import static com.yaoruozhou.jfactory.Response.Type.JsonArray;

@Request(path = "/beans", method = "PUT")
@Response(type = JsonArray, gzip = true, times = 1)
public class PutGzipBeanForArrayOnlyOneTime extends PutBeanForArray {
}
