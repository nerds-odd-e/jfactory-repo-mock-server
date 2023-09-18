package com.yaoruozhou.jfactory.cucumber.post;

import com.github.leeonky.jfactory.Spec;

public class PostBeanForXmls {

    public static class BeanFactory extends Spec<PostBeanForXml> {
        @Override
        protected String getName() {
            return "PostBeanForXml";
        }
    }
}
