package com.yaoruozhou.jfactory.cucumber.get;

import com.github.leeonky.jfactory.Spec;

public class BeanForXmls {

    public static class BeanSpec extends Spec<BeanForXml> {
        @Override
        protected String getName() {
            return "BeanForXml";
        }
    }
}
