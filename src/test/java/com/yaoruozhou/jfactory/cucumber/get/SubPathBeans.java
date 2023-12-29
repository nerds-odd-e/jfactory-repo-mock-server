package com.yaoruozhou.jfactory.cucumber.get;

import com.github.leeonky.jfactory.Spec;

public class SubPathBeans {

    public static class BeanFactory extends Spec<SubPathBean> {
        @Override
        protected String getName() {
            return "SubPathBean";
        }

    }
}
