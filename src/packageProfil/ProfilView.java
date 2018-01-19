package packageProfil;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
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

            //final FileChooser fileChooser = new FileChooser();

            //configuringFileChooser(fileChooser);

            Stage ImportPdfStage = new Stage();
            ImportPdfStage.setTitle("Import de PDF");
            ImportPdfStage.initModality(Modality.WINDOW_MODAL);
            ImportPdfStage.initOwner(profilStage);
            Scene scene = new Scene(page);
            ImportPdfStage.setScene(scene);

            ImportPdfController controller = loader.getController();
            controller.setImportPdfStage(ImportPdfStage);
            //controller.setPdf(pdf);

            // Show the dialog and wait until the user closes it
            ImportPdfStage.showAndWait();

            return controller.isOkClicked();

            //fileChooser.showOpenDialog(profilStage);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
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

