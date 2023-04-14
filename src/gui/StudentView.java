package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class StudentView {
    public void studentView(Stage stage, MainView mainView) {
        BorderPane borderpane = new BorderPane();
        VBox vbox = new VBox();
        vbox.setSpacing(14);
        vbox.setPadding(new Insets(8));

        Label label1 = new Label("Students");
        label1.setFont(Font.font("Arial", 16));

        Button toMainView = new Button("Back to home");
        toMainView.setOnAction((event) -> {
            mainView.mainView(stage);
        });

        // crud options
        RadioButton radioButton1 = new RadioButton("Create a student");
        ToggleGroup toggleGroup = new ToggleGroup();
        radioButton1.setToggleGroup(toggleGroup);
        radioButton1.setSelected(true);

        RadioButton radioButton2 = new RadioButton("Get all students");
        radioButton2.setToggleGroup(toggleGroup);

        RadioButton radioButton3 = new RadioButton("Update a student");
        radioButton3.setToggleGroup(toggleGroup);

        RadioButton radioButton4 = new RadioButton("Delete a student");
        radioButton4.setToggleGroup(toggleGroup);

        HBox crudOptions = new HBox();
        crudOptions.setPadding(new Insets(10));
        crudOptions.setSpacing(20);
        crudOptions.getChildren().addAll(radioButton1, radioButton2, radioButton3, radioButton4);

        TextArea textArea = new TextArea();
        textArea.setEditable(false);

        // Create the input fields
        TextField emailField = new TextField();
        TextField nameField = new TextField();
        DatePicker birthDateField = new DatePicker();
        TextField genderField = new TextField();
        TextField addressField = new TextField();
        TextField cityField = new TextField();
        TextField countryField = new TextField();
        TextField zipCodeField = new TextField();

        Button clear = new Button("Clear fields");
        clear.setOnAction((event) -> {
            clearInputFields(emailField, nameField, birthDateField, genderField, addressField, cityField, countryField,
                    zipCodeField);
        });

        // Create the labels
        Label emailLabel = new Label("Email:");
        Label nameLabel = new Label("Name:");
        Label birthDateLabel = new Label("Birth date:");
        Label genderLabel = new Label("Gender:");
        Label addressLabel = new Label("Address:");
        Label cityLabel = new Label("City:");
        Label countryLabel = new Label("Country:");
        Label zipCodeLabel = new Label("Zipcode:");

        HBox toMainViewAndClear = new HBox();
        toMainViewAndClear.getChildren().addAll(toMainView, clear);

        // Create the grid pane and add the input fields and labels to it
        GridPane inputFields = new GridPane();
        inputFields.setPadding(new Insets(10));
        inputFields.setHgap(10);
        inputFields.setVgap(10);

        inputFields.addRow(0, emailLabel, emailField);
        inputFields.addRow(1, nameLabel, nameField);
        inputFields.addRow(2, birthDateLabel, birthDateField);
        inputFields.addRow(3, genderLabel, genderField);
        inputFields.addRow(4, addressLabel, addressField);
        inputFields.addRow(5, cityLabel, cityField);
        inputFields.addRow(6, countryLabel, countryField);
        inputFields.addRow(7, zipCodeLabel, zipCodeField);
        inputFields.addRow(8, toMainViewAndClear);

        vbox.getChildren().addAll(label1, crudOptions);
        borderpane.setTop(vbox);
        borderpane.setLeft(inputFields);
        borderpane.setRight(textArea);
        borderpane.setPadding(new Insets(10));

        Scene studentView = new Scene(borderpane, 650, 430);
        stage.setScene(studentView);
        stage.show();

    }

    public void clearInputFields(TextField emailField, TextField nameField, DatePicker birthDateField,
            TextField genderField, TextField addressField, TextField cityField, TextField countryField,
            TextField zipCodeField) {
        emailField.setText(null);
        nameField.setText(null);
        birthDateField.setValue(null);
        genderField.setText(null);
        addressField.setText(null);
        cityField.setText(null);
        countryField.setText(null);
        zipCodeField.setText(null);
    }
}