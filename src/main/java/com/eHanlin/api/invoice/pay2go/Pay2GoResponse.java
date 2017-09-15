package com.eHanlin.api.invoice.pay2go;

import com.eHanlin.api.invoice.util.MoshiJsonParser;

import java.util.Map;

public class Pay2GoResponse {

    private String reponseBody;

    private MoshiJsonParser jsonParser;

    public Pay2GoResponse(String reponseBody, MoshiJsonParser jsonParser) {
        this.reponseBody = reponseBody;
        this.jsonParser = jsonParser;
    }

    public String string() {
        return reponseBody;
    }

    public Map<String, Object> map() {
        return jsonParser.asMap(reponseBody);
    }

    public Map result() {
        String jsonString = (String) map().get("Result");
        return jsonParser.asMap(jsonString);
    }

    public <T> T result(Class<T> clazz) {
        String jsonString = (String) map().get("Result");
        return jsonParser.stringTo(clazz, jsonString);
    }

}

