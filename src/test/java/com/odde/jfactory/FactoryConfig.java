package com.odde.jfactory;

import com.github.leeonky.jfactory.JFactory;
import org.mockserver.client.MockServerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryConfig {

    @Bean
    public MockServerClient createMockServerClient() {
        return MockServer.getClientAndServer();
    }

    @Bean
    public JFactory factorySet(@Autowired MockServerClient mockServerClient) {
        return new EntityFactory(new MockServerDataRepository(mockServerClient));
    }

}
