package packageModels;

import javafx.beans.property.*;
import javax.xml.soap.Name;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Pdf {

    private IntegerProperty id;
    private StringProperty name;
    private ObjectProperty<LocalDate> uploadDate;
    private boolean status;

    /**
     * Constructeur Pdf
     */

    public Pdf(int id, String name, ObjectProperty<LocalDate> uploadDate, boolean status) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.uploadDate = new SimpleObjectProperty(uploadDate);
        this.status = status;
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
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
