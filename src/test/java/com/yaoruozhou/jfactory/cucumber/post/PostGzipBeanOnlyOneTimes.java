package com.yaoruozhou.jfactory.cucumber.post;

import com.github.leeonky.jfactory.Spec;

public class PostGzipBeanOnlyOneTimes {

    public static class BeanSpec extends Spec<PostGzipBeanOnlyOneTime> {
        @Override
        protected String getName() {
            return "PostGzipBeanOnlyOneTime";
        }
    }
}
