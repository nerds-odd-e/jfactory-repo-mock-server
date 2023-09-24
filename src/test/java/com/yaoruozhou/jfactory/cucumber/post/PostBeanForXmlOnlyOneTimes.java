package com.yaoruozhou.jfactory.cucumber.post;

import com.github.leeonky.jfactory.Spec;

public class PostBeanForXmlOnlyOneTimes {

    public static class BeanSpec extends Spec<PostBeanForXmlOnlyOneTime> {
        @Override
        protected String getName() {
            return "PostBeanForXmlOnlyOneTime";
        }
    }
}
