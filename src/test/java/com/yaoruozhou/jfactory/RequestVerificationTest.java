package com.yaoruozhou.jfactory;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockserver.model.HttpRequest;

import java.lang.annotation.Annotation;
import java.util.AbstractMap;
import java.util.HashMap;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class RequestVerificationTest {

    RequestForTest requestAnnotation = new RequestForTest("/path", "GET");
    HttpRequest receivedRequest = new HttpRequest();

    @Nested
    public class QueryParams {

        @Test
        public void one_query_param() {
            receivedRequest.withQueryStringParameter("key", "value");

            assertThat(requestVerification().queryParams).containsExactly(new AbstractMap.SimpleEntry<>("key", singletonList("value")));
        }

        @Test
        public void two_query_params() {
            receivedRequest.withQueryStringParameter("key", "value").withQueryStringParameter("key2", "value2");

            assertThat(requestVerification().queryParams).containsExactlyInAnyOrderEntriesOf(new HashMap<String, java.util.List<String>>() {{
                put("key", singletonList("value"));
                put("key2", singletonList("value2"));
            }});
        }

        @Test
        public void query_param_with_multiple_values() {
            receivedRequest.withQueryStringParameter("key", "value1", "value2");

            assertThat(requestVerification().queryParams).containsExactly(new AbstractMap.SimpleEntry<>("key", asList("value1", "value2")));
        }
    }

    @Nested
    public class PathVariables {

        @Test
        public void one_path_variable() {
            requestAnnotation = new RequestForTest("/path/{key}", "GET");
            receivedRequest.withPath("/path/value");

            assertThat(requestVerification().pathVariables).containsExactly(new AbstractMap.SimpleEntry<>("key", "value"));
        }

        @Test
        public void two_path_variables() {
            requestAnnotation = new RequestForTest("/path/{key}/{key2}", "GET");
            receivedRequest.withPath("/path/value/value2");

            assertThat(requestVerification().pathVariables).containsExactlyInAnyOrderEntriesOf(new HashMap<String, String>() {{
                put("key", "value");
                put("key2", "value2");
            }});
        }

        @Test
        public void two_path_variables_with_other_sub_path_in_middle() {
            requestAnnotation = new RequestForTest("/path/{key}/other/{key2}", "GET");
            receivedRequest.withPath("/path/value/other/value2");

            assertThat(requestVerification().pathVariables).containsExactlyInAnyOrderEntriesOf(new HashMap<String, String>() {{
                put("key", "value");
                put("key2", "value2");
            }});
        }
    }

    @Nested
    public class QueryParamsAndPathVariables {

        @Test
        public void one_query_param_and_one_path_variable() {
            requestAnnotation = new RequestForTest("/path/{key}", "GET");
            receivedRequest.withPath("/path/value").withQueryStringParameter("key2", "value2");

            assertThat(requestVerification().pathVariables).containsExactly(new AbstractMap.SimpleEntry<>("key", "value"));
            assertThat(requestVerification().queryParams).containsExactly(new AbstractMap.SimpleEntry<>("key2", singletonList("value2")));
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