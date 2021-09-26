package heating.views.main;

import heating.model.HeatingModel;
import heating.model.RadiatorState;
import heating.model.radiator.Radiator;
import heating.model.temperature.Temperature;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HeatingVM implements PropertyChangeListener {
    private StringProperty thermometer0;
    private StringProperty thermometer1;
    private StringProperty thermometer2;
    private StringProperty radiatorValue;
    private StringProperty warningLabel;

    //ref to interfaces
    private HeatingModel modelFactory;
    private RadiatorState radiatorState;

    public HeatingVM(HeatingModel model){
        this.modelFactory=model;
        this.radiatorState=(RadiatorState) model;
        thermometer0 =new SimpleStringProperty();
        thermometer1= new SimpleStringProperty();
        thermometer2= new SimpleStringProperty();
        radiatorValue = new SimpleStringProperty();
        warningLabel = new SimpleStringProperty();
        modelFactory.addPropertyChangeListener("Temperature added", this::propertyChangeIndoor);
        modelFactory.addPropertyChangeListener("Outdoor Temperature added", this::propertyChangeOutdoor);
        modelFactory.addPropertyChangeListener("Power changed",this::propertyChangeRadiator);
    }

    public StringProperty getThermometer0() {
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
        String temp = propertyChangeEvent.getNewValue()+"";
        if ((int)propertyChangeEvent.getNewValue()==3)
        {
            Platform.runLater(()->warningLabel.set("Max power reached,, decreasing power............"));
        }
        else
        {
            Platform.runLater(()->warningLabel.set(""));
        }
        Platform.runLater(()->radiatorValue.set(temp));
    }

    public void turnUp(){
        radiatorState.turnUp((Radiator) radiatorState);
        System.out.println(radiatorState.getPower());
    }

    public void turnDown(){
        radiatorState.turnDown((Radiator) radiatorState);
    }

    private void propertyChangeOutdoor(PropertyChangeEvent propertyChangeEvent) {
        Temperature temperature =(Temperature) propertyChangeEvent.getNewValue();
        if (temperature.getId().equals("t0"))
        {
            Platform.runLater(() -> thermometer0.set("Thermometer 0: "+temperature.getValue()+""));
        }
        else {
            Platform.runLater(() -> thermometer0.set("No data"));
        }
    }

    private void propertyChangeIndoor(PropertyChangeEvent propertyChangeEvent) {
        Temperature temperature = (Temperature) propertyChangeEvent.getNewValue();
        System.out.println(temperature.getId()+"indoor");
        if (temperature.getId().equals("t1"))
        {
            Platform.runLater(() -> thermometer1.set("Thermometer 1: "+temperature.getValue()+""));
        }
        else if (temperature.getId().equals("t2"))
        {
            Platform.runLater(() -> thermometer2.set("Thermometer 2: "+temperature.getValue()+""));
        }
        else {
            Platform.runLater(() -> thermometer1.set("No data"));
            Platform.runLater(() -> thermometer2.set("No data"));
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
