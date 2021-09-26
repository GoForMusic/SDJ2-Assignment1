package heating.core;


import heating.views.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler {
    private ViewModelFactory vmf;
    private Stage mainStage;

    //specific scenes
    private Scene heatingScene;


    //open the first scene
    public void start()
    {
        openHeatingScene();
    }

    public ViewHandler(ViewModelFactory vmf)
    {
        this.vmf = vmf;
        mainStage = new Stage();
    }

    //Add open method for specific scene
    public void openHeatingScene()  {

        try {
            heatingScene= getScene("../views/main/Heating.fxml");

        } catch (IOException e) {
            e.printStackTrace();
        }

        mainStage.setTitle("Heating Controller");
        mainStage.setScene(heatingScene);
        mainStage.show();

    }

//===

    private Scene getScene(String path) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        Parent root = loader.load();
        ViewController view = loader.getController();
        view.init(this, vmf);
        return new Scene(root);
    }

}
