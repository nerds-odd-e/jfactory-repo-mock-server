package com.odde.jfactory.cucumber;

import com.github.leeonky.jfactory.JFactory;
import com.github.leeonky.jfactory.cucumber.JData;
import com.github.leeonky.jfactory.cucumber.Table;
import io.cucumber.java.en.Given;

public class MockServerData {

    private final JData jData;
    private final JFactory jFactory;

    public MockServerData(JFactory jFactory) {
        jData = new JData(jFactory);
        this.jFactory = jFactory;
    }

    @Given("Exists data {string} with params {string}")
    public void existsDataWithParams(String factory, String params, Table table) {
    }

    @Given("Exists api data {string}:")
    public void existsApiData(String factory, Table table) {
        jData.prepare(factory, table);
    }
}
