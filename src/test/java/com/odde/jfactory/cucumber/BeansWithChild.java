package com.odde.jfactory.cucumber;

import com.github.leeonky.jfactory.Spec;

public class BeansWithChild {

    public static class BeanWithChildFactory extends Spec<BeanWithChild> {
        @Override
        protected String getName() {
            return "BeanWithChild";
        }
    }
}
