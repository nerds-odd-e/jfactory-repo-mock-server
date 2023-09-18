package com.yaoruozhou.jfactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.yaoruozhou.jfactory.Response.Type.JsonObject;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Response {

    Type type() default JsonObject;

    enum Type {
        JsonObject, JsonArray, Xml
    }
}
