package com.odde.jfactory.cucumber.put;

import com.github.leeonky.jfactory.Spec;

public class PutBeansWithChild {

    public static class BeanFactory extends Spec<PutBeanWithChild> {
        @Override
        protected String getName() {
            return "PutBeanWithChild";
        }
    }
}
