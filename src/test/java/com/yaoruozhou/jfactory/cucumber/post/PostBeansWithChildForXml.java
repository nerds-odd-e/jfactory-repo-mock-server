package com.yaoruozhou.jfactory.cucumber.post;

import com.github.leeonky.jfactory.Spec;

public class PostBeansWithChildForXml {

    public static class BeanFactory extends Spec<PostBeanWithChildForXml> {
        @Override
        protected String getName() {
            return "PostBeanWithChildForXml";
        }
    }
}
