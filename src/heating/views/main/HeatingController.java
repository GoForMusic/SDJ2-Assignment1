package heating.views.main;

import heating.core.ViewHandler;
import heating.core.ViewModelFactory;
import heating.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class HeatingController implements ViewController {
    @FXML private Label thermometer0;
    @FXML private Label thermometer1;
    @FXML private Label thermometer2;
    @FXML private Label warningMessage;
    @FXML private LineChart<String, Number> chart;

    private XYChart.Series<String, Number> number1, number2, number3;


    @FXML private Label radiatorPower;

    private HeatingVM vm;
    private ViewHandler viewHandler;

    public void init(ViewHandler vh, ViewModelFactory vfm) {

        //chart
        number1 = new XYChart.Series<>();
        number1.setName("Indoor (t1)");
        //number1.setData();
        number2 = new XYChart.Series<>();
        number2.setName("Indoor (t2)");
        //number2.setData();
        number3 = new XYChart.Series<>();
        number3.setName("Outdoor (t0)");
        //number3.setData();

        viewHandler = vh;
        vm = vfm.getHeatingVM();
        //thermometer0.textProperty().bindBidirectional(vm.getThermometer0());
        thermometer0.textProperty().bind(vm.getThermometer0());
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
