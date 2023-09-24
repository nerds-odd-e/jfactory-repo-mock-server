package com.yaoruozhou.jfactory.cucumber.get;

import com.github.leeonky.jfactory.Spec;

public class BeanForXmlOnlyOneTimes {

    public static class BeanSpec extends Spec<BeanForXmlOnlyOneTime> {
        @Override
        protected String getName() {
            return "BeanForXmlOnlyOneTime";
        }
    }
}
