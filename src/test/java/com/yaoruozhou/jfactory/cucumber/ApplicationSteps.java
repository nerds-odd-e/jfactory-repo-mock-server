package com.yaoruozhou.jfactory.cucumber;

import com.github.leeonky.cucumber.restful.RestfulStep;
import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {CucumberConfig.class}, loader = SpringBootContextLoader.class)
@CucumberContextConfiguration
public class ApplicationSteps {

    @Autowired
    RestfulStep restfulStep;

    @Before
    public void setBaseUrl() {
        restfulStep.setBaseUrl("http://127.0.0.1:9081");
    }

}
