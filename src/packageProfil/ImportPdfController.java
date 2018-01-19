package packageProfil;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import packageModels.Pdf;

public class ImportPdfController {

    private Stage importPdfStage;
    private Pdf pdf;
    private boolean okClicked = false;

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
     * Sets the person to be edited in the dialog.
     */
    public void setPdf(Pdf pdf) {
        this.pdf = pdf;

        /*
        loginField.setText(user.getLogin());
        passwordField.setText(user.getPassword());
        firstNameField.setText(user.getFirstName());
        lastNameField.setText(user.getLastName());
        emailField.setText(user.getEmail());
        birthdayField.setText(DateUtil.format(user.getBirthday()));
        birthdayField.setPromptText("dd.mm.yyyy");
        */
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     */
    public boolean isOkClicked() {
        return okClicked;
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
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        importPdfStage.close();
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
