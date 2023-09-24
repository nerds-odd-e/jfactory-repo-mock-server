package com.yaoruozhou.jfactory.cucumber.post;

import com.yaoruozhou.jfactory.Request;
import com.yaoruozhou.jfactory.Response;

@Request(path = "/beans", method = "POST")
@Response(gzip = true, times = 1)
public class PostGzipBeanOnlyOneTime extends PostBean {
}
