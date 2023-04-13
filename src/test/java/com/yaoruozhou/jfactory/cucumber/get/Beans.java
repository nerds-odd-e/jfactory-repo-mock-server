package com.yaoruozhou.jfactory.cucumber.get;

import com.github.leeonky.jfactory.Spec;
import com.github.leeonky.jfactory.Trait;

public class Beans {

    public static class BeanFactory extends Spec<Bean> {
        @Override
        protected String getName() {
            return "Bean";
        }

        @Trait
        public void strValue() {
            property("someString").value("someStringValue");
        }

        @Trait
        public void intValue() {
            property("someInt").value(42);
        }

    }
}
