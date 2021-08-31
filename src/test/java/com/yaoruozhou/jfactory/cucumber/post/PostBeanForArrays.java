package com.yaoruozhou.jfactory.cucumber.post;

import com.github.leeonky.jfactory.Spec;

public class PostBeanForArrays {

    public static class BeanFactory extends Spec<PostBeanForArray> {
        @Override
        protected String getName() {
            return "PostBeanForArray";
        }
    }
}
