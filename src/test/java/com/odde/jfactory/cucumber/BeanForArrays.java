package com.odde.jfactory.cucumber;

import com.github.leeonky.jfactory.Spec;

public class BeanForArrays {

    public static class BeanForArrayFactory extends Spec<BeanForArray> {
        @Override
        protected String getName() {
            return "BeanForArray";
        }
    }
}
