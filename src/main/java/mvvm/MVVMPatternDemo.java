package mvvm;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MVVMPatternDemo extends Application {
    private PersonViewModel viewModel;

    @Override
    public void start(Stage primaryStage) {
        Person model = new Person();
        viewModel = new PersonViewModel(model);

        TextField nameTextField = new TextField();
        nameTextField.textProperty().bindBidirectional(viewModel.nameProperty());

        Button displayButton = new Button("Display");
        displayButton.setOnAction(event -> System.out.println("Person: " + viewModel.nameProperty().get()));

        VBox root = new VBox(nameTextField, displayButton);
        Scene scene = new Scene(root, 300, 100);

        primaryStage.setTitle("MVVM Pattern Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}