package com.yaoruozhou.jfactory.cucumber.put;

import com.yaoruozhou.jfactory.Request;
import com.yaoruozhou.jfactory.Response;

@Request(path = "/beans/sub-path", method = "PUT")
@Response(gzip = true)
public class SubPathPutGzipBean extends SubPathPutBean {
}
