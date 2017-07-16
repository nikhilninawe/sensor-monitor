package domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by 14577 on 16/07/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ObserverTest {

    @InjectMocks
    Observer observer;

    @Mock
    RestTemplate restTemplate;

    @Mock
    Sensor sensor;

    @Test
    public void update() throws Exception {
        observer.update();
        verify(restTemplate, times(1)).postForObject(anyString(), any(), any());
    }

}