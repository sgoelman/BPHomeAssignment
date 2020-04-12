package com.bigpanda.assignment.response;

public class ResponseData {
    public Object result;

    public ResponseData(Number data) {
        if (data == null) {
            result = "Error couldn't find required data ";
        } else {
            result = data;
        }

    }
}
