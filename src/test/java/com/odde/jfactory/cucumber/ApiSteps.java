package com.odde.jfactory.cucumber;

import io.cucumber.java.en.Then;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = {CucumberConfig.class}, loader = SpringBootContextLoader.class)
@CucumberContextConfiguration
public class ApiSteps {

    private final OkHttpClient okHttpClient = new OkHttpClient();

    @SneakyThrows
    @Then("Get {string} response code is {int} and body as below")
    public void getResponseCodeIsAndBodyAsBelow(String url, int code, String body) {
        Response response = getExecute(url);
        assertThat(response.code()).isEqualTo(code);
        JSONAssert.assertEquals(body, response.body().string(), JSONCompareMode.NON_EXTENSIBLE);
    }

    @Then("Get {string} response code is {int}")
    public void getResponseCodeIs(String url, int code) {
        Response response = getExecute(url);
        assertThat(response.code()).isEqualTo(code);
    }

    @SneakyThrows
    private Response getExecute(String url) {
        Request request = new Request.Builder()
                .url(String.format("http://localhost:9081%s", url))
                .get().build();
        return okHttpClient.newCall(request).execute();
    }

}
