package com.bigpanda.assignment.logic;

import com.bigpanda.assignment.entity.Event;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class EventsRepository {

    @Getter
    private ConcurrentHashMap<String, Integer> eventCount = new ConcurrentHashMap();

    @Getter
    private ConcurrentHashMap<String, Integer> dataCount = new ConcurrentHashMap();


    public void addEvent(Event e) {
        updateEventCount(e);
        updateDataCount(e);
    }

    private void updateDataCount(Event e) {
        if (dataCount.containsKey(e.data))
            dataCount.put(e.data, dataCount.get(e.data) + 1);
        else
            dataCount.put(e.data, 1);
    }

    private void updateEventCount(Event e) {
        if (eventCount.containsKey(e.eventType))
            eventCount.put(e.eventType, eventCount.get(e.eventType) + 1);
        else
            eventCount.put(e.eventType, 1);
    }

    /**
     * Method will return 0 - if eventType is unknown
     *
     * @param eventType string should be an known event type
     * @return amount of event_types
     */
    public int getCountByEventType(String eventType) {
        if (eventCount.containsKey(eventType)) {
            return eventCount.get(eventType);
        }
        System.out.println("unknown eventType: " + eventType);
        return 0;
    }

    public int getDataCount(String data) {
        if (dataCount.containsKey(data)) {
            return dataCount.get(data);
        }
        System.out.println("unknown eventType: " + data);
        return 0;
    }
}
