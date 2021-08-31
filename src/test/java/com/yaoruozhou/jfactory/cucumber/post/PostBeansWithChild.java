package com.yaoruozhou.jfactory.cucumber.post;

import com.github.leeonky.jfactory.Spec;

public class PostBeansWithChild {

    public static class BeanFactory extends Spec<PostBeanWithChild> {
        @Override
        protected String getName() {
            return "PostBeanWithChild";
        }
    }
}
