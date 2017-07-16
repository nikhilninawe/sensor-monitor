package updates;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

/**
 * Created by 14577 on 07/07/17.
 */

@Component
public class SensorUpdates {

    private RestTemplate restTemplate = new RestTemplate();

    /*
       Generates Random temperatures between 190 to 210 for Sensors S0 to S3
     */
    @Scheduled(fixedRate = 200L)
    public void generateRandomUpdates(){
        Random r = new Random();
        double randomValue = 190 + (210 - 190) * r.nextDouble();
        int randomSensor = r.nextInt(4);
        String sensorId = "S" + randomSensor;
        restTemplate.postForObject("http://localhost:8090/sensor/update/" + sensorId + "/" + randomValue,
                                    null, String.class);
    }

}
