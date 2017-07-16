package domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by 14577 on 16/07/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class SensorTest {

    @Mock
    Observer observer;


    @Mock
    Observer observer2;

    @InjectMocks
    Sensor sensor;

    @Test
    public void add() throws Exception {
        sensor.add(observer);
        assertEquals(1, sensor.getObservers().size());
    }

    @Test
    public void setTemperature() throws Exception {
        sensor.add(observer);
        sensor.setTemperature(20.1);
        verify(observer, times(1)).update();
    }


    @Test
    public void setTemperatureFor2Observers() throws Exception {
        sensor.add(observer);
        sensor.add(observer2);
        sensor.setTemperature(20.1);
        verify(observer, times(1)).update();
        verify(observer2, times(1)).update();
    }

}