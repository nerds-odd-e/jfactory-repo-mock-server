package com.yaoruozhou.jfactory.cucumber.post;

import com.yaoruozhou.jfactory.Request;
import com.yaoruozhou.jfactory.Response;

import static com.yaoruozhou.jfactory.Response.Type.Xml;

@Request(path = "/beansWithChild", method = "POST")
@Response(type = Xml)
public class PostBeanWithChildForXml extends PostBeanWithChild {
}
