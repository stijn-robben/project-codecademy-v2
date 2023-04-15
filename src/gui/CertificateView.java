package gui;

import java.sql.SQLException;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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

public class CertificateView {
    public void certificateView(Stage stage, MainView mainView) {
        BorderPane borderpane = new BorderPane();
        VBox vbox = new VBox();
        vbox.setSpacing(14);
        vbox.setPadding(new Insets(8));

        Label label1 = new Label("Certificates");
        label1.setFont(Font.font("Arial", 16));

        Button toMainView = new Button("Back to home");
        toMainView.setOnAction((event) -> {
            mainView.mainView(stage);
        });

        RadioButton radioButton1 = new RadioButton("Create a Certificate");
        ToggleGroup toggleGroup = new ToggleGroup();
        radioButton1.setToggleGroup(toggleGroup);
        radioButton1.setSelected(true);

        RadioButton radioButton2 = new RadioButton("Get all Certificates");
        radioButton2.setToggleGroup(toggleGroup);

        RadioButton radioButton3 = new RadioButton("Update a Certificate");
        radioButton3.setToggleGroup(toggleGroup);

        RadioButton radioButton4 = new RadioButton("Delete a Certificate");
        radioButton4.setToggleGroup(toggleGroup);

        HBox crudOptions = new HBox();
        crudOptions.setPadding(new Insets(10));
        crudOptions.setSpacing(20);
        crudOptions.getChildren().addAll(radioButton1, radioButton2, radioButton3, radioButton4);

        TextArea textArea = new TextArea();
        textArea.setEditable(false);

        TextField emailField = new TextField();
        TextField gradeField = new TextField();
        TextField employeeField = new TextField();

        radioButton1.selectedProperty().addListener((observable, oldValue, newValue) -> {
            emailField.setDisable(false);
            gradeField.setDisable(false);
            employeeField.setDisable(false);
            clearInputFields(emailField, gradeField, employeeField, textArea);
        });

        radioButton2.selectedProperty().addListener((observable, oldValue, newValue) -> {
            emailField.setDisable(false);
            gradeField.setDisable(false);
            employeeField.setDisable(false);
            clearInputFields(emailField, gradeField, employeeField, textArea);
        });

        radioButton3.selectedProperty().addListener((observable, oldValue, newValue) -> {
            emailField.setDisable(false);
            gradeField.setDisable(false);
            employeeField.setDisable(false);
            clearInputFields(emailField, gradeField, employeeField, textArea);
        });

        radioButton4.selectedProperty().addListener((observable, oldValue, newValue) -> {
            emailField.setDisable(false);
            gradeField.setDisable(false);
            employeeField.setDisable(false);
            clearInputFields(emailField, gradeField, employeeField, textArea);
        });

        Button clear = new Button("Clear fields");
        clear.setOnAction((event) -> {
            clearInputFields(emailField, gradeField, employeeField, textArea);
        });

        Button submit = new Button("Submit");
        submit.setOnAction(e -> {
            Toggle selectedToggle = toggleGroup.getSelectedToggle();

            RadioButton selectedRadioButton = (RadioButton) selectedToggle;
            String selectedText = selectedRadioButton.getText();
            if (selectedText.equals("Create a Certificate")) {
            } else if (selectedText.equals("Get all Certificates")) {
            } else if (selectedText.equals("Update a Certificate")) {

            } else if (selectedText.equals("Delete a Certificate")) {

            }
        });

        // Create the labels
        Label emailLabel = new Label("Email:");
        Label gradLabel = new Label("Grade:");
        Label employeeLabel = new Label("Name employee:");

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

        inputFields.addRow(0, emailLabel, emailField);
        inputFields.addRow(1, gradLabel, gradeField);
        inputFields.addRow(2, employeeLabel, employeeField);
        inputFields.addRow(8, SubmitClearMainView);




        vbox.getChildren().addAll(label1, crudOptions);
        borderpane.setTop(vbox);
        borderpane.setLeft(inputFields);
        borderpane.setRight(textArea);
        borderpane.setPadding(new Insets(10));

        Scene certificateView = new Scene(borderpane, 600, 400);
        stage.setScene(certificateView);
        stage.show();
    }

    private void clearInputFields(TextField emailField, TextField gradeField, TextField employeeField, TextArea textArea) {
        emailField.setText(null);
        gradeField.setText(null);
        employeeField.setText(null);
        textArea.setText(null);
    }

}
