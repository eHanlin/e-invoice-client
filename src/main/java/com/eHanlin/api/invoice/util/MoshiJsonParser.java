package com.eHanlin.api.invoice.util;

import com.squareup.moshi.Moshi;

import java.util.Map;

public class MoshiJsonParser {

    private final Moshi moshi;

    public MoshiJsonParser() {
        moshi = new Moshi.Builder().build();
    }

    public Map asMap(String json) {
        return stringTo(Map.class, json);
    }

    public <T> T stringTo(Class<T> t, String json) {
        try {
            return moshi.adapter(t).fromJson(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
