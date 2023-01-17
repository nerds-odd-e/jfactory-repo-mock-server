package com.yaoruozhou.jfactory.cucumber;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.leeonky.cucumber.restful.RestfulStep;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Map;

@ContextConfiguration(classes = {CucumberConfig.class}, loader = SpringBootContextLoader.class)
@CucumberContextConfiguration
public class ApplicationSteps {

    @Autowired
    RestfulStep restfulStep;

    @Before
    public void setBaseUrl() {
        restfulStep.setBaseUrl("http://127.0.0.1:9081");
    }

    @SneakyThrows
    @Given("header by RESTful api:")
    public void header_by_res_tful_api(String headerJson) {
        new ObjectMapper().readValue(headerJson, new TypeReference<Map<String, Object>>() {
        }).forEach((key, value) -> {
            if (value instanceof String)
                restfulStep.header(key, (String) value);
            else
                restfulStep.header(key, (List<String>) value);
        });
    }
}
