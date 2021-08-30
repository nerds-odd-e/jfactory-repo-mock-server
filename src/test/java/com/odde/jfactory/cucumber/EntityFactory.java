package com.odde.jfactory.cucumber;

import com.github.leeonky.jfactory.DataRepository;
import com.github.leeonky.jfactory.JFactory;

public class EntityFactory extends JFactory {

    public EntityFactory(DataRepository dataRepository) {
        super(dataRepository);
        configFactory();
    }

    private void configFactory() {
        register(Beans.BeanFactory.class);
        register(BeanForArrays.BeanForArrayFactory.class);
        register(BeansWithChild.BeanWithChildFactory.class);
        register(BeansWithPathVariable.BeanFactory.class);
        register(BeansWithTwoPathVariables.BeanFactory.class);
    }
}
