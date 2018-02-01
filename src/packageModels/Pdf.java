package packageModels;

import javafx.beans.property.*;

import java.io.File;
import java.time.LocalDate;

public class Pdf {

    /**
     * Attributs of a Pdf object
     */
    private IntegerProperty id;
    private StringProperty name;
    private ObjectProperty<LocalDate> uploadDate;
    private File file;
    private boolean status_public;

    /**
     * Allow creating empty Pdf objects
     */
    public Pdf(){ super(); }

    /**
     * Constructor Pdf
     */
    public Pdf(int id, String name, LocalDate uploadDate, File file, boolean status) {

        super();
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.uploadDate = new SimpleObjectProperty(uploadDate);
        this.file = file;
        this.status_public = status;
    }

    /**
     * Constructor to cast new Pdf files with the current date
     * @param uploadDate
     */
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
     *  getters and setters for the file
     */
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    /**
     *  getters and setters for Status
     */
    public boolean getStatus() {
        return status_public;
    }

    public void setStatus(boolean status) {
        this.status_public = status;
    }
}