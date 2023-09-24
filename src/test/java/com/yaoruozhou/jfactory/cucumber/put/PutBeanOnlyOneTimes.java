package com.yaoruozhou.jfactory.cucumber.put;

import com.github.leeonky.jfactory.Spec;

public class PutBeanOnlyOneTimes {

    public static class BeanSpec extends Spec<PutBeanOnlyOneTime> {
        @Override
        protected String getName() {
            return "PutBeanOnlyOneTime";
        }
    }
}
