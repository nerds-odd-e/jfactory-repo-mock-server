package com.yaoruozhou.jfactory.cucumber.post;

import com.github.leeonky.jfactory.Spec;

public class SubPathPostBeans {

    public static class BeanFactory extends Spec<SubPathPostBean> {
        @Override
        protected String getName() {
            return "SubPathPostBean";
        }
    }
}
