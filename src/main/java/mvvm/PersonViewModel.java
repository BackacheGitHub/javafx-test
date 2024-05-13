package mvvm;

import javafx.beans.property.StringProperty;

// ViewModel
class PersonViewModel {
    private Person model;

    public PersonViewModel(Person model) {
        this.model = model;
    }

    public StringProperty nameProperty() {
        return model.nameProperty();
    }
}