package com.yaoruozhou.jfactory.cucumber.get;

import com.yaoruozhou.jfactory.Request;
import com.yaoruozhou.jfactory.Response;

import static com.yaoruozhou.jfactory.Response.Type.JsonArray;

@Request(path = "/beans")
@Response(type = JsonArray, gzip = true)
public class GzipBeanForArray extends BeanForArray {
}
