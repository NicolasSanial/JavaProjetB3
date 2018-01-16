package packageConnection;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import packageAdmin.AdminView;

public class ConnectionController {

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

}
