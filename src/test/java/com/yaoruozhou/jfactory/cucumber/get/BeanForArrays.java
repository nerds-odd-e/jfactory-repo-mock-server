package com.yaoruozhou.jfactory.cucumber.get;

import com.github.leeonky.jfactory.Spec;

public class BeanForArrays {

    public static class BeanFactory extends Spec<BeanForArray> {
        @Override
        protected String getName() {
            return "BeanForArray";
        }
    }
}
