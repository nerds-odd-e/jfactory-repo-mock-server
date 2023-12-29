package com.yaoruozhou.jfactory.cucumber.put;

import com.github.leeonky.jfactory.Spec;

public class SamePathPutBeans {

    public static class BeanFactory extends Spec<SamePathPutBean> {
        @Override
        protected String getName() {
            return "SamePathPutBean";
        }
    }
}
