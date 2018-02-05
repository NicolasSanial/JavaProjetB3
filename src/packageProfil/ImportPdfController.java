package packageProfil;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import packageModels.Pdf;
import packageModels.PdfGestionDAO;
import packageModels.PdfGestionList;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import static packageMain.DateUtil.format;

public class ImportPdfController {

    private Stage importPdfStage;
    private Desktop desktop = Desktop.getDesktop();
    private boolean okClicked = false;
    private Pdf pdf;

    /**
     * Fields binded on the FXML
     */
    @FXML
    private TextField namePdfField;

    @FXML
    private TextField dateUploadPdfField;

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
    public void setImportPdfStage(Stage dialogStage) {
        this.importPdfStage = dialogStage;
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        importPdfStage.close();
    }
    /**
     * Returns true if the user clicked OK, false otherwise.
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleImportOne() {

        final FileChooser fileChooser = new FileChooser();
        configuringFileChooser(fileChooser);

        File file = fileChooser.showOpenDialog(importPdfStage);

        if (file != null) {
            //TODO : Faire un if avec isInputValid pour check que tout les champs sont biens rempli

            LocalDate date = LocalDate.now();

            namePdfField.setText(file.getName());
            dateUploadPdfField.setText(format(date));
            dateUploadPdfField.setEditable(false);

            Pdf newPdf = new Pdf(1, namePdfField.getText(), file.getPath(), file, date, false);

            pdf = newPdf;

        } else {
            // Show the error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(importPdfStage);
            alert.setTitle("Aucun fichier reconnu");
            alert.setHeaderText("Ce fichier n'est pas un PDF");
            alert.setContentText("Veuillez choisir un autre fichier");
            alert.showAndWait();
        }
    }

    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                    ImportPdfController.class.getName()).log(
                    Level.SEVERE, null, ex
            );
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
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {

        if(pdf != null) {

            pdf.setName(namePdfField.getText());

            if (PdfGestionList.getInstance().searchPdfByName(pdf.getName()) == false) {

                if (PdfGestionList.getInstance().searchPdfByFile(pdf.getFile()) == true){

                    String newPath = PdfGestionList.getInstance().moveFileToFolder(pdf.getFile(), pdf.getPathPdf());

                    pdf.setPathPdf(newPath);

                    PdfGestionDAO.getInstance().addPdf(pdf);

                    okClicked = true;
                    importPdfStage.close();

                } else {
                    // Show the error message
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(importPdfStage);
                    alert.setTitle("Erreur de fichier");
                    alert.setHeaderText("Un PDF ayant le même contenu existe");
                    alert.setContentText("Veuillez choisir un autre PDF");
                    alert.showAndWait();
                }

            } else {
                // Show the error message
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(importPdfStage);
                alert.setTitle("Erreur de nom");
                alert.setHeaderText("Un PDF ayant ce nom existe déjà");
                alert.setContentText("Veuillez choisir un autre nom de fichier");
                alert.showAndWait();
            }
        }
    }

    /**
     * Validates the user input in the text fields.
     */
    private boolean isInputValid() {
        /*
        String errorMessage = "";

        if (loginField.getText() == null || loginField.getText().length() == 0) {
            errorMessage += "Login non valide !\n";
        }
        if (passwordField.getText() == null || passwordField.getText().length() == 0) {
            errorMessage += "mot de passe non valide !\n";
        }
        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "prénom non valide !\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "nom de famille non valide !\n";
        }
        if (emailField.getText() == null || emailField.getText().length() == 0) {
            errorMessage += "email non valide !\n";
        }
        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "date de naissance non valide !\n";
        } else {
            if (!DateUtil.validDate(birthdayField.getText())) {
                errorMessage += "Utiliser le format dd.mm.yyyy pour la date !\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {

            // Show the error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(importPdfStage);
            alert.setTitle("Champs invalides");
            alert.setHeaderText("Corrigez les champs mal remplis");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
        */
        return false;
    }
}
