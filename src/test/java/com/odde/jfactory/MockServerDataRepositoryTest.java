package com.odde.jfactory;

import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MockServerDataRepositoryTest {

    @Test
    public void throw_exception_when_save_with_no_request_annotation() {
        MockServerDataRepository dataRepository = new MockServerDataRepository(new MockServerClient("anyHost", 12345));

        assertThatThrownBy(() -> dataRepository.save(new Object())).isExactlyInstanceOf(IllegalStateException.class);
    }
}