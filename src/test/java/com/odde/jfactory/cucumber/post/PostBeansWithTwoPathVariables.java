package com.odde.jfactory.cucumber.post;

import com.github.leeonky.jfactory.Spec;

public class PostBeansWithTwoPathVariables {

    public static class BeanFactory extends Spec<PostBeanWithTwoPathVariables> {
        @Override
        protected String getName() {
            return "PostBeanWithTwoPathVariables";
        }
    }
}
