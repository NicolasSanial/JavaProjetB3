package packageAdmin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import packageConnection.ConnectionView;
import packageMain.DateUtil;
import packageModels.User;
import packageModels.UserGestionList;

import java.time.LocalDate;
import java.util.List;

public class AdminController {

    //Definition of column of listView and label how display user stat
    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> firstNameColumn;
    @FXML
    private TableColumn<User, String> lastNameColumn;
    @FXML
    private Label idLabel;
    @FXML
    private Label loginLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label birthdayLabel;

    //To use the view in the controller
    private AdminView adminView;

    /**
    * Constructor (called in the view)
    */
    public AdminController(){}

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize(){

        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        //To erase data before display anyone
        showUserDetails(null);

        // Bind a listener on listView to dispay clicked user with showUserDetails
        userTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showUserDetails(newValue));
    }

    /**
     * Method called on the view to fill
     */
    public void setDataTable(AdminView adminView) {

        this.adminView = adminView;

        //We pull the UserList instance and convert to collections for listView exploitation
        List<User> listUser = UserGestionList.getInstance().getListUser();
        ObservableList<User> userData = FXCollections.observableArrayList(listUser);

        // Add observable list data to the table
        userTable.setItems(userData);
    }

    /**
     * Method how bind user data with label on the view
     */
    private void showUserDetails(User user) {
        if (user != null) {

            // Fill the labels with info from the user object
            idLabel.setText(Integer.toString(user.getId()));
            loginLabel.setText(user.getLogin());
            passwordLabel.setText(user.getPassword());
            firstNameLabel.setText(user.getFirstName());
            lastNameLabel.setText(user.getLastName());
            emailLabel.setText(user.getEmail());
            birthdayLabel.setText(DateUtil.format(user.getBirthday()));

        } else {

            // User is null, remove all the text
            idLabel.setText("");
            loginLabel.setText("");
            passwordLabel.setText("");
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            emailLabel.setText("");
            birthdayLabel.setText("");
        }
    }

    /**
     * Action of the close button
     */
    @FXML
    private javafx.scene.control.Button closeButton;
    @FXML
    private void closeButtonAction() {
        Stage stageConn = new Stage();
        new ConnectionView().start(stageConn);
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    /**
     * Handle the delete user action when button is clicked
     * TODO: Il y a une exeption sur cette page lorsque la pop up d'erreur s'affiche, resoudre ano
     */
    @FXML
    private void handleDeleteUser() {

        //Delete user on the front side
        User selectedPerson = userTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            userTable.getItems().remove(selectedPerson);
        } else {
            // pop in appear when nothing selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(AdminView.getAdminStage());
            alert.setTitle("pas de selection");
            alert.setHeaderText("Aucun utilisateur n'a été selectionné");
            alert.setContentText("selectionnez un utilisateur dans la liste");

            alert.showAndWait();
        }

        //Delete user on the listUser
        List<User> listUser = UserGestionList.getInstance().getListUser();

        for(int i=0;i < listUser.size();i++){
            User u = listUser.get(i);
            if(u != null) {
                if (u.getFirstName() == selectedPerson.getFirstName() && u.getLastName() == selectedPerson.getLastName()) {
                    UserGestionList.getInstance().removeUserListByObJ(selectedPerson);
                }
            }
        }
    }

    @FXML
    private void handleCreateUser() {
        User tempUser = new User(1, "Kant","password" ,"nicolas","sanial","nico.san@smile.fr", LocalDate.of(1994, 2, 21));
        boolean okClicked = AdminView.showUserForm(tempUser);
        if (okClicked) {
            UserGestionList.getInstance().getListUser().add(tempUser);
        }
    }

    @FXML
    private void handleEditPerson() {
        User selectedPerson = userTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = AdminView.showUserForm(selectedPerson);
            if (okClicked) {
                showUserDetails(selectedPerson);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(AdminView.getAdminStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
}
