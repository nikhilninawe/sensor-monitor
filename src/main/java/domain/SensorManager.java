package domain;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by 14577 on 15/07/17.
 */

@Component
public class SensorManager {
    private HashMap<String, Sensor> sensorMap = new HashMap<>();
    private RestTemplate restTemplate = new RestTemplate();

    public void addClientForSensor(String sensorId, String clientAddress){
        Sensor sensor = sensorMap.get(sensorId);
        if(sensor == null){
            throw new RuntimeException("No Such Sensor");
        }
        sensor.add(new Observer(sensor, clientAddress, restTemplate));
    }

    public void updateSensor(String sensorId, Double temperature){
        Sensor sensor = sensorMap.get(sensorId);
        if(sensor == null){
            sensor = new Sensor(sensorId);
            sensorMap.put(sensorId, sensor);
        }
        sensor.setTemperature(temperature);
    }

    public HashMap<String, Sensor> getSensorMap() {
        return sensorMap;
    }
}
