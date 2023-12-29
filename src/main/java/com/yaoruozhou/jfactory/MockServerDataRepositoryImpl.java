package com.yaoruozhou.jfactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.common.base.Joiner;
import lombok.Setter;
import lombok.SneakyThrows;
import org.mockserver.client.MockServerClient;
import org.mockserver.matchers.Times;
import org.mockserver.model.HttpRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;

import static com.yaoruozhou.jfactory.Response.Type.JsonArray;
import static com.yaoruozhou.jfactory.Response.Type.Xml;
import static io.netty.handler.codec.http.HttpHeaderValues.*;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static org.apache.http.HttpHeaders.CONTENT_ENCODING;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.mockserver.matchers.Times.unlimited;
import static org.mockserver.model.BinaryBody.binary;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class MockServerDataRepositoryImpl implements MockServerDataRepository {
    private final MockServerClient mockServerClient;
    private final XmlMapper xmlMapper = new XmlMapper();
    private String urlParams;
    private Class<?> rootClazz;
    private String pathVariables;
    @Setter
    private Function<Object, String> serializer = this::toJson;

    @Setter
    private Function<Object, String> xmlSerializer = this::toXml;

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
            responseJson(new Object[]{object}, method, path, isGzip(object), times(object));
        } else if (isResponseXml(object)) {
            responseXml(object, path, method, isGzip(object), times(object));
        } else {
            responseJson(object, method, path, isGzip(object), times(object));
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

    @Override
    public void setPathVariables(String pathVariables) {
        this.pathVariables = pathVariables;
    }

    private Times getTimes(int times) {
        return times > 0 ? Times.exactly(times) : unlimited();
    }

    private boolean isGzip(Object object) {
        Response response = object.getClass().getAnnotation(Response.class);
        return response != null && response.gzip();
    }

    private boolean isResponseArray(Object object) {
        Response response = object.getClass().getAnnotation(Response.class);
        return response != null && response.type().equals(JsonArray);
    }

    private boolean isResponseXml(Object object) {
        Response response = object.getClass().getAnnotation(Response.class);
        return response != null && response.type().equals(Xml);
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

    @SneakyThrows
    private void responseData(Object object, String path, String method, String contentType, Function<Object, String> serializer, boolean gzip, int times) {
        String pathWithVariable = populatePathVariables(path);
        validatePath(pathWithVariable);
        HttpRequest request = request().withMethod(method.toUpperCase()).withPath(pathWithVariable);
        setParamsForCurrentRequest(request);
        mockServerClient.clear(request);
        if (gzip) {
            mockServerClient.when(request, getTimes(times))
                    .respond(response().withStatusCode(200)
                            .withHeader(CONTENT_TYPE, contentType)
                            .withHeader(CONTENT_ENCODING, GZIP.toString())
                            .withBody(binary(toGzipBinary(serializer.apply(object)))));
        } else {
            mockServerClient.when(request, getTimes(times))
                    .respond(response().withStatusCode(200)
                            .withHeader(CONTENT_TYPE, contentType)
                            .withBody(serializer.apply(object)));
        }
    }

    private void responseJson(Object object, String method, String path, boolean gzip, int times) {
        responseData(object, path, method, APPLICATION_JSON.toString(), serializer, gzip, times);
    }

    @SneakyThrows
    private void responseXml(Object object, String path, String method, boolean gzip, int times) {
        responseData(object, path, method, APPLICATION_XML.toString(), xmlSerializer, gzip, times);
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

    private int times(Object object) {
        Response response = object.getClass().getAnnotation(Response.class);
        return response == null ? 0 : response.times();
    }

    private byte[] toGzipBinary(String bodyStr) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(bodyStr.length());
        try (GZIPOutputStream gzipOut = new GZIPOutputStream(baos)) {
            gzipOut.write(bodyStr.getBytes(StandardCharsets.UTF_8));
        }
        return baos.toByteArray();
    }

    @SneakyThrows
    private String toJson(Object o) {
        return new ObjectMapper().writeValueAsString(o);
    }

    @SneakyThrows
    private String toXml(Object object) {
        return xmlMapper.writeValueAsString(object);
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
