package com.yaoruozhou.jfactory.cucumber.get;

import com.github.leeonky.jfactory.Spec;

public class SamePathBeans {

    public static class BeanFactory extends Spec<SamePathBean> {
        @Override
        protected String getName() {
            return "SamePathBean";
        }

    }
}
