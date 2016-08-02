package com.amadeus.controller;

import com.amadeus.service.RemoteCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by souali on 02/08/2016.
 */
@RestController
public class RemoteController {
    private final RemoteCallService remoteCallService;

    @Autowired
    public RemoteController(RemoteCallService remoteCallService) {
        this.remoteCallService = remoteCallService;
    }

    @RequestMapping("/call")
    public String greeting(@RequestParam(value = "request", defaultValue = "test") String request, @RequestParam(value = "time", defaultValue = "1000") int sleepTime) {
        return remoteCallService.call(request, sleepTime);
    }
}
