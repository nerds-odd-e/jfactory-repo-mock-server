package com.odde.jfactory;

import com.github.leeonky.jfactory.DataRepository;

public interface MockServerDataRepository extends DataRepository {
    void setUrlParams(String urlParams);

    void setRootClass(Class<?> clazz);
}
