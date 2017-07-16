package domain;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by 14577 on 15/07/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class SensorManagerTest {

    @InjectMocks
    SensorManager sensorManager;

    @Test(expected = RuntimeException.class)
    public void addClientForSensorException() throws Exception {
        sensorManager.addClientForSensor("S1", "C1");
    }

    @Test
    public void addClientForSensor() throws Exception {
        sensorManager.updateSensor("S1", 20.2);
        sensorManager.addClientForSensor("S1", "C1");
        assertEquals(1, sensorManager.getSensorMap().size());
    }
}