package gui;

import java.security.cert.CertStoreException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javafx.scene.control.DatePicker;
import domain.Certificate;
import domain.Enrollment;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import logic.CertificateHandler;
import logic.EnrollmentHandler;

public class CertificateView {
    public void certificateView(Stage stage, MainView mainView, CertificateHandler certificateHandler) {
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

        RadioButton radioButton1 = new RadioButton("Create a certificate");
        ToggleGroup toggleGroup = new ToggleGroup();
        radioButton1.setToggleGroup(toggleGroup);
        radioButton1.setSelected(true);

        RadioButton radioButton2 = new RadioButton("Get all certificates");
        radioButton2.setToggleGroup(toggleGroup);

        RadioButton radioButton3 = new RadioButton("Update a certificate");
        radioButton3.setToggleGroup(toggleGroup);

        RadioButton radioButton4 = new RadioButton("Delete a certificate");
        radioButton4.setToggleGroup(toggleGroup);

        HBox crudOptions = new HBox();
        crudOptions.setPadding(new Insets(10));
        crudOptions.setSpacing(20);
        crudOptions.getChildren().addAll(radioButton1, radioButton2, radioButton3, radioButton4);

        TextArea textArea = new TextArea();
        textArea.setEditable(false);

        TextField courseIdField = new TextField();
        TextField certificateIdField = new TextField();
        TextField gradeField = new TextField();
        TextField emailField = new TextField();
        TextField employeeField = new TextField();
        DatePicker registrationDate = new DatePicker();

        radioButton1.selectedProperty().addListener((observable, oldValue, newValue) -> {
            emailField.setDisable(false);
            gradeField.setDisable(false);
            employeeField.setDisable(false);
            courseIdField.setDisable(false);
            certificateIdField.setDisable(true);
            registrationDate.setDisable(false);
            clearInputFields(emailField, gradeField, employeeField, courseIdField, certificateIdField, registrationDate,
                    textArea);
        });

        radioButton2.selectedProperty().addListener((observable, oldValue, newValue) -> {
            emailField.setDisable(true);
            gradeField.setDisable(true);
            employeeField.setDisable(true);
            courseIdField.setDisable(true);
            certificateIdField.setDisable(true);
            registrationDate.setDisable(true);
            clearInputFields(emailField, gradeField, employeeField, courseIdField, certificateIdField, registrationDate,
                    textArea);
        });

        radioButton3.selectedProperty().addListener((observable, oldValue, newValue) -> {
            emailField.setDisable(false);
            gradeField.setDisable(false);
            employeeField.setDisable(false);
            courseIdField.setDisable(false);
            certificateIdField.setDisable(false);
            registrationDate.setDisable(false);
            clearInputFields(emailField, gradeField, employeeField, courseIdField, certificateIdField, registrationDate,
                    textArea);
        });

        radioButton4.selectedProperty().addListener((observable, oldValue, newValue) -> {
            emailField.setDisable(true);
            gradeField.setDisable(true);
            employeeField.setDisable(true);
            courseIdField.setDisable(true);
            certificateIdField.setDisable(false);
            registrationDate.setDisable(true);
            clearInputFields(emailField, gradeField, employeeField, courseIdField, certificateIdField, registrationDate,
                    textArea);
        });

        Button clear = new Button("Clear fields");
        clear.setOnAction((event) -> {
            clearInputFields(emailField, gradeField, employeeField, courseIdField, certificateIdField, registrationDate,
                    textArea);
        });

        Button submit = new Button("Submit");
        submit.setOnAction(e -> {
            Toggle selectedToggle = toggleGroup.getSelectedToggle();

            RadioButton selectedRadioButton = (RadioButton) selectedToggle;
            String selectedText = selectedRadioButton.getText();
            if (selectedText.equals("Create a certificate")) {
                try {
                    createCertificate(emailField, gradeField, employeeField, courseIdField,
                            registrationDate, textArea, certificateHandler);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } else if (selectedText.equals("Get all certificates")) {
                try {
                    getCertificates(textArea, certificateHandler);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } else if (selectedText.equals("Update a certificate")) {
                try {
                    updateCertificate(emailField, gradeField, employeeField, courseIdField, certificateIdField,
                            registrationDate, textArea, certificateHandler);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } else if (selectedText.equals("Delete a certificate")) {
                try {
                    deleteCertificate(certificateIdField, textArea, certificateHandler);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        Label emailLabel = new Label("Email:");
        Label gradeLabel = new Label("Grade:");
        Label employeeLabel = new Label("Name employee:");
        Label courseIdLabel = new Label("Course id: ");
        Label certificateIdLabel = new Label("Certificate id:");
        Label registrationDateLabel = new Label("Registration date:");

        HBox SubmitAndClear = new HBox();
        SubmitAndClear.getChildren().addAll(submit, clear);

        VBox SubmitClearMainView = new VBox();
        SubmitClearMainView.getChildren().addAll(SubmitAndClear, toMainView);

        SubmitAndClear.setPadding(new Insets(10, 0, 10, 0));
        SubmitAndClear.setSpacing(10);
        GridPane inputFields = new GridPane();
        inputFields.setPadding(new Insets(10));
        inputFields.setHgap(10);
        inputFields.setVgap(10);

        inputFields.addRow(0, certificateIdLabel, certificateIdField);
        inputFields.addRow(1, courseIdLabel, courseIdField);
        inputFields.addRow(2, emailLabel, emailField);
        inputFields.addRow(3, gradeLabel, gradeField);
        inputFields.addRow(4, employeeLabel, employeeField);
        inputFields.addRow(5, registrationDateLabel, registrationDate);
        inputFields.addRow(6, SubmitClearMainView);
        vbox.getChildren().addAll(label1, crudOptions);
        borderpane.setTop(vbox);
        borderpane.setLeft(inputFields);
        borderpane.setRight(textArea);
        borderpane.setPadding(new Insets(10));

        Scene certificateView = new Scene(borderpane, 600, 400);
        stage.setScene(certificateView);
        stage.show();
    }

    private void clearInputFields(TextField emailField, TextField gradeField, TextField employeeField,
            TextField courseIdField, TextField certificateIdField, DatePicker registrationDate, TextArea textArea) {
        emailField.setText(null);
        gradeField.setText(null);
        employeeField.setText(null);
        textArea.setText(null);
        courseIdField.setText(null);
        certificateIdField.setText(null);
        registrationDate.setValue(null);
    }

    public void createCertificate(TextField emailField, TextField gradeField, TextField employeeField,
            TextField courseIdField, DatePicker registrationDate, TextArea textArea,
            CertificateHandler certificateHandler) throws SQLException {
        Date date = Date.valueOf(registrationDate.getValue());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = formatter.format(date);
        String email = emailField.getText();
        int courseId = Integer.valueOf(courseIdField.getText());
        String employee = employeeField.getText();
        String grade = gradeField.getText();

        Certificate certificate = new Certificate(courseId, grade, employee,
                email, dateString);
        certificateHandler.addCertificate(certificate);
        textArea.setText("Certificate is now in the database!");
    }

    public void getCertificates(TextArea textArea, CertificateHandler certificateHandler) throws SQLException {
        List<Certificate> enrollmentList = certificateHandler.getCertificates();
        String output = "";
        for (int i = 0; i < enrollmentList.size(); i++) {
            output += enrollmentList.get(i).toString();
            output += "\n";
        }
        textArea.setText(output);
    }

    public void updateCertificate(TextField emailField, TextField gradeField, TextField employeeField,
            TextField courseIdField, TextField certificateIdField, DatePicker registrationDate, TextArea textArea,
            CertificateHandler certificateHandler) throws SQLException {
        Date date = Date.valueOf(registrationDate.getValue());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = formatter.format(date);
        String email = emailField.getText();
        int courseId = Integer.valueOf(courseIdField.getText());
        int certificateID = Integer.valueOf(certificateIdField.getText());
        String employee = employeeField.getText();
        String grade = gradeField.getText();

        Certificate certificate = new Certificate(courseId, grade, employee,
                email, dateString);
        certificateHandler.updateCertificate(certificate, certificateID);
        textArea.setText("Certificate is updated!");
    }

    public void deleteCertificate(TextField certificateIdField, TextArea textArea,
            CertificateHandler certificateHandler) throws SQLException {
        int certificateID = Integer.parseInt(certificateIdField.getText());
        certificateHandler.deleteCertificate(certificateID);
        textArea.setText("Certificate deleted!");
    }
}
