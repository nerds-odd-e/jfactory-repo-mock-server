package com.odde.jfactory;

import com.github.leeonky.jfactory.DataRepository;
import org.mockserver.client.MockServerClient;

import java.util.Collection;

public class MockServerDataRepository implements DataRepository {
    public MockServerDataRepository(MockServerClient mockServerClient) {
    }

    @Override
    public <T> Collection<T> queryAll(Class<T> type) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public void save(Object object) {

    }
}
