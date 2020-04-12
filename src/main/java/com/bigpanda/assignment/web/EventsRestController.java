package com.bigpanda.assignment.web;


import com.bigpanda.assignment.response.ResponseAll;
import com.bigpanda.assignment.response.ResponseData;
import com.bigpanda.assignment.logic.BusinessLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/events")
public class EventsRestController {

    @Autowired
    private BusinessLogic businessLogic;

    @GetMapping
    @ResponseBody
    public ResponseAll getAllEvents() {
        return new ResponseAll(businessLogic.getAllEventTypesCount());
    }

    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseData getEventByName(@PathVariable String name) {
        return new ResponseData(businessLogic.getEventTypeCount(name));
    }

    @GetMapping(value = "/data")
    @ResponseBody
    public ResponseAll getAllData() {
        return new ResponseAll(businessLogic.getAllDataCount());
    }

    @GetMapping(value = "/data/{data}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseData getByName(@PathVariable String data) {
        return new ResponseData(businessLogic.getDataCount(data));

    }
}


