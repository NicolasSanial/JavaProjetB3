package packageConnection;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import packageAdmin.AdminView;

public class ConnectionController {

    /**
    * Action of the page administration button
    */
    @FXML
    private void goPageAdminButtonAction() {
        Stage stage = new Stage();
        new AdminView().start(stage);
    }

    /**
     * Action of the close button
     */
    @FXML private javafx.scene.control.Button closeButton;
    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
