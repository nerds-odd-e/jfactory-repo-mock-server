package com.yaoruozhou.jfactory.cucumber.get;

import com.github.leeonky.jfactory.Spec;

public class BeansWithPathVariable {

    public static class BeanFactory extends Spec<BeanWithPathVariable> {
        @Override
        protected String getName() {
            return "BeanWithPathVariable";
        }
    }
}
