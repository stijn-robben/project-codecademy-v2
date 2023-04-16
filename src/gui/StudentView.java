package gui;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import InputVerification.DateTools;
import InputVerification.MailTools;
import InputVerification.PostalCode;
import domain.Student;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import logic.StudentHandler;

public class StudentView {
    public void studentView(Stage stage, MainView mainView, StudentHandler studentHandler) {
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

        TextField emailField = new TextField();
        TextField nameField = new TextField();
        DatePicker birthDateField = new DatePicker();
        TextField genderField = new TextField();
        TextField addressField = new TextField();
        TextField cityField = new TextField();
        TextField countryField = new TextField();
        TextField zipCodeField = new TextField();

        radioButton1.selectedProperty().addListener((observable, oldValue, newValue) -> {
            emailField.setDisable(false);
            nameField.setDisable(false);
            birthDateField.setDisable(false);
            genderField.setDisable(false);
            addressField.setDisable(false);
            cityField.setDisable(false);
            countryField.setDisable(false);
            zipCodeField.setDisable(false);
            clearInputFields(emailField, nameField, birthDateField, genderField, addressField, cityField, countryField,
                    zipCodeField, textArea);
        });

        radioButton2.selectedProperty().addListener((observable, oldValue, newValue) -> {
            emailField.setDisable(true);
            nameField.setDisable(true);
            birthDateField.setDisable(true);
            genderField.setDisable(true);
            addressField.setDisable(true);
            cityField.setDisable(true);
            countryField.setDisable(true);
            zipCodeField.setDisable(true);
            clearInputFields(emailField, nameField, birthDateField, genderField, addressField, cityField, countryField,
                    zipCodeField, textArea);
        });

        radioButton3.selectedProperty().addListener((observable, oldValue, newValue) -> {
            emailField.setDisable(false);
            nameField.setDisable(false);
            birthDateField.setDisable(false);
            genderField.setDisable(false);
            addressField.setDisable(false);
            cityField.setDisable(false);
            countryField.setDisable(false);
            zipCodeField.setDisable(false);
            clearInputFields(emailField, nameField, birthDateField, genderField, addressField, cityField, countryField,
                    zipCodeField, textArea);
        });

        radioButton4.selectedProperty().addListener((observable, oldValue, newValue) -> {
            emailField.setDisable(false);
            nameField.setDisable(true);
            birthDateField.setDisable(true);
            genderField.setDisable(true);
            addressField.setDisable(true);
            cityField.setDisable(true);
            countryField.setDisable(true);
            zipCodeField.setDisable(true);
            clearInputFields(emailField, nameField, birthDateField, genderField, addressField, cityField, countryField,
                    zipCodeField, textArea);
        });

        Button clear = new Button("Clear fields");
        clear.setOnAction((event) -> {
            clearInputFields(emailField, nameField, birthDateField, genderField, addressField, cityField, countryField,
                    zipCodeField, textArea);
        });

        Button submit = new Button("Submit");
        submit.setOnAction(e -> {
            Toggle selectedToggle = toggleGroup.getSelectedToggle();

            RadioButton selectedRadioButton = (RadioButton) selectedToggle;
            String selectedText = selectedRadioButton.getText();
            if (selectedText.equals("Create a student")) {
                try {
                    createStudent(emailField, nameField, birthDateField, genderField, addressField, cityField,
                            countryField,
                            zipCodeField, studentHandler, textArea);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            } else if (selectedText.equals("Get all students")) {
                try {
                    readStudents(studentHandler, textArea);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            } else if (selectedText.equals("Update a student")) {
                try {
                    updateStudent(emailField, nameField, birthDateField, genderField, addressField, cityField,
                            countryField,
                            zipCodeField, studentHandler, textArea);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            } else if (selectedText.equals("Delete a student")) {
                try {
                    deleteStudent(emailField, studentHandler, textArea);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
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
        inputFields.addRow(1, nameLabel, nameField);
        inputFields.addRow(2, birthDateLabel, birthDateField);
        inputFields.addRow(3, genderLabel, genderField);
        inputFields.addRow(4, addressLabel, addressField);
        inputFields.addRow(5, cityLabel, cityField);
        inputFields.addRow(6, countryLabel, countryField);
        inputFields.addRow(7, zipCodeLabel, zipCodeField);
        inputFields.addRow(8, SubmitClearMainView);

        vbox.getChildren().addAll(label1, crudOptions);
        borderpane.setTop(vbox);
        borderpane.setLeft(inputFields);
        borderpane.setRight(textArea);
        borderpane.setPadding(new Insets(10));

        Scene studentView = new Scene(borderpane, 650, 480);
        stage.setScene(studentView);
        stage.show();

    }

    public void clearInputFields(TextField emailField, TextField nameField, DatePicker birthDateField,
            TextField genderField, TextField addressField, TextField cityField, TextField countryField,
            TextField zipCodeField, TextArea textArea) {
        emailField.setText(null);
        nameField.setText(null);
        birthDateField.setValue(null);
        genderField.setText(null);
        addressField.setText(null);
        cityField.setText(null);
        countryField.setText(null);
        zipCodeField.setText(null);
        textArea.setText(null);
    }

    public void createStudent(TextField emailField, TextField nameField, DatePicker birthDateField,
            TextField genderField, TextField addressField, TextField cityField, TextField countryField,
            TextField zipCodeField, StudentHandler studentHandler, TextArea textArea) throws SQLException {
        String email = emailField.getText();
        String name = nameField.getText();
        Date birthDate = Date.valueOf(birthDateField.getValue());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = formatter.format(birthDate);

        String gender = genderField.getText();
        String address = addressField.getText();
        String city = cityField.getText();
        String country = countryField.getText();
        String zipCode = zipCodeField.getText();

        Student student = new Student(email, name, dateString, gender, address, city, country, zipCode);
        studentHandler.addStudent(student);
        textArea.setText("Student is now in the database!");
    }

    public void readStudents(StudentHandler studentHandler, TextArea textArea) throws SQLException {
        List<Student> studentList = studentHandler.getStudents();
        String output = "";
        for (int i = 0; i < studentList.size(); i++) {
            output += studentList.get(i).toString();
            output += "\n";
        }
        textArea.setText(output);
    }

    public void updateStudent(TextField emailField, TextField nameField, DatePicker birthDateField,
            TextField genderField, TextField addressField, TextField cityField, TextField countryField,
            TextField zipCodeField, StudentHandler studentHandler, TextArea textArea) throws SQLException {

        String email = emailField.getText();
        String name = nameField.getText();
        Date birthDate = Date.valueOf(birthDateField.getValue());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = formatter.format(birthDate);

        String gender = genderField.getText();
        String address = addressField.getText();
        String city = cityField.getText();
        String country = countryField.getText();
        String zipCode = zipCodeField.getText();

        boolean dateBool = DateTools.validateDate(birthDateField.getValue().getDayOfMonth(), birthDateField.getValue().getMonthValue(), birthDateField.getValue().getYear());
        if (!dateBool) {
            Alert msg = new Alert(Alert.AlertType.WARNING);
            msg.setContentText("Geboortedatum is niet geldig!");
            msg.show();

        }
        boolean mailBool = MailTools.validateMailAddress(emailField.getText());
        if (!mailBool) {
            Alert msg = new Alert(Alert.AlertType.WARNING);
            msg.setContentText("Email is niet geldig!");
            msg.show();
        }

        try {
            PostalCode.formatPostalCode(zipCodeField.getText());
            zipCode = PostalCode.formatPostalCode(zipCodeField.getText());
        } catch (Exception e) {
            Alert msg = new Alert(Alert.AlertType.WARNING);
            msg.setContentText("Postcode is niet geldig!");
            msg.show();
        }

        Student student = new Student(email, name, dateString, gender, address, city, country, zipCode);
        studentHandler.updateStudent(student);
        textArea.setText("Student is updated!");
    }

    public void deleteStudent(TextField emailField, StudentHandler studentHandler, TextArea textArea)
            throws SQLException {
        String email = emailField.getText();
        studentHandler.deleteStudent(email);
        textArea.setText("Student is deleted!");
    }
}
