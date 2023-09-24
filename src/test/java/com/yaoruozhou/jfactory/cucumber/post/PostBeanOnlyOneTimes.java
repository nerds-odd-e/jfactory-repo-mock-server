package com.yaoruozhou.jfactory.cucumber.post;

import com.github.leeonky.jfactory.Spec;

public class PostBeanOnlyOneTimes {

    public static class BeanSpec extends Spec<PostBeanOnlyOneTime> {
        @Override
        protected String getName() {
            return "PostBeanOnlyOneTime";
        }
    }
}
