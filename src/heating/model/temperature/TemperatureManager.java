package heating.model.temperature;

import heating.model.TemperatureModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TemperatureManager implements TemperatureModel {


    private TemperatureList temperatureList;
    private PropertyChangeSupport support;
    private TemperatureList outdoortemperatureList;

    public TemperatureManager()
    {
        temperatureList=new TemperatureList();
        outdoortemperatureList = new TemperatureList();
        support = new PropertyChangeSupport(this);
    }

    @Override
    public void addTemperature(String id, double value) {
        Temperature temperature1 = new Temperature(id,value);
        Temperature old = getLastInsertedTemperature();
        temperatureList.addTemperature(temperature1);
        System.out.println("Indoor temperature added");
        // Fire the change of property named Temperature added
        support.firePropertyChange("Temperature added",old,temperature1);
    }

    @Override
    public Temperature getLastInsertedTemperature() {
        return temperatureList.getLastTemperature(null);
    }
    public Temperature getLastAddedOutdoorTemperature()
    {
        return outdoortemperatureList.getLastTemperature(null);
    }

    @Override
    public void addOutdoorTemperature(String id, double value) {
        Temperature temperature = new Temperature(id, value);
        outdoortemperatureList.addTemperature(temperature);
        System.out.println("Outdoor temperature added");
        support.firePropertyChange("Outdoor Temperature added",null,temperature);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void addPropertyChangeListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }
}
