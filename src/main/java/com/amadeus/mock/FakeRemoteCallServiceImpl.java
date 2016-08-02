package com.amadeus.mock;

import org.springframework.stereotype.Service;

/**
 * Created by souali on 01/08/2016.
 */
@Service
public class FakeRemoteCallServiceImpl implements FakeRemoteCallService {

    @Override
    public String call(String request, int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (Exception e) {
            System.err.println("e:" + e.getMessage());
        }
        return request;
    }
}
