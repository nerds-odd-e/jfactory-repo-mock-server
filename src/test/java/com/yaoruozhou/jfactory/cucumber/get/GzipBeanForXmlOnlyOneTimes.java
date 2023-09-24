package com.yaoruozhou.jfactory.cucumber.get;

import com.github.leeonky.jfactory.Spec;

public class GzipBeanForXmlOnlyOneTimes {

    public static class BeanSpec extends Spec<GzipBeanForXmlOnlyOneTime> {
        @Override
        protected String getName() {
            return "GzipBeanForXmlOnlyOneTime";
        }
    }
}
