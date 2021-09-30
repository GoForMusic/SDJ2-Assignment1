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

    private int count1, count2, count3;
    private HeatingVM vm;
    private ViewHandler viewHandler;

    public void init(ViewHandler vh, ViewModelFactory vfm) {
        viewHandler = vh;
        vm = vfm.getHeatingVM();


        //chart
        number1 = new XYChart.Series<>();
        number1.setName("Indoor (t1)");
        number1.setData(vm.getList1());

        number2 = new XYChart.Series<>();
        number2.setName("Indoor (t2)");
        number2.setData(vm.getList2());

        number3 = new XYChart.Series<>();
        number3.setName("Outdoor (t0)");
        number3.setData(vm.getList3());

        chart.getData().add(number1);
        chart.getData().add(number2);
        chart.getData().add(number3);

        //``


        //thermometer0.textProperty().bindBidirectional(vm.getThermometer0());
        thermometer0.textProperty().bind(vm.getThermometer0());
        thermometer1.textProperty().bind(vm.getThermometer1());
        thermometer2.textProperty().bind(vm.getThermometer2());
        warningMessage.textProperty().bind(vm.warningLabelProperty());
        radiatorPower.textProperty().bind(vm.radiatorValueProperty());
    }

    public void upPower(ActionEvent actionEvent) {
        vm.turnUp();
    }

    public void downPower(ActionEvent actionEvent) {
        vm.turnDown();
    }
}
