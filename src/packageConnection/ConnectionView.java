package packageConnection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import packageAdmin.AdminView;

import java.io.IOException;


public class ConnectionView extends Application {

    private Stage connectionStage;

    /* Method who configure the initialisation of the connection view */
    @Override
    public void start(Stage connectionStage) throws Exception{

        this.connectionStage = connectionStage;

        initConnectionView();

    }

    public void initConnectionView() {
        try {
            // Load root layout from fxml file.
            Parent root = FXMLLoader.load(getClass().getResource("ConnectionViewXML.fxml"));
            Scene connectionScene = new Scene(root);

            connectionStage.setScene(connectionScene);
            connectionStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

        /*
        Button btnAdmin = new Button();
        btnAdmin.setLayoutX(450);
        btnAdmin.setLayoutY(400);
        btnAdmin.setText("Administration");
        */

        /*
        btnAdmin.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                new AdminView().start(stage);
                connectionStage.close();
            }
        });

        Button btnQuit = new Button();
        btnQuit.setLayoutX(50);
        btnQuit.setLayoutY(400);
        btnQuit.setText("Quitter");
        */
        /* Action of the leave button (TO DO BETTER : event linked with the controller)*/
        /*
        btnQuit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                connectionStage.close();
            }
        });
        */
        /* fix all componants on the view */