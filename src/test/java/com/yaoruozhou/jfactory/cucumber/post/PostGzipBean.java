package com.yaoruozhou.jfactory.cucumber.post;

import com.yaoruozhou.jfactory.Request;
import com.yaoruozhou.jfactory.Response;

@Request(path = "/beans", method = "POST")
@Response(gzip = true)
public class PostGzipBean extends PostBean {
}
