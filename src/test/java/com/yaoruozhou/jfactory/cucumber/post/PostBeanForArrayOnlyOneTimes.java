package com.yaoruozhou.jfactory.cucumber.post;

import com.github.leeonky.jfactory.Spec;

public class PostBeanForArrayOnlyOneTimes {

    public static class BeanSpec extends Spec<PostBeanForArrayOnlyOneTime> {
        @Override
        protected String getName() {
            return "PostBeanForArrayOnlyOneTime";
        }
    }
}
