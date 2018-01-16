package packageModels;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.Date;

public class User {

    //Attributs of User objects
    private IntegerProperty id;
    private StringProperty login;
    private StringProperty password;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty email;
    private ObjectProperty<LocalDate> birthday;

    /**
    * Constructor with all fields (test data loading)
    */
    public User(int id, String login, String password, String firstName, String lastName, String email, LocalDate birthday) {
        this.id = new SimpleIntegerProperty(id);
        this.login = new SimpleStringProperty(login);
        this.password = new SimpleStringProperty(password);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
        this.birthday = new SimpleObjectProperty(birthday);
    }

    /**
     * Constructor with all fields without id (for the auto increment of id in BD)
     */
    public User(String login, String password, String firstName, String lastName, String email, Date birthday) {
        this.login = new SimpleStringProperty(login);
        this.password = new SimpleStringProperty(password);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
        this.birthday = new SimpleObjectProperty(birthday);
    }

    /**
     * Without firstName, lastName, birthday
     */
    public User(String login, String password, String email) {
        this.login = new SimpleStringProperty(login);
        this.password = new SimpleStringProperty(password);
        this.email = new SimpleStringProperty(email);
    }

    /**
     * Constructor to create empty users to create them
     */
    public User(){}

    /**
     * Methods Get/Set of attributs (*Type*Property for ListView display)
     */
    public int getId() {
        return id.get();
    }

    public void setId(int id) { this.id.set(id); }

    public IntegerProperty idProperty() { return id; }

    public String getLogin() {
        return login.get();
    }

    public void setLogin(String login) { this.login.set(login); }

    public StringProperty loginProperty() { return login; }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) { this.password.set(password); }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() { return firstName; }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) { this.lastName.set(lastName); }

    public StringProperty lastNameProperty() { return lastName; }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) { this.email.set(email); }

    public LocalDate getBirthday() {
        return birthday.get();
    }

    public void setBirthday(LocalDate birthday) { this.birthday.set(birthday); }

}










