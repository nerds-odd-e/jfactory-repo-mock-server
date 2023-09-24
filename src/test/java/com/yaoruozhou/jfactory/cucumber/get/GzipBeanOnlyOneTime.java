package com.yaoruozhou.jfactory.cucumber.get;

import com.yaoruozhou.jfactory.Request;
import com.yaoruozhou.jfactory.Response;

@Request(path = "/beans")
@Response(times = 1, gzip = true)
public class GzipBeanOnlyOneTime extends Bean {
}
