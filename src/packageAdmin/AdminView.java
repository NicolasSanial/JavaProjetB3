package packageAdmin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminView extends Application {

    private static Stage adminStage;

    public static Stage getAdminStage() { return adminStage; }

    /**
    * Method who implement the start of the administration view
    */
    @Override
    public void start(Stage adminStage){

        this.adminStage = adminStage;
        adminStage.setTitle("Panneau d'administration");
        initAdminView();
    }

    /**
     * Method who configure the initialisation of the administration view
     */
    public void initAdminView() {
        try {

            // Load admin layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AdminView.class.getResource("AdminView.fxml"));
            AnchorPane dataTAbleDisplay = (AnchorPane) loader.load();

            //We create a scene with the loader FXML
            Scene scene = new Scene(dataTAbleDisplay);

            //We pull the controller to get data
            AdminController controller = loader.getController();
            controller.setDataTable(this);

            //Add scene on stage and show stage
            adminStage.setScene(scene);
            adminStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}