package com.yaoruozhou.jfactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.common.base.Joiner;
import lombok.Setter;
import lombok.SneakyThrows;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.HttpRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.yaoruozhou.jfactory.Response.Type.JsonArray;
import static com.yaoruozhou.jfactory.Response.Type.Xml;
import static io.netty.handler.codec.http.HttpHeaderValues.APPLICATION_JSON;
import static io.netty.handler.codec.http.HttpHeaderValues.APPLICATION_XML;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.mockserver.matchers.Times.unlimited;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class MockServerDataRepositoryImpl implements MockServerDataRepository {
    private final MockServerClient mockServerClient;
    private String urlParams;
    private Class<?> rootClazz;
    private String pathVariables;
    private final XmlMapper xmlMapper = new XmlMapper();

    @Setter
    private Function<Object, String> serializer = this::toJson;

    public MockServerDataRepositoryImpl(MockServerClient mockServerClient) {
        this.mockServerClient = mockServerClient;
    }

    @Override
    public <T> Collection<T> queryAll(Class<T> type) {
        if (!type.equals(rootClazz)) {
            return Collections.emptyList();
        }
        return (Collection<T>) Arrays.asList(retrieveRecordedRequests(type)).stream()
                .map(rd -> new RequestVerification(rd, type.getAnnotation(Request.class))).collect(toList());
    }

    @Override
    public void clear() {

    }

    @Override
    public void save(Object object) {
        if (!object.getClass().equals(rootClazz)) {
            return;
        }

        validate(object);

        Request requestAnnotation = object.getClass().getAnnotation(Request.class);
        String path = requestAnnotation.path();
        String method = requestAnnotation.method();
        if (isResponseArray(object)) {
            getJson(method, path, new Object[]{object});
        } else if (isResponseXml(object)) {
            getXml(object, path, method);
        } else {
            getJson(method, path, object);
        }
    }

    @SneakyThrows
    private void getXml(Object object, String path, String method) {
        String pathWithVariable = populatePathVariables(path);
        validatePath(pathWithVariable);
        HttpRequest request = request().withMethod(method.toUpperCase()).withPath(pathWithVariable);
        setParamsForCurrentRequest(request);
        mockServerClient.when(request, unlimited())
                .respond(response().withStatusCode(200)
                        .withHeader(CONTENT_TYPE, APPLICATION_XML.toString())
                        .withBody(xmlMapper.writeValueAsString(object)));
    }

    private boolean isResponseXml(Object object) {
        Response response = object.getClass().getAnnotation(Response.class);
        return response != null && response.type().equals(Xml);
    }

    @Override
    public void setUrlParams(String urlParams) {
        this.urlParams = urlParams;
    }

    @Override
    public void setRootClass(Class<?> clazz) {
        rootClazz = clazz;
    }

    @Override
    public void setPathVariables(String pathVariables) {
        this.pathVariables = pathVariables;
    }

    private void getJson(String method, String path, Object response) {
        String pathWithVariable = populatePathVariables(path);
        validatePath(pathWithVariable);
        HttpRequest request = request().withMethod(method.toUpperCase()).withPath(pathWithVariable);
        setParamsForCurrentRequest(request);
        mockServerClient.when(request, unlimited())
                .respond(response().withStatusCode(200)
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON.toString())
                        .withBody(serializer.apply(response)));
    }

    private boolean isResponseArray(Object object) {
        Response response = object.getClass().getAnnotation(Response.class);
        return response != null && response.type().equals(JsonArray);
    }

    private String populatePathVariables(String path) {
        if (pathVariables == null) {
            return path;
        }
        try {
            return Arrays.stream(pathVariables.split("&")).reduce(path, this::updatePathWithVariableValue);
        } finally {
            pathVariables = null;
        }
    }

    private <T> HttpRequest[] retrieveRecordedRequests(Class<T> type) {
        Request request = type.getAnnotation(Request.class);
        String path = request.path();
        HttpRequest requestDefinition = request()
                .withPath(path).withMethod(request.method());
        Matcher matcher = Pattern.compile("\\{.+}").matcher(path);
        while (matcher.find()) {
            String matched = matcher.group();
            requestDefinition.withPathParameter(matched.substring(1, matched.length() - 1), ".+");
        }
        return mockServerClient.retrieveRecordedRequests(requestDefinition);
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

    @SneakyThrows
    private String toJson(Object o) {
        return new ObjectMapper().writeValueAsString(o);
    }

    private String updatePathWithVariableValue(String currentPath, String pair) {
        String[] nameAndValue = pair.split("=");
        if (nameAndValue.length == 1) {
            throw new IllegalArgumentException("Request path variable failed to parse");
        }
        String name = nameAndValue[0];
        if (currentPath.contains(format("{%s}", name))) {
            return currentPath.replace("{" + name + "}", nameAndValue[1]);
        }
        throw new IllegalArgumentException("Request path variable \"" + name + "\" failed to match");
    }

    private void validate(Object object) {
        if (!object.getClass().isAnnotationPresent(Request.class)) {
            throw new IllegalArgumentException("Request annotation must be used");
        }
    }

    private void validatePath(String path) {
        Matcher matcher = Pattern.compile("\\{([^}]+)}").matcher(path);
        ArrayList<String> pathVariableNamesNotSet = new ArrayList<>();
        while (matcher.find()) {
            pathVariableNamesNotSet.add(matcher.group(1));
        }
        if (!pathVariableNamesNotSet.isEmpty()) {
            throw new IllegalArgumentException("Request path variable \"" + Joiner.on(",").join(pathVariableNamesNotSet) + "\" not set");
        }
    }

}
