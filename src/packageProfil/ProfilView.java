package packageProfil;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfilView extends Application {

    private Stage profilStage;

    /**
     * Method who configure the initialisation of the connection view
     */
    @Override
    public void start(Stage profilStage){

        this.profilStage = profilStage;
        profilStage.setTitle("Profil Utilisateur");
        initProfilView();
    }

    /**
     * Method how init connection page
     */
    public void initProfilView() {
        try {
            // Load connection layout from fxml file.
            Parent connec = FXMLLoader.load(getClass().getResource("ProfilView.fxml"));
            Scene profilScene = new Scene(connec);

            // Part of code how display scene in stage en show stage
            profilStage.setScene(profilScene);
            profilStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
