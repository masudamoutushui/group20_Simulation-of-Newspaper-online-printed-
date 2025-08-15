package controller;

import javafx.beans.property.*;
import java.time.LocalDate;

public class Photo {
    private StringProperty imageName;
    private StringProperty category;
    private ObjectProperty<LocalDate> date;
    private StringProperty status;

    public Photo(String imageName, String category, LocalDate date, String status) {
        this.imageName = new SimpleStringProperty(imageName);
        this.category = new SimpleStringProperty(category);
        this.date = new SimpleObjectProperty<>(date);
        this.status = new SimpleStringProperty(status);
    }

    public String getImageName() { return imageName.get(); }
    public void setImageName(String name) { this.imageName.set(name); }
    public StringProperty imageNameProperty() { return imageName; }

    public String getCategory() { return category.get(); }
    public void setCategory(String category) { this.category.set(category); }
    public StringProperty categoryProperty() { return category; }

    public LocalDate getDate() { return date.get(); }
    public void setDate(LocalDate date) { this.date.set(date); }
    public ObjectProperty<LocalDate> dateProperty() { return date; }

    public String getStatus() { return status.get(); }
    public void setStatus(String status) { this.status.set(status); }
    public StringProperty statusProperty() { return status; }
}
