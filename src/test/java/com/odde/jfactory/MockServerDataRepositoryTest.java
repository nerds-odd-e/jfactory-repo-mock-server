package com.odde.jfactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.client.ForwardChainExpectation;
import org.mockserver.client.MockServerClient;
import org.mockserver.matchers.Times;
import org.mockserver.model.RequestDefinition;

import static com.odde.jfactory.Response.Type.JsonArray;
import static io.netty.handler.codec.http.HttpHeaderValues.APPLICATION_JSON;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class MockServerDataRepositoryTest {

    MockServerClient mockMockServerClient = mock(MockServerClient.class);
    MockServerDataRepository dataRepository = new MockServerDataRepository(mockMockServerClient);
    ForwardChainExpectation mockResponse = mock(ForwardChainExpectation.class);
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void givenMockResponse() {
        when(mockMockServerClient.when(any(RequestDefinition.class), any(Times.class))).thenReturn(mockResponse);
    }

    @SneakyThrows
    @Test
    public void mock_get_request_for_response_object() {
        dataRepository.save(new ObjectWithRequestAndResponseObject().setField("value"));

        verify(mockMockServerClient).when(eq(request().withMethod("GET").withPath("path")), eq(Times.unlimited()));
        verify(mockResponse).respond(eq(response().withStatusCode(200)
                .withHeader(CONTENT_TYPE, APPLICATION_JSON.toString())
                .withBody(objectMapper.writeValueAsString(new ObjectWithRequestAndResponseObject().setField("value")))));
    }

    @SneakyThrows
    @Test
    public void mock_get_request_for_response_array() {
        dataRepository.save(new ObjectWithRequestAndResponseArray().setField("value"));

        verify(mockMockServerClient).when(eq(request().withMethod("GET").withPath("path")), eq(Times.unlimited()));
        verify(mockResponse).respond(eq(response().withStatusCode(200)
                .withHeader(CONTENT_TYPE, APPLICATION_JSON.toString())
                .withBody(objectMapper.writeValueAsString(new ObjectWithRequestAndResponseArray[]{
                        new ObjectWithRequestAndResponseArray().setField("value")}))));
    }

    @Test
    public void throw_exception_when_save_with_no_request_annotation() {
        assertThatThrownBy(() -> dataRepository.save(new Object()))
                .isExactlyInstanceOf(IllegalArgumentException.class).hasMessage("Request annotation must be used");
    }

    @Request(path = "path")
    @Getter
    @Setter
    @Accessors(chain = true)
    private static class ObjectWithRequestAndResponseObject {
        private String field;
    }

    @Request(path = "path")
    @Response(type = JsonArray)
    @Getter
    @Setter
    @Accessors(chain = true)
    private static class ObjectWithRequestAndResponseArray {
        private String field;
    }

}