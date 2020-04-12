package com.bigpanda.assignment.stream;

import com.bigpanda.assignment.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class BusinessLogic {
    @Autowired
    private Validator validator;

    @Autowired
    private EventsRepository eventsRepository;

    public void consumeMessage(String message) {
        Event event = validator.isJsonValid(message);
        if (event != null) {
            System.out.println("event_Type=" + event.eventType.toString() + " : data=" + event.data.toString());
            eventsRepository.addEvent(event);
        }
    }

    public int getEventTypeCount(String eventType) {
        return eventsRepository.getCountByEventType(eventType);
    }
    public ConcurrentHashMap<String, Integer> getAllEventTypesCount()  {
        return eventsRepository.getEventCount();
    }


    public int getDataCount(String data) {
        return eventsRepository.getDataCount(data);
    }
    public ConcurrentHashMap<String, Integer> getAllDataCount()  {
        return eventsRepository.getDataCount();
    }
}
