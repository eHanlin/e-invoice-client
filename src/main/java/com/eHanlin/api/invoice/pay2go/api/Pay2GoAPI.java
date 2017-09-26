package com.eHanlin.api.invoice.pay2go.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Pay2GoAPI<T> {

    private static final String DEFAULT_RESPOND_TYPE = "JSON";

    private String name;

    Map<String, Object> params;

    Pay2GoAPI(String name, String version) {
        this.name = name;
        params = new HashMap<>();
        setVersion(version);
        setRespondType(DEFAULT_RESPOND_TYPE);
        setTimeStamp(new Date());
    }

    @SuppressWarnings("unchecked")
    public T setRespondType(String respondType) {
        return setParam("RespondType", respondType);
    }

    @SuppressWarnings("unchecked")
    public T setVersion(String version) {
        return setParam("Version", version);
    }

    /**
     * 商店自訂編號
     * @param merchantOrderNo
     */
    @SuppressWarnings("unchecked")
    public T setMerchantOrderNo(String merchantOrderNo) {
        return setParam("MerchantOrderNo", merchantOrderNo);
    }

    @SuppressWarnings("unchecked")
    public T setTimeStamp(String timeStamp) {
        return setParam("TimeStamp", timeStamp);
    }

    public T setTimeStamp(Date timeStamp) {
        return setTimeStamp((timeStamp.getTime() / 1000) + "");
    }

    /**
     * 取得 API 名稱
     */
    public String name() {
        return name;
    }

    public T setParam(String name, Object value) {
        if (value == null) {
            params.remove(name);
        } else {
            params.put(name, value);
        }

        return (T) this;
    }

    /**
     * 請求參數
     */
    public String param() {
        List<String> args = params.entrySet().stream()
                .filter(entry -> entry.getValue() != null)
                .map(entry -> String.format("%s=%s", entry.getKey(), entry.getValue().toString()))
                .collect(Collectors.toList());

        return String.join("&", args);
    }

    @Override
    public String toString() {
        return String.format("%s(%s)", name(), param());
    }

}

