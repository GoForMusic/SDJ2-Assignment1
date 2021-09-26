package heating.model.temperature;

import heating.model.TemperatureModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TemperatureManager implements TemperatureModel {



    private PropertyChangeSupport support;

    public TemperatureManager()
    {
        support = new PropertyChangeSupport(this);
    }

    @Override
    public void addTemperature(String id, double value) {
        Temperature temperature1 = new Temperature(id,value);
        // Fire the change of property named Temperature added


        support.firePropertyChange("Temperature",null,temperature1);
    }

    @Override
    public void addOutdoorTemperature(String id, double value) {
        Temperature temperature = new Temperature(id,value);

        System.out.println("Outdoor temperature added");
        support.firePropertyChange("Outdoor",null,temperature);
    }

    @Override
    public void addPropertyChangeListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName,listener);
    }
}
