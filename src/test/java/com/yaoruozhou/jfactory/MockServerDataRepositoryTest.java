package com.yaoruozhou.jfactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.yaoruozhou.jfactory.cucumber.get.*;
import com.yaoruozhou.jfactory.cucumber.post.PostBean;
import com.yaoruozhou.jfactory.cucumber.put.PutBean;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockserver.client.ForwardChainExpectation;
import org.mockserver.client.MockServerClient;
import org.mockserver.matchers.Times;
import org.mockserver.mock.Expectation;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.Parameter;
import org.mockserver.model.RequestDefinition;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPOutputStream;

import static io.netty.handler.codec.http.HttpHeaderValues.*;
import static org.apache.http.HttpHeaders.CONTENT_ENCODING;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockserver.model.BinaryBody.binary;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class MockServerDataRepositoryTest {

    MockServerClient mockMockServerClient = mock(MockServerClient.class);
    MockServerDataRepositoryImpl dataRepository = new MockServerDataRepositoryImpl(mockMockServerClient);
    ForwardChainExpectation mockResponse = mock(ForwardChainExpectation.class);
    ObjectMapper objectMapper = new ObjectMapper();
    XmlMapper xmlMapper = new XmlMapper();
    ArgumentCaptor<HttpRequest> requestCaptor = forClass(HttpRequest.class);

    @BeforeEach
    public void givenMockResponse() {
        when(mockMockServerClient.when(any(RequestDefinition.class), any(Times.class))).thenReturn(mockResponse);
        when(mockMockServerClient.retrieveActiveExpectations(any(RequestDefinition.class))).thenReturn(new Expectation[0]);
    }

    @SneakyThrows
    @Test
    public void mock_get_request_for_response_object() {
        save(new Bean().setSomeString("value"));

        verify(mockMockServerClient).when(eq(request().withMethod("GET").withPath("/beans")), eq(Times.unlimited()));
        verify(mockResponse).respond(eq(response().withStatusCode(200)
                .withHeader(CONTENT_TYPE, APPLICATION_JSON.toString())
                .withBody(objectMapper.writeValueAsString(new Bean().setSomeString("value")))));
    }

    @SneakyThrows
    @Test
    public void mock_get_request_for_response_array() {
        save(new BeanForArray().setSomeString("value"));

        verify(mockMockServerClient).when(eq(request().withMethod("GET").withPath("/beans")), eq(Times.unlimited()));
        verify(mockResponse).respond(eq(response().withStatusCode(200)
                .withHeader(CONTENT_TYPE, APPLICATION_JSON.toString())
                .withBody(objectMapper.writeValueAsString(new Bean[]{
                        new BeanForArray().setSomeString("value")}))));
    }

    @SneakyThrows
    @Test
    public void mock_get_request_for_xml_object() {
        save(new BeanForXml().setSomeString("value"));

        verify(mockMockServerClient).when(eq(request().withMethod("GET").withPath("/beans")), eq(Times.unlimited()));
        verify(mockResponse).respond(eq(response().withStatusCode(200)
                .withHeader(CONTENT_TYPE, APPLICATION_XML.toString())
                .withBody(xmlMapper.writeValueAsString(new BeanForXml().setSomeString("value")))));
    }

    @SneakyThrows
    @Test
    public void mock_get_request_for_gzip_json_object() {
        save(new GzipBean().setSomeString("value"));

        verify(mockMockServerClient).when(eq(request().withMethod("GET").withPath("/beans")), eq(Times.unlimited()));
        verify(mockResponse).respond(eq(response().withStatusCode(200)
                .withHeader(CONTENT_TYPE, APPLICATION_JSON.toString())
                .withHeader(CONTENT_ENCODING, GZIP.toString())
                .withBody(binary(toGzipBinary(objectMapper.writeValueAsString(new GzipBean().setSomeString("value")))))));
    }

    @SneakyThrows
    @Test
    public void mock_post_request() {
        save(new PostBean().setSomeString("value"));

        verify(mockMockServerClient).when(eq(request().withMethod("POST").withPath("/beans")), eq(Times.unlimited()));
        verify(mockResponse).respond(eq(response().withStatusCode(200)
                .withHeader(CONTENT_TYPE, APPLICATION_JSON.toString())
                .withBody(objectMapper.writeValueAsString(new Bean().setSomeString("value")))));
    }

    @SneakyThrows
    @Test
    public void mock_put_request() {
        save(new PutBean().setSomeString("value"));

        verify(mockMockServerClient).when(eq(request().withMethod("PUT").withPath("/beans")), eq(Times.unlimited()));
        verify(mockResponse).respond(eq(response().withStatusCode(200)
                .withHeader(CONTENT_TYPE, APPLICATION_JSON.toString())
                .withBody(objectMapper.writeValueAsString(new Bean().setSomeString("value")))));
    }

    @Test
    public void mock_request_with_non_uppercase_method_name() {
        saveAndCaptureRequest(new BeanWithNonUpperCaseMethod());

        Assertions.assertThat(requestCaptor.getValue().getMethod().getValue()).isEqualTo("POST");
    }

    @Test
    public void throw_exception_when_save_with_no_request_annotation() {
        assertThatThrownBy(() -> save(new Object()))
                .isExactlyInstanceOf(IllegalArgumentException.class).hasMessage("Request annotation must be used");
    }

    @Nested
    public class Params {

        @Test
        public void mock_get_request_with_params() {
            dataRepository.setUrlParams("name=value");

            saveObjectAndCaptureRequest();

            Assertions.assertThat(requestCaptor.getValue().getQueryStringParameterList()).containsExactly(new Parameter("name", "value"));
        }

        @Test
        public void mock_get_request_without_params() {
            saveObjectAndCaptureRequest();

            Assertions.assertThat(requestCaptor.getValue().getQueryStringParameterList()).isEmpty();
        }

        @Test
        public void mock_get_request_with_more_than_one_params() {
            dataRepository.setUrlParams("name=value&yaName=yaValue");

            saveObjectAndCaptureRequest();

            Assertions.assertThat(requestCaptor.getValue().getQueryStringParameterList()).containsExactlyInAnyOrder(new Parameter("name", "value"), new Parameter("yaName", "yaValue"));
        }

        @Test
        public void mock_get_request_with_multiple_value_param() {
            dataRepository.setUrlParams("name=value1&name=value2");

            saveObjectAndCaptureRequest();

            Assertions.assertThat(requestCaptor.getValue().getQueryStringParameterList()).containsExactlyInAnyOrder(new Parameter("name", "value1", "value2"));
        }

        @Test
        public void mock_get_request_with_params_should_only_available_to_next_save() {
            dataRepository.setUrlParams("name=value");
            saveObjectAndCaptureRequest();
            resetMocks();

            saveObjectAndCaptureRequest();

            Assertions.assertThat(requestCaptor.getValue().getQueryStringParameterList()).isEmpty();
        }

        @Test
        public void throw_exception_when_param_is_invalid() {
            dataRepository.setUrlParams("namevalue");

            assertThatThrownBy(() -> save(new BeanForArray())).isInstanceOf(IllegalArgumentException.class).hasMessage("Request param failed to parse");
        }

        @Test
        public void param_error_should_only_impact_to_next_save() {
            dataRepository.setUrlParams("namevalue");
            saveAndIgnoreException(new BeanForArray());
            resetMocks();

            saveObjectAndCaptureRequest();

            Assertions.assertThat(requestCaptor.getValue().getQueryStringParameterList()).isEmpty();
        }

    }

    @Nested
    public class ChildObject {

        @Test
        public void mock_get_request_with_child_object() {
            dataRepository.setRootClass(BeanWithChild.class);

            dataRepository.save(new BeanWithChild.ChildBean());

            verifyNoInteractions(mockMockServerClient);
        }

    }

    @Nested
    public class PathVariables {

        @Test
        public void mock_get_request_with_path_variable() {
            dataRepository.setPathVariables("foo=bar");

            saveAndCaptureRequest(new BeanWithPathVariable());

            Assertions.assertThat(requestCaptor.getValue().getPath().getValue()).isEqualTo("/beans/bar");
        }

        @Test
        public void mock_get_request_with_multiple_path_variables() {
            dataRepository.setPathVariables("foo=bar&name=value");

            saveAndCaptureRequest(new BeanWithTwoPathVariables());

            Assertions.assertThat(requestCaptor.getValue().getPath().getValue()).isEqualTo("/beans/bar/another/value");
        }

        @Test
        public void mock_get_request_with_path_variable_should_only_available_to_next_save() {
            dataRepository.setPathVariables("foo=bar");
            saveAndCaptureRequest(new BeanWithPathVariable());
            resetMocks();

            assertThatThrownBy(() -> save(new BeanWithPathVariable())).isInstanceOf(IllegalArgumentException.class).hasMessage("Request path variable \"foo\" not set");
        }

        @Test
        public void throw_exception_when_path_variable_is_invalid() {
            dataRepository.setPathVariables("foobar");

            assertThatThrownBy(() -> save(new BeanWithPathVariable())).isInstanceOf(IllegalArgumentException.class).hasMessage("Request path variable failed to parse");
        }

        @Test
        public void throw_exception_when_path_variable_not_filled() {
            dataRepository.setPathVariables("foo=bar");

            assertThatThrownBy(() -> save(new Bean())).isInstanceOf(IllegalArgumentException.class).hasMessage("Request path variable \"foo\" failed to match");
        }

        @Test
        public void throw_exception_when_path_variable_not_set() {
            assertThatThrownBy(() -> save(new BeanWithPathVariable())).isInstanceOf(IllegalArgumentException.class).hasMessage("Request path variable \"foo\" not set");
        }

        @Test
        public void throw_exception_when_two_path_variables_not_set() {
            assertThatThrownBy(() -> save(new BeanWithTwoPathVariables())).isInstanceOf(IllegalArgumentException.class).hasMessage("Request path variable \"foo,name\" not set");
        }

        @Test
        public void path_variable_error_should_only_impact_to_next_save() {
            dataRepository.setPathVariables("foobar");
            saveAndIgnoreException(new BeanWithPathVariable());
            resetMocks();

            Assertions.assertThatCode(MockServerDataRepositoryTest.this::saveObjectAndCaptureRequest).doesNotThrowAnyException();
        }

    }

    @Getter
    @Setter
    @Request(path = "/beans", method = "Post")
    private class BeanWithNonUpperCaseMethod {
    }

    private void resetMocks() {
        requestCaptor = ArgumentCaptor.forClass(HttpRequest.class);
        reset(mockMockServerClient);
        givenMockResponse();
    }

    private void save(Object object) {
        dataRepository.setRootClass(object.getClass());
        dataRepository.save(object);
    }

    private void saveAndCaptureRequest(Object object) {
        save(object);
        verify(mockMockServerClient).when(requestCaptor.capture(), any(Times.class));
    }

    private void saveAndIgnoreException(Object object) {
        try {
            save(object);
        } catch (Exception ignored) {
        }
    }

    private void saveObjectAndCaptureRequest() {
        saveAndCaptureRequest(new BeanForArray());
    }

    @SneakyThrows
    private byte[] toGzipBinary(String bodyStr) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(bodyStr.length());
        try (GZIPOutputStream gzipOut = new GZIPOutputStream(baos)) {
            gzipOut.write(bodyStr.getBytes(StandardCharsets.UTF_8));
        }
        return baos.toByteArray();
    }

}