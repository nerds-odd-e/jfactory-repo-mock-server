package com.odde.jfactory.cucumber;

import com.github.leeonky.jfactory.DataRepository;
import com.github.leeonky.jfactory.JFactory;
import com.odde.jfactory.cucumber.get.*;
import com.odde.jfactory.cucumber.post.*;

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
    }
}
