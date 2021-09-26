package heating.model;



public interface TemperatureModel extends HeatingModel {

    void addTemperature(String id, double value);
    void addOutdoorTemperature(String id, double value);

}
