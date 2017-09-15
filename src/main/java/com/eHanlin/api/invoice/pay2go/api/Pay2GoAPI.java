package com.eHanlin.api.invoice.pay2go.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Pay2GoAPI<T> {

    private static final String DEFAULT_RESPOND_TYPE = "JSON";

    private static final String DEFAULT_VERSION = "1.1";

    Map<String, String> params;

    Pay2GoAPI() {
        params = new HashMap<>();
        setRespondType(DEFAULT_RESPOND_TYPE);
        setVersion(DEFAULT_VERSION);
    }

    @SuppressWarnings("unchecked")
    public T setRespondType(String respondType) {
        params.put("RespondType", respondType);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setVersion(String version) {
        params.put("Version", version);
        return (T) this;
    }

    /**
     * 取得 API 名稱
     */
    public abstract String name();

    /**
     * 請求參數
     */
    public String param() {
        List<String> args = params.entrySet().stream()
                .filter(entry -> entry.getValue() != null && entry.getValue().length() > 0)
                .map(entry -> String.format("%s=%s", entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return String.join("&", args);
    }

    @Override
    public String toString() {
        return String.format("%s(%s)", name(), param());
    }

}

