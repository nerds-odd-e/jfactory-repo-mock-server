package com.yaoruozhou.jfactory.cucumber.put;

import com.github.leeonky.jfactory.Spec;

public class PutGzipBeans {

    public static class BeanSpec extends Spec<PutGzipBean> {
        @Override
        protected String getName() {
            return "PutGzipBean";
        }
    }
}
