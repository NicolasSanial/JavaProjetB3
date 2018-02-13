package packageProfil;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PdfSplitView extends Application {

    private static Stage splitStage;

    public static Stage getSplitStage() { return splitStage; }

    public PdfSplitView getThisSplitView() {

        return this;
    }

    /**
     * Method who configure the initialisation of the connection view
     */
    @Override
    public void start(Stage splitStage){

        this.splitStage = splitStage;
        splitStage.setTitle("Split de PDF");
        initSplitPdfView();
    }

    public boolean initSplitPdfView() {
        try {
            // Load connection layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ProfilView.class.getResource("SplitPdfView.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage SplitPdfStage = new Stage();
            SplitPdfStage.setTitle("Split un PDF");
            SplitPdfStage.initModality(Modality.WINDOW_MODAL);
            SplitPdfStage.initOwner(splitStage);
            Scene scene = new Scene(page);
            SplitPdfStage.setScene(scene);


            //We pull the controller to get data
            PdfSplitController controller = loader.getController();
            controller.setSplitPdfStage(SplitPdfStage);
            controller.setPdfDataTable(this);

            //Add scene on stage and show stage
            SplitPdfStage.setScene(scene);
            SplitPdfStage.show();
            //return controller.isOkClicked();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
