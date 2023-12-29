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
        register(BeanOnlyOneTimes.BeanSpec.class);
        register(GzipBeanOnlyOneTimes.BeanSpec.class);
        register(BeanForArrayOnlyOneTimes.BeanSpec.class);
        register(GzipBeanForArrayOnlyOneTimes.BeanSpec.class);
        register(BeanForXmlOnlyOneTimes.BeanSpec.class);
        register(GzipBeanForXmlOnlyOneTimes.BeanSpec.class);
        register(SubPathBeans.BeanFactory.class);
        register(SubPathGzipBeans.BeanSpec.class);
        register(SamePathBeans.BeanFactory.class);
        register(SubPathBeansWithPathVariable.BeanFactory.class);

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
        register(PostBeanOnlyOneTimes.BeanSpec.class);
        register(PostGzipBeanOnlyOneTimes.BeanSpec.class);
        register(PostBeanForArrayOnlyOneTimes.BeanSpec.class);
        register(PostGzipBeanForArrayOnlyOneTimes.BeanSpec.class);
        register(PostBeanForXmlOnlyOneTimes.BeanSpec.class);
        register(PostGzipBeanForXmlOnlyOneTimes.BeanSpec.class);
        register(SubPathPostBeans.BeanFactory.class);
        register(SubPathPostGzipBeans.BeanSpec.class);
        register(SamePathPostBeans.BeanFactory.class);
        register(SubPathPostBeansWithPathVariable.BeanFactory.class);

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
        register(PutBeanOnlyOneTimes.BeanSpec.class);
        register(PutGzipBeanOnlyOneTimes.BeanSpec.class);
        register(PutBeanForArrayOnlyOneTimes.BeanSpec.class);
        register(PutGzipBeanForArrayOnlyOneTimes.BeanSpec.class);
        register(PutBeanForXmlOnlyOneTimes.BeanSpec.class);
        register(PutGzipBeanForXmlOnlyOneTimes.BeanSpec.class);
        register(SubPathPutBeans.BeanFactory.class);
        register(SubPathPutGzipBeans.BeanSpec.class);
        register(SamePathPutBeans.BeanFactory.class);
        register(SubPathPutBeansWithPathVariable.BeanFactory.class);

        register(GetRequests.GetRequest.class);
        register(PostRequests.PostRequest.class);
        register(PutRequests.PutRequest.class);
        register(GetRequestsWithPV.GetRequestWithPV.class);
        register(PostRequestsWithPV.PostRequestWithPV.class);
        register(PutRequestsWithPV.PutRequestWithPV.class);
    }
}
