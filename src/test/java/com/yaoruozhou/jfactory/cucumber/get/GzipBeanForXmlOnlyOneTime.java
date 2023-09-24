package com.yaoruozhou.jfactory.cucumber.get;

import com.yaoruozhou.jfactory.Request;
import com.yaoruozhou.jfactory.Response;

import static com.yaoruozhou.jfactory.Response.Type.Xml;

@Request(path = "/beans")
@Response(type = Xml, gzip = true, times = 1)
public class GzipBeanForXmlOnlyOneTime extends BeanForXml {
}
