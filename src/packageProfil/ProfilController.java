package packageProfil;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import packageConnection.ConnectionView;

public class ProfilController {

    private ProfilView profilView;

    @FXML
    private javafx.scene.control.Button closeButton;

    /**
     * Constructor (called in the view)
     */
    public ProfilController(){}

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize(){

    }

    @FXML
    private Label userName;

    @FXML
    private void goPageConnectionButtonAction() {
        Stage stageConnect = new Stage();
        new ConnectionView().start(stageConnect);

        Stage stageConn = (Stage) closeButton.getScene().getWindow();
        stageConn.close();
    }

    @FXML
    private void closeAction() {
        Stage stagePro = (Stage) closeButton.getScene().getWindow();
        stagePro.close();
    }

    @FXML
    private void importPdfPageAction() {
        ProfilView.initImportPDF();
    }

    @FXML
    private void splitPageAction() {
        Stage stageConnect = new Stage();
        new PdfSplitView().start(stageConnect);
    }

    @FXML
    private void mergePageAction() {
        ProfilView.initMergePdf();
    }

}