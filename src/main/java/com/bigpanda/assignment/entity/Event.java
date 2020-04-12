package com.bigpanda.assignment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class Event {
    @JsonProperty("event_type")
    public String eventType;
    public String data;

    //json Deserializer
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "s")
    public Date timestamp;
}

