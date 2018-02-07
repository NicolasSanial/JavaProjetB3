package packageProfil;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import packageModels.Pdf;
import packageModels.PdfGestionList;
import packageModels.UserGestionDAO;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class PdfSplitController {

    private Stage splitPdfStage;

    private ProfilView profilView;

    private boolean okClicked = false;

    private PDDocument document;

    @FXML
    private TableView<Pdf> pdfTable;
    @FXML
    private TableColumn<Pdf, String> pdfNameColumn;
    @FXML
    private TableColumn<Pdf, String> pdfDateColumn;

    @FXML
    private TextField numberPagePdf;
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

        //To lock the textField to insert only numbers
        numberPagePdf.lengthProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    char ch = numberPagePdf.getText().charAt(oldValue.intValue());
                    // Check if the new character is the number or other's
                    if (!(ch >= '0' && ch <= '9' )) {
                        // if it's not number then just setText to previous one
                        numberPagePdf.setText(numberPagePdf.getText().substring(0,numberPagePdf.getText().length()-1));
                    }
                }
            }

        });

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

    @FXML
    private void handleSplit() {
        //If number page is set and pdf selected
        Pdf pdfSelected = pdfTable.getSelectionModel().getSelectedItem();

        if(pdfSelected != null) {

            if(numberPagePdf.getText().trim().isEmpty() == false){

                try{
                    //we get the selected pdf in the table

                    //convert String to int
                    int numberPage = Integer.parseInt(numberPagePdf.getText());

                    //Set the file to give to get a document
                    //File file = new File(pdfSelected.getPathPdf());

                    //Set the document
                    document = PDDocument.load(new File(pdfSelected.getPathPdf()));

                    //Instantiating Splitter class
                    Splitter splitter = new Splitter();

                    //splitting the pages of a PDF document
                    List<PDDocument> Pages = splitter.split(document);

                    //Creating an iterator
                    Iterator<PDDocument> iterator = Pages.listIterator();

                    //Saving 2 pdf, cutted on an fixed page from an only one pdf
                    int i = 1;
                    PDDocument document1 = new PDDocument();
                    PDDocument document2 = new PDDocument();

                    while(iterator.hasNext()) {

                        if(i <= numberPage){
                            i++;
                            PDDocument doc = iterator.next();
                            PDPage page = doc.getPage(0);
                            document1.addPage(page);

                        }else{
                            i++;
                            PDDocument doc = iterator.next();
                            PDPage page = doc.getPage(0);
                            document2.addPage(page);

                        }
                    }

                    String path = pdfSelected.getPathPdf();

                    //format the path to get 2 differents names for the to created pdf
                    int lastDot = path.lastIndexOf(".");
                    String[] modifiedPath =  {path.substring(0, i), path.substring(i)};

                    //Save the 2 pdf into differents files paths
                    document1.save(modifiedPath + "1.pdf");
                    document1.save(modifiedPath + "2.pdf");

                    document.close();
                    document1.close();
                    document2.close();

                    // pop in appear when nothing selected
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.initOwner(splitPdfStage);
                    alert.setTitle("Pdf coupé en deux");
                    alert.setHeaderText("Le PDF a bien été séparé");
                    alert.setContentText("Vous pourrez retrouver vos pdf dans le dossier de sauvegarde");

                    alert.showAndWait();

                    splitPdfStage.close();

                }catch(IOException io){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.initOwner(splitPdfStage);
                    alert.setTitle("Pdf non trouvé");
                    alert.setHeaderText("Le PDF est introuvable");
                    alert.setContentText("Veuillez selectionner un autre PDF");
                    alert.showAndWait();
                }

            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(splitPdfStage);
                alert.setTitle("pas de numéro de page");
                alert.setHeaderText("Aucune page n'a été selectionnée");
                alert.setContentText("veillez saisir un numéro de page");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(splitPdfStage);
            alert.setTitle("Aucune selection");
            alert.setHeaderText("Aucun pdf n'a été selectionné");
            alert.setContentText("veillez saisir un pdf dans la liste");
            alert.showAndWait();
        }
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
