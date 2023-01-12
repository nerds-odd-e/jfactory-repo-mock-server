package com.yaoruozhou.jfactory;

import org.mockserver.model.HttpRequest;
import org.mockserver.model.NottableString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class RequestVerification {
    public final Map<String, List<String>> queryParams;
    public final Map<String, String> pathVariables = new HashMap<>();

    public RequestVerification(HttpRequest rd, Request request) {
        queryParams = rd.getQueryStringParameterList().stream().collect(toMap(p -> p.getName().getValue(), p -> p.getValues().stream().map(NottableString::getValue).collect(toList())));
        String pvDefinedPath = request.path();
        int startPos = pvDefinedPath.indexOf("{");
        String path = rd.getPath().getValue();
        if (startPos > 0) {
            path = path.substring(startPos);
            int endPos = path.indexOf("/");
            String pvValue = path.substring(0, endPos > 0 ? endPos : path.length());
            path = path.substring(endPos + 1);
            pvDefinedPath = pvDefinedPath.substring(startPos + 1);
            String pvKey = pvDefinedPath.substring(0, pvDefinedPath.indexOf("}"));
            pvDefinedPath = pvDefinedPath.substring(pvDefinedPath.indexOf("}") + 1);
            pathVariables.put(pvKey, pvValue);
        }
        startPos = pvDefinedPath.indexOf("{");
        if (startPos > 0) {
            int endPos = path.indexOf("/");
            String pvValue = path.substring(0, endPos > 0 ? endPos : path.length());
            path = path.substring(endPos + 1);
            pvDefinedPath = pvDefinedPath.substring(startPos + 1);
            String pvKey = pvDefinedPath.substring(0, pvDefinedPath.indexOf("}"));
            pvDefinedPath = pvDefinedPath.substring(pvDefinedPath.indexOf("}") + 1);
            pathVariables.put(pvKey, pvValue);
        }
    }
}
