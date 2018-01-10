package packageAdmin;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import packageModels.User;
import packageModels.UserGestionList;

import java.util.List;

public class AdminController {

    public static TableView getDataTable(){

        TableView dataTable = new TableView();
        dataTable.setLayoutX(50);
        dataTable.setLayoutY(50);

        /* Pull of the listUser with instance into the TableView */
        List<User> listUser = UserGestionList.getInstance().getListUser();
        dataTable.getItems().setAll(listUser);

        /* Create columns of the TableView and bind user attributs */
        final TableColumn<User, Integer> idColumn = new TableColumn<>("Id");
        idColumn.setCellValueFactory(param -> {
            final User user = param.getValue();
            return new SimpleObjectProperty<>(user.getId());
        });
        final TableColumn<User, String> loginColumn = new TableColumn<>("Login");
        loginColumn.setCellValueFactory(param -> {
            final User user = param.getValue();
            return new SimpleStringProperty(user.getLogin());
        });
        final TableColumn<User, String> passwordColumn = new TableColumn<>("Password");
        passwordColumn.setCellValueFactory(param -> {
            final User user = param.getValue();
            return new SimpleStringProperty(user.getPassword());
        });
        final TableColumn<User, String> firstColumn = new TableColumn<>("firstName");
        firstColumn.setCellValueFactory(param -> {
            final User user = param.getValue();
            return new SimpleStringProperty(user.getFirstName());
        });
        final TableColumn<User, String> lastColumn = new TableColumn<>("lastName");
        lastColumn.setCellValueFactory(param -> {
            final User user = param.getValue();
            return new SimpleStringProperty(user.getLastName());
        });

        /* Insert columns into the TableView */
        dataTable.getColumns().addAll(idColumn, loginColumn, passwordColumn, firstColumn, lastColumn);

        return dataTable;
    }

}
