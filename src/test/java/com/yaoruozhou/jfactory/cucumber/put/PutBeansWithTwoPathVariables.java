package com.yaoruozhou.jfactory.cucumber.put;

import com.github.leeonky.jfactory.Spec;

public class PutBeansWithTwoPathVariables {

    public static class BeanFactory extends Spec<PutBeanWithTwoPathVariables> {
        @Override
        protected String getName() {
            return "PutBeanWithTwoPathVariables";
        }
    }
}
