package com.yaoruozhou.jfactory.cucumber.put;

import com.github.leeonky.jfactory.Spec;

public class PutBeanForXmlOnlyOneTimes {

    public static class BeanSpec extends Spec<PutBeanForXmlOnlyOneTime> {
        @Override
        protected String getName() {
            return "PutBeanForXmlOnlyOneTime";
        }
    }
}
