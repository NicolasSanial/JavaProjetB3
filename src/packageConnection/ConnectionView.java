package packageConnection;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import packageAdmin.AdminView;


public class ConnectionView extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /* Method who configure the initialisation of the connection view */
    @Override
    public void start(Stage connectionStage) throws Exception{
        connectionStage.setTitle("PDF-Stocker");
        Group connectionGroup = new Group();
        Scene connectionScene = new Scene(connectionGroup, 600, 450, Color.LIGHTGREY);

        Button btnAdmin = new Button();
        btnAdmin.setLayoutX(450);
        btnAdmin.setLayoutY(400);
        btnAdmin.setText("Administration");

        /* Action of the page administration button (TO DO BETTER : event linked with the controller)*/
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

        /* Action of the leave button (TO DO BETTER : event linked with the controller)*/
        btnQuit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                connectionStage.close();
            }
        });

        /* fix all componants on the view */
        connectionGroup.getChildren().add(btnQuit);
        connectionGroup.getChildren().add(btnAdmin);
        connectionStage.setScene(connectionScene);
        connectionStage.show();
    }
}
