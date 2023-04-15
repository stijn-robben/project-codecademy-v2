package gui;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import domain.Enrollment;
import domain.Student;
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
import logic.EnrollmentHandler;
import logic.StudentHandler;

public class EnrollmentView {
    public void enrollmentView(Stage stage, MainView mainView, EnrollmentHandler enrollmentHandler) {
        BorderPane borderpane = new BorderPane();
        VBox vbox = new VBox();
        vbox.setSpacing(14);
        vbox.setPadding(new Insets(8));

        Label label1 = new Label("Enrollments");
        label1.setFont(Font.font("Arial", 16));

        Button toMainView = new Button("Back to home");
        toMainView.setOnAction((event) -> {
            mainView.mainView(stage);
        });

        RadioButton radioButton1 = new RadioButton("Create an enrollment");
        ToggleGroup toggleGroup = new ToggleGroup();
        radioButton1.setToggleGroup(toggleGroup);
        radioButton1.setSelected(true);

        RadioButton radioButton2 = new RadioButton("Get all enrollments");
        radioButton2.setToggleGroup(toggleGroup);

        RadioButton radioButton3 = new RadioButton("Update an enrollment");
        radioButton3.setToggleGroup(toggleGroup);

        RadioButton radioButton4 = new RadioButton("Delete an enrollment");
        radioButton4.setToggleGroup(toggleGroup);

        HBox crudOptions = new HBox();
        crudOptions.setPadding(new Insets(10));
        crudOptions.setSpacing(20);
        crudOptions.getChildren().addAll(radioButton1, radioButton2, radioButton3, radioButton4);

        TextArea textArea = new TextArea();
        textArea.setEditable(false);

        DatePicker registrationDate = new DatePicker();
        TextField emailField = new TextField();
        TextField courseIdField = new TextField();

        radioButton1.selectedProperty().addListener((observable, oldValue, newValue) -> {
            registrationDate.setDisable(false);
            emailField.setDisable(false);
            courseIdField.setDisable(false);
            clearInputFields(registrationDate, emailField, courseIdField, textArea);
        });

        radioButton2.selectedProperty().addListener((observable, oldValue, newValue) -> {
            registrationDate.setDisable(true);
            emailField.setDisable(true);
            courseIdField.setDisable(true);
            clearInputFields(registrationDate, emailField, courseIdField, textArea);
        });

        radioButton3.selectedProperty().addListener((observable, oldValue, newValue) -> {
            registrationDate.setDisable(false);
            emailField.setDisable(false);
            courseIdField.setDisable(false);
            clearInputFields(registrationDate, emailField, courseIdField, textArea);
        });

        radioButton4.selectedProperty().addListener((observable, oldValue, newValue) -> {
            registrationDate.setDisable(true);
            emailField.setDisable(false);
            courseIdField.setDisable(false);
            clearInputFields(registrationDate, emailField, courseIdField, textArea);
        });

        Button clear = new Button("Clear fields");
        clear.setOnAction((event) -> {
            clearInputFields(registrationDate, emailField, courseIdField, textArea);
        });

        Button submit = new Button("Submit");
        submit.setOnAction(e -> {
            Toggle selectedToggle = toggleGroup.getSelectedToggle();

            RadioButton selectedRadioButton = (RadioButton) selectedToggle;
            String selectedText = selectedRadioButton.getText();
            if (selectedText.equals("Create an enrollment")) {

                try {
                    createEnrollment(registrationDate, emailField, courseIdField, textArea, enrollmentHandler);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            } else if (selectedText.equals("Get all enrollments")) {
                try {
                    readEnrollment(enrollmentHandler, textArea);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            } else if (selectedText.equals("Update an enrollment")) {
                try {
                    updateEnrollment(registrationDate, emailField, courseIdField, textArea, enrollmentHandler);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } else if (selectedText.equals("Delete an enrollment")) {
                try {
                    deleteEnrollment(emailField, courseIdField, textArea, enrollmentHandler);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        // Create the labels
        Label dateLabel = new Label("Registration date:");
        Label emailLabel = new Label("Student email:");
        Label courseIdLabel = new Label("Course ID:");

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
        inputFields.addRow(1, courseIdLabel, courseIdField);
        inputFields.addRow(2, dateLabel, registrationDate);

        inputFields.addRow(3, SubmitClearMainView);

        vbox.getChildren().addAll(label1, crudOptions);
        borderpane.setTop(vbox);
        borderpane.setLeft(inputFields);
        borderpane.setRight(textArea);
        borderpane.setPadding(new Insets(10));

        Scene studentView = new Scene(borderpane, 650, 480);
        stage.setScene(studentView);
        stage.show();

    }

    private void clearInputFields(DatePicker registrationDate, TextField emailField, TextField courseIdField,
            TextArea textArea) {
        registrationDate.setValue(null);
        emailField.setText(null);
        courseIdField.setText(null);
        textArea.setText(null);
    }

    private void createEnrollment(DatePicker registrationDate, TextField emailField, TextField courseIdField,
            TextArea textArea, EnrollmentHandler enrollmentHandler) throws SQLException {
        Date date = Date.valueOf(registrationDate.getValue());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = formatter.format(date);
        String email = emailField.getText();
        int courseId = Integer.valueOf(courseIdField.getText());

        Enrollment enrollment = new Enrollment(dateString, email, courseId);
        enrollmentHandler.addEnrollment(enrollment);
        textArea.setText("Enrollment is now in the database!");
    }

    public void readEnrollment(EnrollmentHandler enrollmentHandler, TextArea textArea) throws SQLException {
        List<Enrollment> enrollmentList = enrollmentHandler.getEnrollments();
        String output = "";
        for (int i = 0; i < enrollmentList.size(); i++) {
            output += enrollmentList.get(i).toString();
            output += "\n";
        }
        textArea.setText(output);
    }

    public void updateEnrollment(DatePicker registrationDate, TextField emailField, TextField courseIdField,
            TextArea textArea, EnrollmentHandler enrollmentHandler) throws SQLException {
        Date date = Date.valueOf(registrationDate.getValue());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = formatter.format(date);
        String email = emailField.getText();
        int courseId = Integer.valueOf(courseIdField.getText());

        Enrollment enrollment = new Enrollment(dateString, email, courseId);
        enrollmentHandler.updateEnrollment(enrollment);
        textArea.setText("Enrollment is updated in the database!");
    }

    public void deleteEnrollment(TextField emailField, TextField courseIdField,
            TextArea textArea, EnrollmentHandler enrollmentHandler) throws SQLException {

        String email = emailField.getText();
        int courseId = Integer.valueOf(courseIdField.getText());
        enrollmentHandler.deleteEnrollment(email, courseId);
        textArea.setText("Enrollment is deleted in the database!");

    }
}
