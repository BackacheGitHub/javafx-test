package mvvm;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model
 */
class Person {
    private final SimpleStringProperty name = new SimpleStringProperty("");

    Person() {
        name.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
//                System.out.println("invalidated listener triggered, name: " + name.get());
            }
        });
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }
}