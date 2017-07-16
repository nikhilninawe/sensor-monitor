package service;

import domain.Observer;
import domain.Sensor;
import domain.SensorManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by 14577 on 15/07/17.
 */

@Component
public class HeartBeatService {

    private RestTemplate restTemplate = new RestTemplate();
    private static Logger logger = LoggerFactory.getLogger(HeartBeatService.class);

    @Autowired
    SensorManager sensorManager;

    private Map<String, Integer> clientFailureCount = new HashMap<>();

    @Scheduled(initialDelay = 200L, fixedRate = 200L)
    public void checkHearBeat(){
        Map<String, Sensor> sensorMap = sensorManager.getSensorMap();
        if(sensorMap.isEmpty()) {
            return;
        }
        for(Sensor sensor : sensorMap.values()) {
            if(sensor.getObservers().isEmpty()){
                continue;
            }
            Iterator<Observer> observerIterator = sensor.getObservers().iterator();
            while (observerIterator.hasNext()){
                Observer observer = observerIterator.next();
                try {
                    restTemplate.getForEntity("http://" + observer.getClientAddress() + "/client/ping",
                            null, String.class);
                } catch (Exception ex) {
                    logger.error("Got exception {} while pinging client {}", ex.getMessage(), observer.getClientAddress());
                    Integer count = clientFailureCount.getOrDefault(observer.getClientAddress(), 0);
                    count++;

                    if(count >= 3){
                        observerIterator.remove();
                        clientFailureCount.remove(observer.getClientAddress());
                        logger.info("Removed client {}", observer.getClientAddress());
                        continue;
                    }

                    clientFailureCount.put(observer.getClientAddress(), count);
                }
            }
        }

    }

}
