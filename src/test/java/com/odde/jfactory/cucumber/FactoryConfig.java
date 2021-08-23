package com.odde.jfactory.cucumber;

import com.github.leeonky.jfactory.JFactory;
import com.odde.jfactory.MockServerDataRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryConfig {

    @Bean
    public JFactory factorySet() {
        return new EntityFactory(new MockServerDataRepository(MockServer.getClientAndServer()));
    }

}
