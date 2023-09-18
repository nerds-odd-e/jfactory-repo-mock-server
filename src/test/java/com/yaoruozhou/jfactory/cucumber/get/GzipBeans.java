package com.yaoruozhou.jfactory.cucumber.get;

import com.github.leeonky.jfactory.Spec;

public class GzipBeans {

    public static class BeanSpec extends Spec<GzipBean> {
        @Override
        protected String getName() {
            return "GzipBean";
        }
    }
}
