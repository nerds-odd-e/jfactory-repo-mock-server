package com.yaoruozhou.jfactory.cucumber;

import com.github.leeonky.jfactory.JFactory;
import com.github.leeonky.jfactory.cucumber.JData;
import com.github.leeonky.jfactory.cucumber.Table;
import com.yaoruozhou.jfactory.MockServerDataRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.zh_cn.假如;
import io.cucumber.java.zh_cn.那么;

import java.util.function.Function;

public class MockServerData {

    private final JData jData;
    private final JFactory jFactory;
    private final MockServerDataRepository dataRepository;

    public MockServerData(JFactory jFactory) {
        jData = new JData(jFactory);
        this.jFactory = jFactory;
        dataRepository = ((MockServerDataRepository) jFactory.getDataRepository());
    }

    public void setSerializer(Function<Object, String> serializer) {
        dataRepository.setSerializer(serializer);
    }

    @Given("Exists api data {string} with params {string}:")
    @假如("存在接口数据{string}并匹配查询参数{string}:")
    public void prepareApiDataWithParams(String spec, String params, Table table) {
        dataRepository.setUrlParams(params);
        prepareApiData(spec, table);
    }

    @Given("Exists api data {string}:")
    @假如("存在接口数据{string}:")
    public void prepareApiData(String spec, Table table) {
        setRootClass(spec);
        jData.prepare(spec, table);
    }

    @Given("Exists api data {string} with path variables {string}:")
    @假如("存在接口数据{string}并匹配路径变量{string}:")
    public void prepareApiDataWithPathVariables(String spec, String pathVariables, Table table) {
        dataRepository.setPathVariables(pathVariables);
        prepareApiData(spec, table);
    }

    @Given("Exists {int} api data {string}")
    @假如("存在{int}个接口数据{string}")
    public void prepareApiDataWithNumber(int number, String spec) {
        setRootClass(spec);
        jData.prepare(number, spec);
    }

    @Then("all api data {string} should be:")
    @那么("所有接口数据{string}应为:")
    public void allApiDataShouldBe(String spec, String dalExpression) {
        setRootClass(spec);
        jData.allShould(spec, dalExpression);
    }

    @Given("Exists {int} api data {string} with params {string}")
    @假如("存在{int}个接口数据{string}并匹配查询参数{string}")
    public void prepareApiDataWithNumberAndParams(int number, String spec, String params) {
        dataRepository.setUrlParams(params);
        prepareApiDataWithNumber(number, spec);
    }

    @Given("Exists {int} api data {string} with path variables {string}")
    @假如("存在{int}个接口数据{string}并匹配路径变量{string}")
    public void existsApiDataWithPathVariables(int number, String spec, String pathVariables) {
        dataRepository.setPathVariables(pathVariables);
        prepareApiDataWithNumber(number, spec);
    }

    private void setRootClass(String spec) {
        dataRepository.setRootClass(jFactory.spec(spec.split("[ ,]")).getType().getType());
    }
}
