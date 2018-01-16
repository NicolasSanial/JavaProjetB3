package packageConnection;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import packageAdmin.AdminView;
import packageModels.UserGestionList;
import packageProfil.ProfilView;

public class ConnectionController {

    @FXML
    private TextField loginLabel;
    @FXML
    private TextField passwordLabel;

    @FXML private javafx.scene.control.Button closeButton;

    /**
    * Action of the page administration button
    */
    @FXML
    private void goPageAdminButtonAction() {
        Stage stageAdmin = new Stage();
        new AdminView().start(stageAdmin);
        Stage stageConn = (Stage) closeButton.getScene().getWindow();
        stageConn.close();
    }

    /**
     * Action of the close button
     */
    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void connectionAction() {

        String login = loginLabel.getText();
        String pwd = passwordLabel.getText();

        if(UserGestionList.getInstance().searchUserByLogin(login) == true){
            if(UserGestionList.getInstance().checkPassword(login, pwd) == true){

                Stage stageProfil = new Stage();
                new ProfilView().start(stageProfil);
                Stage stage = (Stage) closeButton.getScene().getWindow();
                stage.close();

            }else{
                // error pop in when pwd not correspond to the login
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(AdminView.getAdminStage());
                alert.setTitle("Mauvais mot de passe");
                alert.setHeaderText("Le mot de passe renseigné est mauvais");
                alert.setContentText("Entrez un mot de passe correspondant au pseudo");

                alert.showAndWait();
            }
        }else{
            // error pop in when login is not recognized
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(AdminView.getAdminStage());
            alert.setTitle("Ce pseudo n'existe pas");
            alert.setHeaderText("Le pseudo n'est pas reconnu");
            alert.setContentText("Entrez un bon pseudo");

            alert.showAndWait();
        }
    }

}
