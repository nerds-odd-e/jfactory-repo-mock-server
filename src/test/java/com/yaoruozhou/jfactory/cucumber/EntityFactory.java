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
        register(BeanForXmls.BeanSpec.class);
        register(BeansWithChildForXml.BeanFactory.class);
        register(GzipBeans.BeanSpec.class);
        register(GzipBeanForArrays.BeanSpec.class);
        register(GzipBeanForXmls.BeanSpec.class);

        register(PostBeans.BeanFactory.class);
        register(PostBeanForArrays.BeanFactory.class);
        register(PostBeansWithChild.BeanFactory.class);
        register(PostBeansWithPathVariable.BeanFactory.class);
        register(PostBeansWithTwoPathVariables.BeanFactory.class);
        register(PostBeanForXmls.BeanFactory.class);
        register(PostBeansWithChildForXml.BeanFactory.class);
        register(PostGzipBeans.BeanSpec.class);
        register(PostGzipBeanForArrays.BeanSpec.class);
        register(PostGzipBeanForXmls.BeanSpec.class);

        register(PutBeans.BeanFactory.class);
        register(PutBeanForArrays.BeanFactory.class);
        register(PutBeansWithChild.BeanFactory.class);
        register(PutBeansWithPathVariable.BeanFactory.class);
        register(PutBeansWithTwoPathVariables.BeanFactory.class);
        register(PutBeanForXmls.BeanFactory.class);
        register(PutBeansWithChildForXml.BeanFactory.class);
        register(PutGzipBeans.BeanSpec.class);
        register(PutGzipBeanForArrays.BeanSpec.class);
        register(PutGzipBeanForXmls.BeanSpec.class);

        register(GetRequests.GetRequest.class);
        register(PostRequests.PostRequest.class);
        register(PutRequests.PutRequest.class);
        register(GetRequestsWithPV.GetRequestWithPV.class);
        register(PostRequestsWithPV.PostRequestWithPV.class);
        register(PutRequestsWithPV.PutRequestWithPV.class);
    }
}
