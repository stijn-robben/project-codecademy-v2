package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StudentView {
    public void studentView(Stage stage, MainView mainView) {
        BorderPane borderPane = new BorderPane();
        Button toMainView = new Button("Back to home");
        toMainView.setOnAction((event) -> {
            mainView.mainView(stage);
        });
        borderPane.setCenter(toMainView);
        Scene studentView = new Scene(borderPane);
        stage.setScene(studentView);
        stage.show();
    }
}
