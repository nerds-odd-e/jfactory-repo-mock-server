package com.yaoruozhou.jfactory.cucumber.put;

import com.yaoruozhou.jfactory.Request;
import com.yaoruozhou.jfactory.Response;

@Request(path = "/beans", method = "PUT")
@Response(gzip = true)
public class PutGzipBean extends PutBean {
}
