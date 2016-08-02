package com.amadeus;

import com.amadeus.mock.FakeRemoteCallServiceImpl;
import com.amadeus.service.RemoteCallService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HystrixDemoApplication.class)
public class RemoteCallServiceTest {

    @Autowired
    private RemoteCallService remoteCallService;

    @MockBean
    private FakeRemoteCallServiceImpl fakeRemoteCallService;

    @Test
    public void test_should_return_exact_value() throws Exception {
        when(fakeRemoteCallService.call(anyString(), anyInt())).thenCallRealMethod();
        assertThat(this.remoteCallService.call("test", 4500), is("test"));
    }

    @Test
    public void when_exception_should_call_default_value() throws Exception {
        when(fakeRemoteCallService.call(anyString(), anyInt())).thenThrow(new RuntimeException("exception 1"));
        assertThat(this.remoteCallService.call("test", 0), is("default"));
    }

    @Test
    public void when_timout_should_return_default_value() throws Exception {
        when(fakeRemoteCallService.call(anyString(), anyInt())).thenCallRealMethod();
        assertThat(this.remoteCallService.call("test", 5500), is("default"));
    }


}
