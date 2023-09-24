package com.yaoruozhou.jfactory.cucumber.get;

import com.github.leeonky.jfactory.Spec;

public class BeanForArrayOnlyOneTimes {

    public static class BeanSpec extends Spec<BeanForArrayOnlyOneTime> {
        @Override
        protected String getName() {
            return "BeanForArrayOnlyOneTime";
        }
    }
}
