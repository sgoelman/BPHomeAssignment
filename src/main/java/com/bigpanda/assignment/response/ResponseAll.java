package com.bigpanda.assignment.response;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ResponseAll {
    public HashMap<String, Integer> result;

    public ResponseAll(ConcurrentHashMap<String, Integer> data) {
        result = new HashMap<>(data);
    }

}
