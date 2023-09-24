package com.yaoruozhou.jfactory.cucumber.get;

import com.github.leeonky.jfactory.Spec;

public class BeanOnlyOneTimes {

    public static class BeanSpec extends Spec<BeanOnlyOneTime> {
        @Override
        protected String getName() {
            return "BeanOnlyOneTime";
        }
    }
}
