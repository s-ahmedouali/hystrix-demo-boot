package com.amadeus.service;

import com.amadeus.mock.FakeRemoteCallService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by souali on 01/08/2016.
 */
@Service
public class RemoteCallServiceImpl implements RemoteCallService {

    private final FakeRemoteCallService fakeRemoteCallService;

    @Autowired
    public RemoteCallServiceImpl(FakeRemoteCallService fakeRemoteCallService) {
        this.fakeRemoteCallService = fakeRemoteCallService;
    }


    @Override
    @HystrixCommand(fallbackMethod = "defaultCall", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String call(String request, int sleepTime) {
        return fakeRemoteCallService.call(request, sleepTime);
    }

    public String defaultCall(String request, int sleepTime) {
        return "default";
    }

}
