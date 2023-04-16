package gui;

import java.sql.SQLException;
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
import logic.OverviewHandler;

public class OverviewView {
    public void overviewView(Stage stage, MainView mainView, OverviewHandler overviewHandler) {
        BorderPane borderpane = new BorderPane();
        VBox vbox = new VBox();
        vbox.setSpacing(14);
        vbox.setPadding(new Insets(8));

        Label label1 = new Label("Overviews");
        label1.setFont(Font.font("Arial", 16));

        Button toMainView = new Button("Back to home");
        toMainView.setOnAction((event) -> {
            mainView.mainView(stage);
        });

        RadioButton radioButton1 = new RadioButton("Percentage behaalde certificaten per geslacht");
        ToggleGroup toggleGroup = new ToggleGroup();
        radioButton1.setToggleGroup(toggleGroup);
        radioButton1.setSelected(true);

        RadioButton radioButton2 = new RadioButton("Gemiddelde voortgang per module van alle accounts");
        radioButton2.setToggleGroup(toggleGroup);

        RadioButton radioButton3 = new RadioButton("Voortgang per module voor een geselecteerd account en cursus");
        radioButton3.setToggleGroup(toggleGroup);

        RadioButton radioButton4 = new RadioButton("Behaalde certificaten voor een geselecteerd account");
        radioButton4.setToggleGroup(toggleGroup);

        RadioButton radioButton5 = new RadioButton("Top 3 meest bekeken webcasts");
        radioButton5.setToggleGroup(toggleGroup);

        RadioButton radioButton6 = new RadioButton("Top 3 cursussen met meeste uitgegeven certificaten");
        radioButton6.setToggleGroup(toggleGroup);

        RadioButton radioButton7 = new RadioButton("Aanbevolen cursussen voor een geselecteerde cursus");
        radioButton7.setToggleGroup(toggleGroup);

        RadioButton radioButton8 = new RadioButton(
                "Aantal cursisten dat een geselecteerde cursus in zijn geheel heeft behaald");
        radioButton8.setToggleGroup(toggleGroup);

        VBox crudOptions = new VBox();
        crudOptions.setPadding(new Insets(10));
        crudOptions.setSpacing(20);
        crudOptions.getChildren().addAll(radioButton1, radioButton2, radioButton3, radioButton4, radioButton5,
                radioButton6, radioButton7, radioButton8);

        TextArea textArea = new TextArea();
        textArea.setEditable(false);

        TextField genderField = new TextField();
        TextField courseIdField = new TextField();
        TextField emailField = new TextField();

        emailField.setDisable(true);
        courseIdField.setDisable(true);
        genderField.setDisable(false);

        radioButton1.selectedProperty().addListener((observable, oldValue, newValue) -> {
            emailField.setDisable(true);
            courseIdField.setDisable(true);
            genderField.setDisable(false);
            clearInputFields(genderField, emailField, courseIdField, textArea);
        });

        radioButton2.selectedProperty().addListener((observable, oldValue, newValue) -> {
            emailField.setDisable(true);
            courseIdField.setDisable(false);
            genderField.setDisable(true);
            clearInputFields(genderField, emailField, courseIdField, textArea);
        });

        radioButton3.selectedProperty().addListener((observable, oldValue, newValue) -> {
            emailField.setDisable(false);
            courseIdField.setDisable(false);
            genderField.setDisable(true);
            clearInputFields(genderField, emailField, courseIdField, textArea);
        });

        radioButton4.selectedProperty().addListener((observable, oldValue, newValue) -> {
            emailField.setDisable(false);
            courseIdField.setDisable(true);
            genderField.setDisable(true);
            clearInputFields(genderField, emailField, courseIdField, textArea);
        });

        radioButton5.selectedProperty().addListener((observable, oldValue, newValue) -> {
            emailField.setDisable(true);
            courseIdField.setDisable(true);
            genderField.setDisable(true);
            clearInputFields(genderField, emailField, courseIdField, textArea);
        });

        radioButton6.selectedProperty().addListener((observable, oldValue, newValue) -> {
            emailField.setDisable(true);
            courseIdField.setDisable(true);
            genderField.setDisable(true);
            clearInputFields(genderField, emailField, courseIdField, textArea);
        });

        radioButton7.selectedProperty().addListener((observable, oldValue, newValue) -> {
            emailField.setDisable(true);
            courseIdField.setDisable(false);
            genderField.setDisable(true);
            clearInputFields(genderField, emailField, courseIdField, textArea);
        });

        radioButton8.selectedProperty().addListener((observable, oldValue, newValue) -> {
            emailField.setDisable(true);
            courseIdField.setDisable(false);
            genderField.setDisable(true);
            clearInputFields(genderField, emailField, courseIdField, textArea);
        });

        Button clear = new Button("Clear fields");
        clear.setOnAction((event) -> {
            clearInputFields(genderField, emailField, courseIdField, textArea);
        });

        Button submit = new Button("Submit");
        submit.setOnAction(e -> {
            Toggle selectedToggle = toggleGroup.getSelectedToggle();

            RadioButton selectedRadioButton = (RadioButton) selectedToggle;
            String selectedText = selectedRadioButton.getText();
            if (selectedText.equals("Percentage behaalde certificaten per geslacht")) {
                try {
                    overview1(genderField,
                            textArea, overviewHandler);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            } else if (selectedText.equals("Gemiddelde voortgang per module van alle accounts")) {
                overview2(courseIdField, textArea, overviewHandler);
            } else if (selectedText.equals("Behaalde certificaten voor een geselecteerd account")) {
                overview4(emailField, textArea, overviewHandler);
            } else if (selectedText.equals("Voortgang per module voor een geselecteerd account en cursus")) {
                overview3(emailField, courseIdField, textArea, overviewHandler);
            } else if (selectedText.equals("Top 3 meest bekeken webcasts")) {
                overview5(textArea, overviewHandler);
            } else if (selectedText.equals("Top 3 cursussen met meeste uitgegeven certificaten")) {
                overview6(textArea, overviewHandler);
            } else if (selectedText
                    .equals("Aantal cursisten dat een geselecteerde cursus in zijn geheel heeft behaald")) {
                overview8(courseIdField, textArea, overviewHandler);
            } else if (selectedText.equals("Aanbevolen cursussen voor een geselecteerde cursus")) {
                overview7(courseIdField, textArea, overviewHandler);
            }

        });
        // Create the labels
        Label emailLabel = new Label("Student email:");
        Label courseIdLabel = new Label("Course ID:");
        Label genderLabel = new Label("Gender:");

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
        inputFields.addRow(0, crudOptions);
        inputFields.addRow(1, emailLabel, emailField);
        inputFields.addRow(2, courseIdLabel, courseIdField);
        inputFields.addRow(3, genderLabel, genderField);

        inputFields.addRow(4, SubmitClearMainView);

        vbox.getChildren().addAll(label1);
        borderpane.setTop(vbox);
        borderpane.setLeft(inputFields);
        borderpane.setRight(textArea);
        borderpane.setPadding(new Insets(10));

        Scene studentView = new Scene(borderpane, 1000, 560);
        stage.setScene(studentView);
        stage.show();

    }

    private void clearInputFields(TextField genderField, TextField emailField, TextField courseIdField,
            TextArea textArea) {
        genderField.setText(null);
        emailField.setText(null);
        courseIdField.setText(null);
        textArea.setText(null);
    }

    private void overview1(TextField genderField,
            TextArea textArea, OverviewHandler overviewHandler) throws SQLException {
        String gender = genderField.getText();
        String result = overviewHandler.overview1(gender);
        textArea.setText(result);
    }

    public void overview2(TextField courseField, TextArea textArea, OverviewHandler overviewHandler) {
        int courseId = Integer.parseInt(courseField.getText());
        textArea.setText(overviewHandler.overview2(courseId));
    }

    public void overview4(TextField emailField, TextArea textArea, OverviewHandler overviewHandler) {
        textArea.setText(overviewHandler.overview4(emailField.getText()));
    }

    public void overview3(TextField emailField, TextField courseField, TextArea textArea,
            OverviewHandler overviewHandler) {
        textArea.setText(overviewHandler.overview3(emailField.getText(), Integer.parseInt(courseField.getText())));
    }

    public void overview5(TextArea textArea, OverviewHandler overviewHandler) {
        textArea.setText(overviewHandler.overview5());
    }

    public void overview6(TextArea textArea, OverviewHandler overviewHandler) {
        textArea.setText(overviewHandler.overview6());
    }

    public void overview8(TextField courseIdField, TextArea textArea, OverviewHandler overviewHandler) {
        textArea.setText(overviewHandler.overview8(Integer.parseInt(courseIdField.getText())));
    }

    public void overview7(TextField courseIdField, TextArea textArea, OverviewHandler overviewHandler) {
        textArea.setText(overviewHandler.overview7(Integer.parseInt(courseIdField.getText())));
    }
}
