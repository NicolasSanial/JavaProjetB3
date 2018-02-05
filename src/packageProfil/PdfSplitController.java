package packageProfil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import packageModels.Pdf;
import packageModels.PdfGestionList;
import packageModels.UserGestionDAO;

import java.util.List;

public class PdfSplitController {

    private Stage splitPdfStage;

    private ProfilView profilView;

    private boolean okClicked = false;

    @FXML
    private TableView<Pdf> pdfTable;
    @FXML
    private TableColumn<Pdf, String> pdfNameColumn;
    @FXML
    private TableColumn<Pdf, String> pdfDateColumn;

    @FXML
    private TextField numberPage1Pdf;
    @FXML
    private TextField name1Pdf;
    @FXML
    private TextField name2Pdf;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

        // Initialize the person table with the two columns.
        pdfNameColumn.setCellValueFactory(cellData -> cellData.getValue().pdfNameProperty());
        //TODO Voir pour afficher aussi la date

    }

    /**
     * Sets the stage of this dialog.
     */
    public void setSplitPdfStage(Stage dialogStage) {
        this.splitPdfStage = dialogStage;
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        splitPdfStage.close();
    }

    /**
     * Method called on the view to fill
     */
    public void setPdfDataTable(PdfSplitView pdfSplitView) {

        this.profilView = profilView;

        UserGestionDAO.getInstance().loadUserintoList();

        //We pull the UserList instance and convert to collections for listView exploitation
        List<Pdf> listPdf = PdfGestionList.getInstance().getListPdf();
        ObservableList<Pdf> pdfData = FXCollections.observableArrayList(listPdf);

        // Add observable list data to the table
        pdfTable.setItems(pdfData);
    }
}
