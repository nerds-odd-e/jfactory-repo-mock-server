package com.yaoruozhou.jfactory.cucumber.put;

import com.github.leeonky.jfactory.Spec;

public class PutBeanForArrayOnlyOneTimes {

    public static class BeanSpec extends Spec<PutBeanForArrayOnlyOneTime> {
        @Override
        protected String getName() {
            return "PutBeanForArrayOnlyOneTime";
        }
    }
}
