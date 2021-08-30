package com.odde.jfactory.cucumber.put;

import com.github.leeonky.jfactory.Spec;

public class PutBeansWithPathVariable {

    public static class BeanFactory extends Spec<PutBeanWithPathVariable> {
        @Override
        protected String getName() {
            return "PutBeanWithPathVariable";
        }
    }
}
