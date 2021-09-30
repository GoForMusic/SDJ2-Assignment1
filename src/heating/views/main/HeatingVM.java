package heating.views.main;

import heating.core.ModelFactory;
import heating.model.radiator.Radiator;
import heating.model.temperature.Temperature;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.beans.PropertyChangeEvent;


public class HeatingVM{
    private StringProperty thermometer0;
    private StringProperty thermometer1;
    private StringProperty thermometer2;
    private StringProperty radiatorValue;
    private StringProperty warningLabel;

    //line chart

    private DoubleProperty t0Property, t1Property, t2Property;
    private ObservableList<XYChart.Data<String, Number>> list1, list2, list3;
    private int count1, count2, count3;

    //ref to interfaces
    private ModelFactory modelFactory;

    public HeatingVM(ModelFactory model){
        this.modelFactory=model;

        //linechart

        this.list1 = FXCollections.observableArrayList();
        this.list2 = FXCollections.observableArrayList();
        this.list3 = FXCollections.observableArrayList();
        t0Property = new SimpleDoubleProperty();
        t1Property = new SimpleDoubleProperty();
        t2Property = new SimpleDoubleProperty();
        modelFactory.getTemperatureModel().addPropertyChangeListener("Temperature added", this::propertyChangeIndoor);
        modelFactory.getTemperatureModel().addPropertyChangeListener("Outdoor Temperature added", this::propertyChangeOutdoor);
        //temp

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
    }

    public void turnDown(){
        modelFactory.getRadiator().turnDown((Radiator) modelFactory.getRadiator());
    }

    private void propertyChangeOutdoor(PropertyChangeEvent propertyChangeEvent) {
        Platform.runLater(()->{
            Temperature temperature =(Temperature) propertyChangeEvent.getNewValue();
            if (temperature.getId().equals("t0"))
            {
                if (list3.size()>10)
                {
                    list3.remove(0);
                }
                thermometer0.set("Thermometer 0: "+temperature.getValue()+"");
                list3.add(new XYChart.Data<>((count3++) + "", temperature.getValue()));
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
                if (list1.size()>10)
                {
                    list1.remove(0);
                }
                thermometer1.set("Thermometer 1: "+temperature.getValue()+"");
                list1.add(new XYChart.Data<>((count1++) + "", temperature.getValue()));
            }
            else if (temperature.getId().equals("t2"))
            {
                if (list2.size()>10)
                {
                    list2.remove(0);
                }
                thermometer2.set("Thermometer 2: "+temperature.getValue()+"");
                list2.add(new XYChart.Data<>((count2++) + "", temperature.getValue()));
            }
            else {
                thermometer1.set("No data");
                thermometer2.set("No data");
            }
        });
    }

    public ObservableList<XYChart.Data<String, Number>> getList1() {
        return list1;
    }

    public ObservableList<XYChart.Data<String, Number>> getList2() {
        return list2;
    }

    public ObservableList<XYChart.Data<String, Number>> getList3() {
        return list3;
    }
}
