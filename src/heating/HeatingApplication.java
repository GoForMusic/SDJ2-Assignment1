package heating;

import heating.core.ModelFactory;
import heating.core.ViewHandler;
import heating.core.ViewModelFactory;
import heating.external.Thermometer;
import javafx.application.Application;
import javafx.stage.Stage;

public class HeatingApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ModelFactory mf = new ModelFactory();
        ViewModelFactory vmf = new ViewModelFactory(mf);


        Thermometer thermometer1 = new Thermometer("t1",1,mf);
        Thermometer thermometer2 = new Thermometer("t2", 7, mf);
        Thermometer thermometer3 = new Thermometer("t0", 10, mf);
        Thread thread1 = new Thread(thermometer1);
        Thread thread2 = new Thread(thermometer2);
        Thread thread3 = new Thread(thermometer3);
       thread1.setDaemon(true);
        thread1.start();
       thread2.setDaemon(true);
        thread2.start();
        thread3.setDaemon(true);
        thread3.start();

        ViewHandler vh = new ViewHandler(vmf);
        vh.start();
    }


}
