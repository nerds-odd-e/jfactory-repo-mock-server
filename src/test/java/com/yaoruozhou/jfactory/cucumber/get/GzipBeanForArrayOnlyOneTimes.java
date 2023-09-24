package com.yaoruozhou.jfactory.cucumber.get;

import com.github.leeonky.jfactory.Spec;

public class GzipBeanForArrayOnlyOneTimes {

    public static class BeanSpec extends Spec<GzipBeanForArrayOnlyOneTime> {
        @Override
        protected String getName() {
            return "GzipBeanForArrayOnlyOneTime";
        }
    }
}
