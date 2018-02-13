package packageProfil;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import packageModels.Pdf;
import packageModels.PdfGestionDAO;
import packageModels.PdfGestionList;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class MergePdfController {

    private Stage mergePdfStage;
    private Pdf firstPdf;
    private Pdf secondPdf;
    private boolean okClicked = false;
    private boolean firstPdfSet = false;
    private boolean secondPdfSet = false;

    @FXML
    private TextField pdf1Field;

    @FXML
    private TextField pdf2Field;

    @FXML
    private TextField pdfGeneretedField;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     */
    public void setMergePdfStage(Stage dialogStage) {
        this.mergePdfStage = dialogStage;
    }

    @FXML
    private void handleImportFirstPdf() {

        final FileChooser fileChooser = new FileChooser();
        configuringFileChooser(fileChooser);

        File file = fileChooser.showOpenDialog(mergePdfStage);

        if (file != null) {
            //TODO : Faire un if avec isInputValid pour check que tout les champs sont biens rempli

            LocalDate date = LocalDate.now();

            pdf1Field.setText(file.getName());

            Pdf newPdf = new Pdf(1, pdf1Field.getText(), file.getPath(), file, date, false);

            firstPdf = newPdf;

            firstPdfSet = true;

        } else {
            // Show the error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mergePdfStage);
            alert.setTitle("Aucun fichier reconnu");
            alert.setHeaderText("Ce fichier n'est pas un PDF");
            alert.setContentText("Veuillez choisir un autre fichier");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleImportSecondPdf() {

        final FileChooser fileChooser = new FileChooser();
        configuringFileChooser(fileChooser);

        File file = fileChooser.showOpenDialog(mergePdfStage);

        if (file != null) {
            //TODO : Faire un if avec isInputValid pour check que tout les champs sont biens rempli

            LocalDate date = LocalDate.now();

            pdf2Field.setText(file.getName());

            Pdf newPdf = new Pdf(1, pdf1Field.getText(), file.getPath(), file, date, false);

            secondPdf = newPdf;

            secondPdfSet = true;

        } else {
            // Show the error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mergePdfStage);
            alert.setTitle("Aucun fichier reconnu");
            alert.setHeaderText("Ce fichier n'est pas un PDF");
            alert.setContentText("Veuillez choisir un autre fichier");
            alert.showAndWait();
        }
    }

    private static void configuringFileChooser(FileChooser fileChooser) {
        // Set title for FileChooser
        fileChooser.setTitle("Ouvrir le PDF");

        // Set Initial Directory
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
    }

    /**
     * Called when the user clicks ok and put values on object user with the values rihgted in the form, return true to okClicked
     */
    @FXML
    private void handleMerge() {

        if (firstPdfSet == true && secondPdfSet == true){

            try{

                PDDocument document1 = PDDocument.load(firstPdf.getFile());

                PDDocument document2 = PDDocument.load(secondPdf.getFile());

                PDFMergerUtility PDFmerger = new PDFMergerUtility();

                String name = "mergedPdf.pdf";

                pdfGeneretedField.setText(name);

                PDFmerger.setDestinationFileName("/home/sanial/IdeaProjects/JavaProjectB3/PdfFolder/"+ name);

                PDFmerger.addSource(firstPdf.getFile());

                PDFmerger.addSource(secondPdf.getFile());

                PDFmerger.mergeDocuments(null);

                document1.close();
                document2.close();

            }catch(IOException io){

            }

        }else{
            // Show the error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mergePdfStage);
            alert.setTitle("Erreur de fichier");
            alert.setHeaderText("Il manque un des deux PDF");
            alert.setContentText("Veuillez rechoisir les fichiers");
            alert.showAndWait();
        }

    }

    @FXML
    private void handleSave() {

        if (firstPdfSet == true && secondPdfSet == true){

            String testField = pdfGeneretedField.getText();

            if(testField != null && testField != "mergedPdf.pdf"){

                LocalDate date = LocalDate.now();

                File file = new File("/home/sanial/IdeaProjects/JavaProjectB3/PdfFolder/mergedPdf.pdf");


                Pdf newPdf = new Pdf(1, pdfGeneretedField.getText(), file.getPath(), file, date, false);

                String newPath = PdfGestionList.getInstance().moveFileToFolder(newPdf, newPdf.getPathPdf());

                newPdf.setPathPdf(newPath);

                PdfGestionDAO.getInstance().addPdf(newPdf);

                file.delete();

                okClicked = true;
                mergePdfStage.close();

            }else{
                // Show the error message
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mergePdfStage);
                alert.setTitle("Nom manquant ou erreur");
                alert.setHeaderText("Il manque/existe déjà un nom pour ce PDF");
                alert.setContentText("Veuillez entrer le nom du PDF");
                alert.showAndWait();
            }

        }else{
            // Show the error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mergePdfStage);
            alert.setTitle("Erreur de fichier");
            alert.setHeaderText("Il manque un des deux PDF");
            alert.setContentText("Veuillez rechoisir les fichiers");
            alert.showAndWait();
        }

    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        mergePdfStage.close();
    }
    /**
     * Returns true if the user clicked OK, false otherwise.
     */
    public boolean isOkClicked() {
        return okClicked;
    }



}
