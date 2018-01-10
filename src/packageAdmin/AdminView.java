package packageAdmin;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AdminView extends Application {

    /* Method who configure the initialisation of the administration view */
    @Override
    public void start(Stage adminStage) {
        adminStage.setTitle("Allo");
        Group adminGroup = new Group();
        Scene adminScene = new Scene(adminGroup, 600, 550, Color.LIGHTGREY);

        Button btnReturn = new Button();
        btnReturn.setLayoutX(500);
        btnReturn.setLayoutY(500);
        btnReturn.setText("Retour");

        /* Action of the return button (TO DO BETTER : event linked with the controller)*/
        btnReturn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                adminStage.close();
            }
        });

        /* display of tableView created with the controller (TO DO BETTER : Try to don't use static method) */
        TableView dataTable = AdminController.getDataTable();

        /* fix all componants on the view */
        adminGroup.getChildren().add(dataTable);
        adminGroup.getChildren().add(btnReturn);
        adminStage.setScene(adminScene);
        adminStage.show();
    }
}
