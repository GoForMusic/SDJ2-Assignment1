package heating.views.main;

import heating.core.ViewHandler;
import heating.core.ViewModelFactory;
import heating.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;

public class HeatingController implements ViewController {
    @FXML private Label thermometer0;
    @FXML private Label thermometer1;
    @FXML private Label thermometer2;
    @FXML private Label warningMessage;
    @FXML private LineChart chart;
    @FXML private Label radiatorPower;

    private HeatingVM vm;
    private ViewHandler viewHandler;

    public void init(ViewHandler vh, ViewModelFactory vfm) {
        viewHandler = vh;
        vm = vfm.getHeatingVM();
        thermometer0.textProperty().bindBidirectional(vm.getThermometer0());
        //thermometer0.textProperty().bind(vm.getThermometer0());
        thermometer1.textProperty().bind(vm.getThermometer1());
        thermometer2.textProperty().bind(vm.getThermometer2());
        warningMessage.textProperty().bind(vm.warningLabelProperty());
        radiatorPower.textProperty().bind(vm.radiatorValueProperty());
    }

    public void testFunc(ActionEvent actionEvent) {
        System.out.println("XXX");
        vm.turnUp();

    }
}
