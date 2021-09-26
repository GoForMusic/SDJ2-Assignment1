package heating.core;

import heating.model.HeatingModel;
import heating.model.RadiatorState;
import heating.model.TemperatureModel;
import heating.model.radiator.Radiator;
import heating.model.temperature.TemperatureManager;
import heating.views.main.HeatingVM;

public class ModelFactory {
    private HeatingModel heatingModel;
    private TemperatureModel temperatureModel;
    private RadiatorState radiator;

    public HeatingModel getHeatingModel()
    {
        if(heatingModel==null)
        {
            heatingModel = new Radiator();
        }
        return heatingModel;
    }


    public TemperatureModel getTemperatureModel() {
        if (temperatureModel == null) {
            temperatureModel = new TemperatureManager();
        }
        return temperatureModel;
    }

    public RadiatorState getRadiator() {
        if (radiator == null) {
            radiator = new Radiator();
        }
        return radiator;
    }
}
