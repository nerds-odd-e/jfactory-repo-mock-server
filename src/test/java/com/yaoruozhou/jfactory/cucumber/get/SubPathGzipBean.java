package com.yaoruozhou.jfactory.cucumber.get;

import com.yaoruozhou.jfactory.Request;
import com.yaoruozhou.jfactory.Response;

@Request(path = "/beans/sub-path")
@Response(gzip = true)
public class SubPathGzipBean extends SubPathBean {
}
