package packageAdmin;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import packageMain.DateUtil;
import packageModels.User;

public class FormController {

    /**
     * View to edit data of an user
     */
    @FXML
    private TextField loginField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField birthdayField;

    private Stage formStage;
    private User user;
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
    public void setFormStage(Stage dialogStage) {
        this.formStage = dialogStage;
    }

    /**
    * Sets the person to be edited in the dialog.
    */
    public void setUser(User user) {
        this.user = user;

        loginField.setText(user.getLogin());
        passwordField.setText(user.getPassword());
        firstNameField.setText(user.getFirstName());
        lastNameField.setText(user.getLastName());
        emailField.setText(user.getEmail());
        birthdayField.setText(DateUtil.format(user.getBirthday()));
        birthdayField.setPromptText("dd.mm.yyyy");
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
            user.setLogin(loginField.getText());
            user.setPassword(passwordField.getText());
            user.setFirstName(firstNameField.getText());
            user.setLastName(lastNameField.getText());
            user.setEmail(emailField.getText());
            user.setBirthday(DateUtil.parse(birthdayField.getText()));

            okClicked = true;
            formStage.close();
        }
    }

    /**
    * Called when the user clicks cancel.
    */
    @FXML
    private void handleCancel() {
        formStage.close();
    }

    /**
    * Validates the user input in the text fields.
    */
    private boolean isInputValid() {
        String errorMessage = "";

        if (loginField.getText() == null || loginField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (passwordField.getText() == null || passwordField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (emailField.getText() == null || emailField.getText().length() == 0) {
            errorMessage += "No valid street!\n";
        }
        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else {
            if (!DateUtil.validDate(birthdayField.getText())) {
                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {

            // Show the error message
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(formStage);
            alert.setTitle("Champs invalides");
            alert.setHeaderText("Corrigez les champs mal remplis");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}