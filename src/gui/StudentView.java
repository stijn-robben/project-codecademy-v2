package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class StudentView {
    public void studentView(Stage stage, MainView mainView) {
        VBox vbox = new VBox();
        vbox.setSpacing(14);
        vbox.setPadding(new Insets(8)); 
        
        Label label1 = new Label("Students");
        label1.setFont(Font.font("Arial", 16));

        Button createStudentButton = new Button("Create Student");
        createStudentButton.setOnAction((event) -> {
            mainView.mainView(stage);
        });
        
        Button toMainView = new Button("Back to home");
        toMainView.setOnAction((event) -> {
            mainView.mainView(stage);
        });
        
        vbox.getChildren().addAll(label1, toMainView);

        Scene studentView = new Scene(vbox, 600, 400);
        stage.setScene(studentView);
        stage.show();
    }
}