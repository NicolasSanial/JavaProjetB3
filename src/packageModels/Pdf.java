package packageModels;

import javafx.beans.property.*;
import java.time.LocalDate;

public class Pdf {

    /**
     * Attributs of a Pdf object
     */
    private IntegerProperty id;
    private StringProperty name;
    private ObjectProperty<LocalDate> uploadDate;
    private boolean status_public;

    /**
     * Allow creating empty Pdf objects
     */
    public Pdf(){ super(); }

    /**
     * Constructor Pdf
     */
    public Pdf(int id, String name, LocalDate uploadDate, boolean status) {

        super();
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.uploadDate = new SimpleObjectProperty(uploadDate);
        this.status_public = status;
    }

    public Pdf(LocalDate uploadDate) {
        this.uploadDate = new SimpleObjectProperty(uploadDate);
    }

    /**
     * getters and setters for ID
     */
    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    /**
     * getters and setters for Name
     */
    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }


    /**
     * getters and setters for UploadDate
     */
    public LocalDate getUploadDate() {
        return uploadDate.get();
    }

    public ObjectProperty<LocalDate> uploadDateProperty() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate.set(uploadDate);
    }

    /**
     *  getters and setters for Status
     */
    public boolean isStatus() {
        return status_public;
    }

    public void setStatus(boolean status) {
        this.status_public = status;
    }
}
