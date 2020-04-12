package com.bigpanda.assignment.web;


import com.bigpanda.assignment.response.ResponseAll;
import com.bigpanda.assignment.response.ResponseData;
import com.bigpanda.assignment.stream.BusinessLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/event_data")
public class DataRestController {

    @Autowired
    private BusinessLogic businessLogic;

    @GetMapping
    @ResponseBody
    public ResponseAll getAllData() {
        return new ResponseAll(businessLogic.getAllDataCount());
    }

    @GetMapping(value = "/{data}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseData getByName(@PathVariable String data) {
        return new ResponseData(businessLogic.getDataCount(data));

    }
}
