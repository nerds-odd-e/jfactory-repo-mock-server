package com.yaoruozhou.jfactory.cucumber.post;

import com.github.leeonky.jfactory.Spec;

public class PostGzipBeans {

    public static class BeanSpec extends Spec<PostGzipBean> {
        @Override
        protected String getName() {
            return "PostGzipBean";
        }
    }
}
