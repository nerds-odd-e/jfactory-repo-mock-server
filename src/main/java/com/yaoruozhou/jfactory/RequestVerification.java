package com.yaoruozhou.jfactory;

import org.mockserver.model.HttpRequest;
import org.mockserver.model.KeyToMultiValue;
import org.mockserver.model.NottableString;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class RequestVerification {
    public final Map<String, List<String>> queryParams;
    public final Map<String, String> pathVariables = new HashMap<>();
    public final byte[] body;
    public final Map<String, List<String>> headers;

    public RequestVerification(HttpRequest rd, Request request) {
        queryParams = parseKeyToMultiValue(rd.getQueryStringParameterList());
        parsePathVariables(rd, request);
        body = rd.getBodyAsRawBytes();
        headers = parseKeyToMultiValue(rd.getHeaderList());
    }

    private boolean isMatched(String[] allActualSubPath, String definedSubPath, int index) {
        return definedSubPath.startsWith("{") && definedSubPath.endsWith("}") && index <= allActualSubPath.length - 1;
    }

    private Map<String, List<String>> parseKeyToMultiValue(List<? extends KeyToMultiValue> headerList) {
        return headerList.stream().collect(toMap(h -> h.getName().getValue(), h -> h.getValues().stream().map(NottableString::getValue).collect(toList())));
    }

    private void parsePathVariables(HttpRequest rd, Request request) {
        String[] allDefinedSubPath = request.path().split("/");
        String[] allActualSubPath = rd.getPath().getValue().split("/");
        for (String definedSubPath : allDefinedSubPath) {
            int index = Arrays.asList(allDefinedSubPath).indexOf(definedSubPath);
            if (isMatched(allActualSubPath, definedSubPath, index)) {
                pathVariables.put(definedSubPath.substring(1, definedSubPath.length() - 1), allActualSubPath[index]);
            }
        }
    }

}
