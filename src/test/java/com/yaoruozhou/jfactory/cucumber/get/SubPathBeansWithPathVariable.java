package com.yaoruozhou.jfactory.cucumber.get;

import com.github.leeonky.jfactory.Spec;

public class SubPathBeansWithPathVariable {

    public static class BeanFactory extends Spec<SubPathBeanWithPathVariable> {
        @Override
        protected String getName() {
            return "SubPathBeanWithPathVariable";
        }
    }
}
