package service;

import domain.SensorManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sensor")
public class SensorService {

	private static Logger logger = LoggerFactory.getLogger(SensorService.class);

	@Autowired
    SensorManager sensorManager;

	@RequestMapping(value="/subscribe/{clientAddress}/{sensorId}", method = RequestMethod.POST)
	public String subscribe(@PathVariable String clientAddress,
						    @PathVariable String sensorId){
        logger.info("Subscribed {} for updates of sensor {}", clientAddress, sensorId);
        sensorManager.addClientForSensor(sensorId, clientAddress);
        return "Success";
	}

	@RequestMapping(value="/update/{sensorId}/{temperature:.+}", method = RequestMethod.POST)
	public String update(@PathVariable String sensorId,
						 @PathVariable Double temperature){
        logger.info("Temperature {} updated for sensor {}", temperature, sensorId);
        sensorManager.updateSensor(sensorId, temperature);
        return "Success";
	}
}
