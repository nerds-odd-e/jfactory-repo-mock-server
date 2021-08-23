package com.odde.jfactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.leeonky.jfactory.DataRepository;
import lombok.SneakyThrows;
import org.mockserver.client.MockServerClient;

import java.util.Collection;

import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.mockserver.matchers.Times.unlimited;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class MockServerDataRepository implements DataRepository {
    private final MockServerClient mockServerClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public MockServerDataRepository(MockServerClient mockServerClient) {
        this.mockServerClient = mockServerClient;
    }

    @Override
    public <T> Collection<T> queryAll(Class<T> type) {
        return null;
    }

    @Override
    public void clear() {

    }

    @SneakyThrows
    @Override
    public void save(Object object) {
        mockServerClient.when(request().withMethod("GET").withPath("/beans"), unlimited())
                .respond(response().withStatusCode(200)
                        .withHeader(CONTENT_TYPE, "application/json")
                        .withBody(objectMapper.writeValueAsString(object)));
    }
}
