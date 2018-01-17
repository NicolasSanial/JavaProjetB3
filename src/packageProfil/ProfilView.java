package packageProfil;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import packageAdmin.AdminView;

import java.io.File;
import java.io.IOException;

public class ProfilView extends Application {

    private static Stage profilStage;

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

    public static void initImportPDF() {
        try {
            // Load connection layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AdminView.class.getResource("ImportPDF.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            final FileChooser fileChooser = new FileChooser();

            configuringFileChooser(fileChooser);

            Stage formStage = new Stage();
            formStage.setTitle("Modification d'un utilisateur");
            formStage.initModality(Modality.WINDOW_MODAL);
            formStage.initOwner(profilStage);
            Scene scene = new Scene(page);
            formStage.setScene(scene);

            // Part of code how display scene in stage en show stage
            profilStage.setScene(scene);
            fileChooser.showOpenDialog(profilStage);
            profilStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void configuringFileChooser(FileChooser fileChooser) {
        // Set title for FileChooser
        fileChooser.setTitle("Select Some Files");

        // Set Initial Directory
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
    }
}

