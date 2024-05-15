package mvvm;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MVVMPatternDemo extends Application {
    private PersonViewModel viewModel;

    @Override
    public void start(Stage primaryStage) {
        Person model = new Person();
        viewModel = new PersonViewModel(model);

        TextField nameTextField = new TextField();
        nameTextField.textProperty().bindBidirectional(viewModel.nameProperty());
        nameTextField.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                log.info("name text field invalidated, current value: {}", nameTextField.textProperty().get());
            }
        });
        nameTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                log.info("name text field changed, current value: {}", nameTextField.textProperty().get());
            }
        });

        Button displayButton = new Button("Display");
        displayButton.setOnAction(event -> System.out.println("Person: " + viewModel.nameProperty().get()));

        Button appendButton = new Button("Append");
        appendButton.setOnAction(event -> {
            // 点击 appendButton ，给 model 中的 name 属性追加一个 ~
            model.nameProperty().setValue(model.getName() + "~");
        });

        Button replaceButton = new Button("Replace");
        replaceButton.setOnAction(event -> {
            if (nameTextField.getLength() > 1) {
                nameTextField.replaceText(0, 1, "*");
            }
        });

        VBox root = new VBox(nameTextField, displayButton, appendButton, replaceButton);
        Scene scene = new Scene(root, 300, 100);

        primaryStage.setTitle("MVVM Pattern Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}