package com.yaoruozhou.jfactory.cucumber.put;

import com.yaoruozhou.jfactory.Request;
import com.yaoruozhou.jfactory.Response;

import static com.yaoruozhou.jfactory.Response.Type.Xml;

@Request(path = "/beansWithChild", method = "PUT")
@Response(type = Xml)
public class PutBeanWithChildForXml extends PutBeanWithChild {
}
