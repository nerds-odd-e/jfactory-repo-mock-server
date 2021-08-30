package com.odde.jfactory.cucumber.post;

import com.github.leeonky.jfactory.Spec;

public class PostBeansWithPathVariable {

    public static class BeanFactory extends Spec<PostBeanWithPathVariable> {
        @Override
        protected String getName() {
            return "PostBeanWithPathVariable";
        }
    }
}
