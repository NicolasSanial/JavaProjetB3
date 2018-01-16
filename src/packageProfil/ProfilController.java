package packageProfil;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import packageConnection.ConnectionView;

public class ProfilController {

    @FXML
    private Label userName;

    @FXML private javafx.scene.control.Button closeButton;

    @FXML
    private void goPageConnectionButtonAction() {
        Stage stageConnect = new Stage();
        new ConnectionView().start(stageConnect);
        Stage stageConn = (Stage) closeButton.getScene().getWindow();
        stageConn.close();
    }

    @FXML
    private void closeAction() {
        Stage stageConn = (Stage) closeButton.getScene().getWindow();
        stageConn.close();
    }
}
