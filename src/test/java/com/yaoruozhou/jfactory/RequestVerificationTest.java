package com.yaoruozhou.jfactory;

import org.assertj.core.api.Assertions;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockserver.model.HttpRequest;

import java.lang.annotation.Annotation;
import java.util.AbstractMap;
import java.util.HashMap;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class RequestVerificationTest {

    RequestForTest requestAnnotation = new RequestForTest("/path", "GET");
    HttpRequest receivedRequest = new HttpRequest();

    @Nested
    public class QueryParams {

        @Test
        public void one_query_param() {
            receivedRequest.withQueryStringParameter("key", "value");

            Assertions.assertThat(requestVerification().queryParams).containsExactly(new AbstractMap.SimpleEntry<>("key", singletonList("value")));
        }

        @Test
        public void two_query_params() {
            receivedRequest.withQueryStringParameter("key", "value").withQueryStringParameter("key2", "value2");

            Assertions.assertThat(requestVerification().queryParams).containsExactlyInAnyOrderEntriesOf(new HashMap<String, java.util.List<String>>() {{
                put("key", singletonList("value"));
                put("key2", singletonList("value2"));
            }});
        }

        @Test
        public void query_param_with_multiple_values() {
            receivedRequest.withQueryStringParameter("key", "value1", "value2");

            Assertions.assertThat(requestVerification().queryParams).containsExactly(new AbstractMap.SimpleEntry<>("key", asList("value1", "value2")));
        }
    }

    @NotNull
    private RequestVerification requestVerification() {
        return new RequestVerification(receivedRequest, requestAnnotation);
    }

    private static class RequestForTest implements Request {
        private final String path;
        private final String method;

        public RequestForTest(String path, String method) {
            this.path = path;
            this.method = method;
        }

        @Override
        public String path() {
            return path;
        }

        @Override
        public String method() {
            return method;
        }

        @Override
        public Class<? extends Annotation> annotationType() {
            return Request.class;
        }
    }

}