package com.bigpanda.assignment.logic;

import com.bigpanda.assignment.entity.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class BusinessLogic {


    @Autowired
    private EventsRepository eventsRepository;

    public void consumeMessage(String message) {
        Event event = this.isJsonValid(message);
        if (event != null) {
            System.out.println("event_Type=" + event.eventType.toString() + " : data=" + event.data.toString());
            eventsRepository.addEvent(event);
        }
    }

    public Number getEventTypeCount(String eventType) {
        return eventsRepository.getCountByEventType(eventType);
    }

    public ConcurrentHashMap<String, Integer> getAllEventTypesCount() {
        return eventsRepository.getEventCount();
    }


    public Number getDataCount(String data) {
        return eventsRepository.getDataCount(data);
    }

    public ConcurrentHashMap<String, Integer> getAllDataCount() {
        return eventsRepository.getDataCount();
    }

    private Event isJsonValid(String test) {
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
