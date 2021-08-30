package com.odde.jfactory.cucumber.get;

import com.github.leeonky.jfactory.Spec;

public class BeansWithChild {

    public static class BeanFactory extends Spec<BeanWithChild> {
        @Override
        protected String getName() {
            return "BeanWithChild";
        }
    }
}
