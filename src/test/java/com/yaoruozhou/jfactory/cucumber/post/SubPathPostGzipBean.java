package com.yaoruozhou.jfactory.cucumber.post;

import com.yaoruozhou.jfactory.Request;
import com.yaoruozhou.jfactory.Response;

@Request(path = "/beans/sub-path", method = "POST")
@Response(gzip = true)
public class SubPathPostGzipBean extends SubPathPostBean {
}
