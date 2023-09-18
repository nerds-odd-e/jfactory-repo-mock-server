package com.yaoruozhou.jfactory.cucumber.get;

import com.yaoruozhou.jfactory.Request;
import com.yaoruozhou.jfactory.Response;

@Request(path = "/beans")
@Response(gzip = true)
public class GzipBean extends Bean {
}
