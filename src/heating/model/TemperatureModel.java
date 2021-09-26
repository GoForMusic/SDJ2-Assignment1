package heating.model;

import heating.model.temperature.Temperature;

public interface TemperatureModel extends HeatingModel {

    void addTemperature(String id, double value);
    Temperature getLastInsertedTemperature();

    void addOutdoorTemperature(String id, double value);
    Temperature getLastAddedOutdoorTemperature();
}
