package packageProfil;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import packageModels.Pdf;

import java.io.File;
import java.time.LocalDate;

public class ImportPdfController {

    private Stage importPdfStage;
    private Pdf pdf;
    private boolean okClicked = false;

    /**
     * Fields binded on the FXML
     */
    @FXML
    private TextField namePdfField;

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
        // TODO : faire un if qui appel la methode de vérification isInputValid


            LocalDate date = LocalDate.now();
            Pdf newPdf = new Pdf(date);

            final FileChooser fileChooser = new FileChooser();
            configuringFileChooser(fileChooser);
            fileChooser.setTitle("Ouvrir le pdf");
            File file = fileChooser.showOpenDialog(importPdfStage);

            if (file != null) {
                namePdfField.setText(file.getName());
                newPdf.setName(namePdfField.getText());
                okClicked = true;
                importPdfStage.close();
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

    private static void configuringFileChooser(FileChooser fileChooser) {
        // Set title for FileChooser
        fileChooser.setTitle("Select Some Files");

        // Set Initial Directory
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            /*
            user.setLogin(loginField.getText());
            user.setPassword(passwordField.getText());
            user.setFirstName(firstNameField.getText());
            user.setLastName(lastNameField.getText());
            user.setEmail(emailField.getText());
            user.setBirthday(DateUtil.parse(birthdayField.getText()));

            okClicked = true;
            */
            importPdfStage.close();
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
