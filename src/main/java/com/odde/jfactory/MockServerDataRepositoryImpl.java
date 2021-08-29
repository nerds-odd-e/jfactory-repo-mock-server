package com.odde.jfactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.HttpRequest;

import java.util.Arrays;
import java.util.Collection;

import static com.odde.jfactory.Response.Type.JsonArray;
import static io.netty.handler.codec.http.HttpHeaderValues.APPLICATION_JSON;
import static java.util.Collections.emptyList;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.mockserver.matchers.Times.unlimited;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class MockServerDataRepositoryImpl implements MockServerDataRepository {
    private final MockServerClient mockServerClient;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private String urlParams;
    private Class<?> rootClazz;

    public MockServerDataRepositoryImpl(MockServerClient mockServerClient) {
        this.mockServerClient = mockServerClient;
    }

    @Override
    public <T> Collection<T> queryAll(Class<T> type) {
        return emptyList();
    }

    @Override
    public void clear() {

    }

    @SneakyThrows
    @Override
    public void save(Object object) {
        if (!object.getClass().equals(rootClazz)) {
            return;
        }

        validate(object);

        String path = object.getClass().getAnnotation(Request.class).path();
        if (isResponseArray(object)) {
            getJson(path, new Object[]{object});
        } else {
            getJson(path, object);
        }
    }

    @Override
    public void setUrlParams(String urlParams) {
        this.urlParams = urlParams;
    }

    @Override
    public void setRootClass(Class<?> clazz) {
        rootClazz = clazz;
    }

    private void getJson(String path, Object response) throws JsonProcessingException {
        HttpRequest request = request().withMethod("GET").withPath(path);
        setParamsForCurrentRequest(request);
        mockServerClient.when(request, unlimited())
                .respond(response().withStatusCode(200)
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON.toString())
                        .withBody(objectMapper.writeValueAsString(response)));
    }

    private boolean isResponseArray(Object object) {
        Response response = object.getClass().getAnnotation(Response.class);
        return response != null && response.type().equals(JsonArray);
    }

    private void setParamsForCurrentRequest(HttpRequest request) {
        if (urlParams != null) {
            try {
                Arrays.stream(urlParams.split("&")).forEach(pair -> {
                    String[] nameAndValue = pair.split("=");
                    request.withQueryStringParameter(nameAndValue[0], nameAndValue[1]);
                });
            } catch (Exception e) {
                throw new IllegalArgumentException("Request param failed to parse");
            } finally {
                urlParams = null;
            }
        }
    }

    private void validate(Object object) {
        if (!object.getClass().isAnnotationPresent(Request.class)) {
            throw new IllegalArgumentException("Request annotation must be used");
        }
    }
}
