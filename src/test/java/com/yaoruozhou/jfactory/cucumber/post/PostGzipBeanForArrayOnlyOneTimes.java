package com.yaoruozhou.jfactory.cucumber.post;

import com.github.leeonky.jfactory.Spec;

public class PostGzipBeanForArrayOnlyOneTimes {

    public static class BeanSpec extends Spec<PostGzipBeanForArrayOnlyOneTime> {
        @Override
        protected String getName() {
            return "PostGzipBeanForArrayOnlyOneTime";
        }
    }
}
