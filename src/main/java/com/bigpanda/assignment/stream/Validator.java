package com.bigpanda.assignment.stream;

import com.bigpanda.assignment.entity.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class Validator {
    public Event isJsonValid(String test) {
        Event event = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            event = objectMapper.readValue(test, Event.class);
        } catch (Exception ex) {
            return null;
        }
        return event;
    }
}
