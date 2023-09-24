package com.yaoruozhou.jfactory.cucumber.put;

import com.github.leeonky.jfactory.Spec;

public class PutGzipBeanOnlyOneTimes {

    public static class BeanSpec extends Spec<PutGzipBeanOnlyOneTime> {
        @Override
        protected String getName() {
            return "PutGzipBeanOnlyOneTime";
        }
    }
}
