package com.odde.jfactory.cucumber.get;

import com.github.leeonky.jfactory.Spec;

public class BeansWithTwoPathVariables {

    public static class BeanFactory extends Spec<BeanWithTwoPathVariables> {
        @Override
        protected String getName() {
            return "BeanWithTwoPathVariables";
        }
    }
}
