package com.yaoruozhou.jfactory.cucumber.get;

import com.github.leeonky.jfactory.Spec;

public class GzipBeanOnlyOneTimes {

    public static class BeanSpec extends Spec<GzipBeanOnlyOneTime> {
        @Override
        protected String getName() {
            return "GzipBeanOnlyOneTime";
        }
    }
}
