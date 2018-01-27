package packageAdmin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import packageModels.User;
import java.io.IOException;

public class AdminView extends Application {

    private static Stage adminStage;

    public static Stage getAdminStage() { return adminStage; }

    /**
     * Method who implement the start of the administration view
     * @param adminStage : the stage of the administration page
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

    /**
     * Show the view to manage user (form to add/modify)
     * @param user : user who will be displayed on the form view
     * @return true if the button OK is pressed
     */
    public static boolean showUserForm(User user) {

        try {

            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AdminView.class.getResource("FormView.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage formStage = new Stage();
            formStage.setTitle("Modification d'un utilisateur");
            formStage.initModality(Modality.WINDOW_MODAL);
            formStage.initOwner(adminStage);
            Scene scene = new Scene(page);
            formStage.setScene(scene);

            // Set the user into the controller.
            FormController controller = loader.getController();
            controller.setFormStage(formStage);
            controller.setUser(user);

            // Show the dialog and wait until the user closes it
            formStage.showAndWait();

            return controller.isOkClicked();

        } catch (IOException e) {

            e.printStackTrace();
            return false;
        }
    }
}