package packageConnection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ConnectionView extends Application {

    private Stage connectionStage;

    /**
    * Method who configure the initialisation of the connection view
    */
    @Override
    public void start(Stage connectionStage){

        this.connectionStage = connectionStage;
        connectionStage.setTitle("PDF-Stocker");

        initConnectionView();
    }

    /**
     * Method how init connection page
     */
    public void initConnectionView() {
        try {

            // Load connection layout from fxml file.
            Parent connec = FXMLLoader.load(getClass().getResource("ConnectionView.fxml"));
            Scene connectionScene = new Scene(connec);

            // Part of code how display scene in stage en show stage
            connectionStage.setScene(connectionScene);
            connectionStage.show();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
