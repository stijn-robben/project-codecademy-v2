package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.CourseHandler;

public class CourseView {
    public void courseView(Stage stage, MainView mainView) {
        BorderPane borderpane = new BorderPane();
        VBox vbox = new VBox();
        vbox.setSpacing(14);
        vbox.setPadding(new Insets(8));

        Label label1 = new Label("Courses");
        label1.setFont(Font.font("Arial", 16));

        Button toMainView = new Button("Back to home");
        toMainView.setOnAction((event) -> {
            mainView.mainView(stage);
        });

        RadioButton radioButton1 = new RadioButton("Create a Course");
        ToggleGroup toggleGroup = new ToggleGroup();
        radioButton1.setToggleGroup(toggleGroup);
        radioButton1.setSelected(true);

        RadioButton radioButton2 = new RadioButton("Get all Courses");
        radioButton2.setToggleGroup(toggleGroup);

        RadioButton radioButton3 = new RadioButton("Update a Course");
        radioButton3.setToggleGroup(toggleGroup);

        RadioButton radioButton4 = new RadioButton("Delete a Course");
        radioButton4.setToggleGroup(toggleGroup);

        HBox crudOptions = new HBox();
        crudOptions.setPadding(new Insets(10));
        crudOptions.setSpacing(20);
        crudOptions.getChildren().addAll(radioButton1, radioButton2, radioButton3, radioButton4);

        TextArea textArea = new TextArea();
        textArea.setEditable(false);

        TextField courseNameTextField = new TextField();
        TextField subjectTextField = new TextField();
        TextArea introductionTextTextArea = new TextArea();
        ObservableList<String> optionsList = FXCollections.observableArrayList(
                "beginner",
                "gevordert",
                "expert");

        ChoiceBox<String> level = new ChoiceBox<>(optionsList);

        radioButton1.selectedProperty().addListener((observable, oldValue, newValue) -> {
            courseNameTextField.setDisable(false);
            subjectTextField.setDisable(false);
            level.setDisable(false);
            introductionTextTextArea.setDisable(false);
            
            clearInputFields(courseNameTextField, subjectTextField, level, textArea);
        });

        radioButton2.selectedProperty().addListener((observable, oldValue, newValue) -> {
            courseNameTextField.setDisable(true);
            subjectTextField.setDisable(true);
            level.setDisable(true);
            introductionTextTextArea.setDisable(true);
            clearInputFields(courseNameTextField, subjectTextField, level, textArea);
        });

        radioButton3.selectedProperty().addListener((observable, oldValue, newValue) -> {
            courseNameTextField.setDisable(false);
            subjectTextField.setDisable(false);
            level.setDisable(false);
            introductionTextTextArea.setDisable(false);
            clearInputFields(courseNameTextField, subjectTextField, level, textArea);
        });

        radioButton4.selectedProperty().addListener((observable, oldValue, newValue) -> {
            courseNameTextField.setDisable(false);
            subjectTextField.setDisable(true);
            level.setDisable(true);
            introductionTextTextArea.setDisable(true);
            clearInputFields(courseNameTextField, subjectTextField, level, textArea);
        });

        Button clear = new Button("Clear fields");
        clear.setOnAction((event) -> {
            clearInputFields(courseNameTextField, subjectTextField, level, textArea);
        });

        //////// not working yet
        Button submit = new Button("Submit");
        submit.setOnAction(e -> {
            Toggle selectedToggle = toggleGroup.getSelectedToggle();

            RadioButton selectedRadioButton = (RadioButton) selectedToggle;
            String selectedText = selectedRadioButton.getText();
            if (selectedText.equals("Create a Course")) {
            } else if (selectedText.equals("Get all Courses")) {
            } else if (selectedText.equals("Update a Course")) {

            } else if (selectedText.equals("Delete a Course")) {

            }
        });


        ////////////////////////////////

        Label courseNamLabel = new Label("Course name:");
        Label subjectLabel = new Label("Subject:");
        Label introLabel = new Label("Introduction Text:");
        Label levelLabel = new Label("Level");

        HBox SubmitAndClear = new HBox();
        SubmitAndClear.getChildren().addAll(submit, clear);

        VBox SubmitClearMainView = new VBox();
        SubmitClearMainView.getChildren().addAll(SubmitAndClear, toMainView);

        SubmitAndClear.setPadding(new Insets(10, 0, 10, 0));
        SubmitAndClear.setSpacing(10);
        // Create the grid pane and add the input fields and labels to it
        GridPane inputFields = new GridPane();
        inputFields.setPadding(new Insets(10));
        inputFields.setHgap(10);
        inputFields.setVgap(10);

        inputFields.addRow(0, courseNamLabel, courseNameTextField);
        inputFields.addRow(1, subjectLabel, subjectTextField);
        inputFields.addRow(2, levelLabel, level);
        inputFields.addRow(3, introLabel, introductionTextTextArea);
        inputFields.addRow(8, SubmitClearMainView);

        vbox.getChildren().addAll(label1, crudOptions);
        borderpane.setTop(vbox);
        borderpane.setLeft(inputFields);
        borderpane.setRight(textArea);
        borderpane.setPadding(new Insets(10));

        Scene courseView = new Scene(borderpane, 1000, 600);
        stage.setScene(courseView);
        stage.show();
    }

    private void clearInputFields(TextField courseNameTextField, TextField subjectTextField, ChoiceBox<String> level,
            TextArea textArea) {
                courseNameTextField.setText(null);
                subjectTextField.setText(null);
                level.setValue(null);
                textArea.setText(null);
    }
}
