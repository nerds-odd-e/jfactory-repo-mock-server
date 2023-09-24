package com.yaoruozhou.jfactory.cucumber.post;

import com.github.leeonky.jfactory.Spec;

public class PostGzipBeanForXmlOnlyOneTimes {

    public static class BeanSpec extends Spec<PostGzipBeanForXmlOnlyOneTime> {
        @Override
        protected String getName() {
            return "PostGzipBeanForXmlOnlyOneTime";
        }
    }
}
