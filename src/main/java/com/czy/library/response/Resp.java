package com.czy.library.response;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Resp {

    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    public static Resp success() {
        Resp resp = new Resp();
        resp.setCode(0);
        resp.setMessage("success");
        return resp;
    }

    public static Resp error() {
        Resp resp = new Resp();
        resp.setCode(-1);
        resp.setMessage("error");
        return resp;
    }

    public Resp data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
}
