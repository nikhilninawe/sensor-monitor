package domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class Observer {

    private RestTemplate restTemplate;
    private Sensor sensor;
    private String clientAddress;

    private static Logger logger = LoggerFactory.getLogger(Observer.class);

    public Observer(Sensor sensor, String clientAddress, RestTemplate restTemplate) {
        this.sensor = sensor;
        this.clientAddress = clientAddress;
        this.restTemplate = restTemplate;
    }

    public void update(){
        try {
            restTemplate.postForObject("http://" + clientAddress + "/client/update/" + sensor.getSensorId() + "/" + sensor.getTemperature(),
                    null, String.class);
        }catch (Exception  ex){
            logger.info("Exception while pushing update to {}", clientAddress);
        }
    }

    public String getClientAddress() {
        return clientAddress;
    }
}
