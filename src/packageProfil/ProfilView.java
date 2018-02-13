package packageProfil;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import packageModels.Pdf;

import java.io.IOException;

public class ProfilView extends Application {

    private static Stage profilStage;

    public static Stage getProfilStage() { return profilStage; }

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

    public static boolean initImportPDF() {
        try {
            // Load connection layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ProfilView.class.getResource("ImportPDF.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage importPdfStage = new Stage();
            importPdfStage.setTitle("Import de PDF");
            importPdfStage.initModality(Modality.WINDOW_MODAL);
            importPdfStage.initOwner(profilStage);
            Scene scene = new Scene(page);
            importPdfStage.setScene(scene);

            ImportPdfController controller = loader.getController();
            controller.setImportPdfStage(importPdfStage);

            // Show the dialog and wait until the user closes it
            importPdfStage.showAndWait();

            return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Show the view to manage user (form to add/modify)
     * @return true if the button OK is pressed
     */
    public static boolean initMergePdf() {

        try {

            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ProfilView.class.getResource("mergePdfView.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage mergePdfStage = new Stage();
            mergePdfStage.setTitle("Merge de Pdf");
            mergePdfStage.initModality(Modality.WINDOW_MODAL);
            mergePdfStage.initOwner(profilStage);
            Scene scene = new Scene(page);
            mergePdfStage.setScene(scene);

            // Set the user into the controller.
            MergePdfController controller = loader.getController();
            controller.setMergePdfStage(mergePdfStage);

            // Show the dialog and wait until the user closes it
            mergePdfStage.showAndWait();

            return controller.isOkClicked();

        } catch (IOException e) {

            e.printStackTrace();
            return false;
        }
    }
}

