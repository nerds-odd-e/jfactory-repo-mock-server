package com.yaoruozhou.jfactory.cucumber.get;

import com.github.leeonky.jfactory.Spec;

public class BeansWithChildForXml {

    public static class BeanFactory extends Spec<BeanWithChildForXml> {
        @Override
        protected String getName() {
            return "BeanWithChildForXml";
        }
    }
}
