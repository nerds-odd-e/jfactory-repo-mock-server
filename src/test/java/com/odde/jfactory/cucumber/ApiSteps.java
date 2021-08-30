package com.odde.jfactory.cucumber;

import io.cucumber.java.en.Then;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.SneakyThrows;
import okhttp3.*;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = {CucumberConfig.class}, loader = SpringBootContextLoader.class)
@CucumberContextConfiguration
public class ApiSteps {

    private final OkHttpClient okHttpClient = new OkHttpClient();

    @SneakyThrows
    @Then("Get {string} response code is {int} and body as below")
    public void getResponseCodeIsAndBodyAsBelow(String url, int code, String body) {
        executeAndAssert("GET", null, url, code, body);
    }

    @Then("Get {string} response code is {int}")
    public void getResponseCodeIs(String url, int code) {
        Response response = execute("GET", null, url);
        assertThat(response.code()).isEqualTo(code);
    }

    @SneakyThrows
    @Then("Post {string} response code is {int} and body as below")
    public void postResponseCodeIsAndBodyAsBelow(String url, int code, String body) {
        executeAndAssert("POST", RequestBody.create("{}", MediaType.parse("application/json")), url, code, body);
    }

    @SneakyThrows
    private Response execute(String method, RequestBody body, String url) {
        Request request = new Request.Builder()
                .url(String.format("http://localhost:9081%s", url))
                .method(method, body).build();
        return okHttpClient.newCall(request).execute();
    }

    private void executeAndAssert(String method, RequestBody requestBody, String url, int code, String body) throws JSONException, IOException {
        Response response = execute(method, requestBody, url);
        assertThat(response.code()).isEqualTo(code);
        JSONAssert.assertEquals(body, response.body().string(), JSONCompareMode.NON_EXTENSIBLE);
    }

}
