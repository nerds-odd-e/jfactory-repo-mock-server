package com.yaoruozhou.jfactory.cucumber;

import com.github.leeonky.jfactory.JFactory;
import com.yaoruozhou.jfactory.MockServerDataRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class FactoryConfig {

    @Bean
    @Scope("prototype")
    public JFactory factorySet() {
        return new EntityFactory(new MockServerDataRepositoryImpl(MockServer.getClientAndServer()));
    }

}
