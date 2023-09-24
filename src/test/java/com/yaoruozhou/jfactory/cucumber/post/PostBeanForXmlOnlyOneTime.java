package com.yaoruozhou.jfactory.cucumber.post;

import com.yaoruozhou.jfactory.Request;
import com.yaoruozhou.jfactory.Response;

import static com.yaoruozhou.jfactory.Response.Type.Xml;

@Request(path = "/beans", method = "POST")
@Response(type = Xml, times = 1)
public class PostBeanForXmlOnlyOneTime extends PostBean {
}
