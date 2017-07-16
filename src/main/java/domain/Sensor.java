package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 14577 on 14/07/17.
 */
public class Sensor {

    private List<Observer> observers = new ArrayList<>();
    private Double temperature;
    private String sensorId;

    public Sensor(String sensorId){
        this.sensorId = sensorId;
    }

    public void add(Observer o) {
        observers.add(o);
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double value) {
        this.temperature = value;
        execute();
    }

    private void execute() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public String getSensorId() {
        return sensorId;
    }

    public List<Observer> getObservers() {
        return observers;
    }
}
