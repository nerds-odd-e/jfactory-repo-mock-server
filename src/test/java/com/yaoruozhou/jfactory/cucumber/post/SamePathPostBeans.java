package com.yaoruozhou.jfactory.cucumber.post;

import com.github.leeonky.jfactory.Spec;

public class SamePathPostBeans {

    public static class BeanFactory extends Spec<SamePathPostBean> {
        @Override
        protected String getName() {
            return "SamePathPostBean";
        }
    }
}
