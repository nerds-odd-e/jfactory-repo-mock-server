package com.yaoruozhou.jfactory.cucumber;

import com.github.leeonky.jfactory.JFactory;
import com.github.leeonky.jfactory.cucumber.JData;
import com.github.leeonky.jfactory.cucumber.Table;
import com.yaoruozhou.jfactory.MockServerDataRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.zh_cn.假如;

public class MockServerData {

    private final JData jData;
    private final JFactory jFactory;
    private final MockServerDataRepository dataRepository;

    public MockServerData(JFactory jFactory) {
        jData = new JData(jFactory);
        this.jFactory = jFactory;
        dataRepository = ((MockServerDataRepository) jFactory.getDataRepository());
    }

    @Given("Exists api data {string} with params {string}:")
    @假如("存在接口数据{string}并匹配查询参数{string}:")
    public void prepareApiDataWithParams(String factory, String params, Table table) {
        dataRepository.setUrlParams(params);
        prepareApiData(factory, table);
    }

    @Given("Exists api data {string}:")
    @假如("存在接口数据{string}:")
    public void prepareApiData(String factory, Table table) {
        dataRepository.setRootClass(jFactory.specFactory(factory).getType().getType());
        jData.prepare(factory, table);
    }

    @Given("Exists api data {string} with path variables {string}:")
    @假如("存在接口数据{string}并匹配路径变量{string}:")
    public void prepareApiDataWithPathVariables(String factory, String pathVariables, Table table) {
        dataRepository.setPathVariables(pathVariables);
        prepareApiData(factory, table);
    }

    @Given("Exists {int} api data {string}")
    @假如("存在{int}个接口数据{string}")
    public void prepareApiDataWithNumber(int number, String factory) {
        dataRepository.setRootClass(jFactory.specFactory(factory).getType().getType());
        jData.prepare(number, factory);
    }
}
