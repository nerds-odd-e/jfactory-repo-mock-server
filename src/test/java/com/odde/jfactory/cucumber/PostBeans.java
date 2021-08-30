package com.odde.jfactory.cucumber;

import com.github.leeonky.jfactory.Spec;

public class PostBeans {

    public static class BeanFactory extends Spec<PostBean> {
        @Override
        protected String getName() {
            return "PostBean";
        }
    }
}
