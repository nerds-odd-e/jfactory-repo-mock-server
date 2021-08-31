package com.yaoruozhou.jfactory.cucumber;

import com.github.leeonky.jfactory.DataRepository;
import com.github.leeonky.jfactory.JFactory;
import com.yaoruozhou.jfactory.cucumber.get.*;
import com.yaoruozhou.jfactory.cucumber.post.*;
import com.yaoruozhou.jfactory.cucumber.put.*;

public class EntityFactory extends JFactory {

    public EntityFactory(DataRepository dataRepository) {
        super(dataRepository);
        configFactory();
    }

    private void configFactory() {
        register(Beans.BeanFactory.class);
        register(BeanForArrays.BeanFactory.class);
        register(BeansWithChild.BeanFactory.class);
        register(BeansWithPathVariable.BeanFactory.class);
        register(BeansWithTwoPathVariables.BeanFactory.class);
        register(PostBeans.BeanFactory.class);
        register(PostBeanForArrays.BeanFactory.class);
        register(PostBeansWithChild.BeanFactory.class);
        register(PostBeansWithPathVariable.BeanFactory.class);
        register(PostBeansWithTwoPathVariables.BeanFactory.class);
        register(PutBeans.BeanFactory.class);
        register(PutBeanForArrays.BeanFactory.class);
        register(PutBeansWithChild.BeanFactory.class);
        register(PutBeansWithPathVariable.BeanFactory.class);
        register(PutBeansWithTwoPathVariables.BeanFactory.class);
    }
}
