package heating.views.main;

import heating.core.ModelFactory;
import heating.model.radiator.Radiator;
import heating.model.temperature.Temperature;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;


public class HeatingVM{
    private StringProperty thermometer0;
    private StringProperty thermometer1;
    private StringProperty thermometer2;
    private StringProperty radiatorValue;
    private StringProperty warningLabel;

    //ref to interfaces
    private ModelFactory modelFactory;

    public HeatingVM(ModelFactory model){
        this.modelFactory=model;
        thermometer0 =new SimpleStringProperty();
        thermometer1= new SimpleStringProperty();
        thermometer2= new SimpleStringProperty();
        radiatorValue = new SimpleStringProperty();
        warningLabel = new SimpleStringProperty();
        modelFactory.getTemperatureModel().addPropertyChangeListener("Temperature", this::propertyChangeIndoor);
        modelFactory.getTemperatureModel().addPropertyChangeListener("Outdoor", this::propertyChangeOutdoor);
        modelFactory.getRadiator().addPropertyChangeListener("Power",this::propertyChangeRadiator);
    }

    public StringProperty getThermometer0() {
        System.out.println("TEST VALUE RETURN: "+ thermometer0);
        return thermometer0;
    }

    public StringProperty getThermometer1() {
        return thermometer1;
    }

    public StringProperty getThermometer2() {
        return thermometer2;
    }

    public StringProperty radiatorValueProperty() {
        return radiatorValue;
    }

    public StringProperty warningLabelProperty() {
        return warningLabel;
    }

    private void propertyChangeRadiator(PropertyChangeEvent propertyChangeEvent) {
        Platform.runLater(()->{
            String temp = propertyChangeEvent.getNewValue()+"";
            if ((int)propertyChangeEvent.getNewValue()==3)
            {
                warningLabel.set("Max power reached,, decreasing power............");
            }
            else
            {
                warningLabel.set("");
            }
            radiatorValue.set(temp);
        });
    }

    public void turnUp(){
        modelFactory.getRadiator().turnUp((Radiator) modelFactory.getRadiator());
        System.out.println(modelFactory.getRadiator().getPower());
    }

    public void turnDown(){
        modelFactory.getRadiator().turnDown((Radiator) modelFactory.getRadiator());
    }

    private void propertyChangeOutdoor(PropertyChangeEvent propertyChangeEvent) {
        Platform.runLater(()->{
            Temperature temperature =(Temperature) propertyChangeEvent.getNewValue();
            if (temperature.getId().equals("t0"))
            {
                thermometer0.set("Thermometer 0: "+temperature.getValue()+"");
            }
            else {
                thermometer0.set("No data");
            }
        });
    }

    private void propertyChangeIndoor(PropertyChangeEvent propertyChangeEvent) {
        Temperature temperature = (Temperature)propertyChangeEvent.getNewValue();
        Platform.runLater(()->{
            if (temperature.getId().equals("t1"))
            {
                thermometer1.set("Thermometer 1: "+temperature.getValue()+"");
            }
            else if (temperature.getId().equals("t2"))
            {
                thermometer2.set("Thermometer 2: "+temperature.getValue()+"");
            }
            else {
                thermometer1.set("No data");
                thermometer2.set("No data");
            }
        });
    }
}
