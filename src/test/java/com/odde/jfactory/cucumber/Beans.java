package com.odde.jfactory.cucumber;

import com.github.leeonky.jfactory.Spec;

public class Beans {

    public static class BeanFactory extends Spec<Bean> {
        @Override
        protected String getName() {
            return "Bean";
        }
    }
}
