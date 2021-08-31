package com.yaoruozhou.jfactory.cucumber.put;

import com.github.leeonky.jfactory.Spec;

public class PutBeans {

    public static class BeanFactory extends Spec<PutBean> {
        @Override
        protected String getName() {
            return "PutBean";
        }
    }
}
