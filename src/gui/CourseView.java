package gui;

import java.sql.SQLException;
import java.util.List;

import domain.Course;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
    public void courseView(Stage stage, MainView mainView, CourseHandler courseHandler) {
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

        RadioButton radioButton1 = new RadioButton("Create a course");
        ToggleGroup toggleGroup = new ToggleGroup();
        radioButton1.setToggleGroup(toggleGroup);
        radioButton1.setSelected(true);

        RadioButton radioButton2 = new RadioButton("Get all courses");
        radioButton2.setToggleGroup(toggleGroup);

        RadioButton radioButton3 = new RadioButton("Update a course");
        radioButton3.setToggleGroup(toggleGroup);

        RadioButton radioButton4 = new RadioButton("Delete a course");
        radioButton4.setToggleGroup(toggleGroup);

        HBox crudOptions = new HBox();
        crudOptions.setPadding(new Insets(10));
        crudOptions.setSpacing(20);
        crudOptions.getChildren().addAll(radioButton1, radioButton2, radioButton3, radioButton4);

        TextArea textArea = new TextArea();
        textArea.setEditable(false);

        TextField courseIdField = new TextField();
        TextField courseNameField = new TextField();
        TextField subjectField = new TextField();
        TextArea introTextField = new TextArea();
        ObservableList<String> levelList = FXCollections.observableArrayList(
                "Beginner",
                "Gevorderd",
                "Expert");

        ChoiceBox<String> levelBox = new ChoiceBox<>(levelList);

        radioButton1.selectedProperty().addListener((observable, oldValue, newValue) -> {
            courseIdField.setDisable(true);
            courseNameField.setDisable(false);
            subjectField.setDisable(false);
            introTextField.setDisable(false);
            levelBox.setDisable(false);

            clearInputFields(courseIdField, courseNameField, subjectField, introTextField, levelBox, textArea);
        });

        radioButton2.selectedProperty().addListener((observable, oldValue, newValue) -> {
            courseIdField.setDisable(true);
            courseNameField.setDisable(true);
            subjectField.setDisable(true);
            introTextField.setDisable(true);
            levelBox.setDisable(true);
            clearInputFields(courseIdField, courseNameField, subjectField, introTextField, levelBox, textArea);
        });

        radioButton3.selectedProperty().addListener((observable, oldValue, newValue) -> {
            courseIdField.setDisable(false);
            courseNameField.setDisable(false);
            subjectField.setDisable(false);
            introTextField.setDisable(false);
            levelBox.setDisable(false);
            clearInputFields(courseIdField, courseNameField, subjectField, introTextField, levelBox, textArea);
        });

        radioButton4.selectedProperty().addListener((observable, oldValue, newValue) -> {
            courseIdField.setDisable(false);
            courseNameField.setDisable(true);
            subjectField.setDisable(true);
            introTextField.setDisable(true);
            levelBox.setDisable(true);
            clearInputFields(courseIdField, courseNameField, subjectField, introTextField, levelBox, textArea);
        });

        Button clear = new Button("Clear fields");
        clear.setOnAction((event) -> {
            clearInputFields(courseIdField, courseNameField, subjectField, introTextField, levelBox, textArea);
        });

        Button submit = new Button("Submit");
        submit.setOnAction(e -> {
            Toggle selectedToggle = toggleGroup.getSelectedToggle();

            RadioButton selectedRadioButton = (RadioButton) selectedToggle;
            String selectedText = selectedRadioButton.getText();
            if (selectedText.equals("Create a course")) {
                try {
                    createCourse(courseNameField, subjectField, textArea, levelBox, courseHandler, textArea);
                } catch (Exception err) {
                    err.printStackTrace();
                }
            } else if (selectedText.equals("Get all courses")) {
                try {
                    readCourses(courseHandler, textArea);
                } catch (Exception err) {
                    err.printStackTrace();
                }
            } else if (selectedText.equals("Update a course")) {
                try {
                    updateCourse(courseIdField, courseNameField, subjectField,
                            introTextField, levelBox, courseHandler,
                            textArea);
                } catch (Exception err) {
                    err.printStackTrace();
                }

            } else if (selectedText.equals("Delete a course")) {
                try {
                    deleteCourse(courseNameField, courseHandler, textArea);
                } catch (Exception err) {
                    err.printStackTrace();
                }
            }
        });

        Label courseNameLabel = new Label("Course Name:");
        Label subjectLabel = new Label("Subject:");
        Label introTextLabel = new Label("Introduction Text:");
        Label levelLabel = new Label("Level");
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

        inputFields.addRow(1, courseIdLabel, courseIdField);
        inputFields.addRow(2, courseNameLabel, courseNameField);
        inputFields.addRow(3, subjectLabel, subjectField);
        inputFields.addRow(4, levelLabel, levelBox);
        inputFields.addRow(5, introTextLabel, introTextField);
        inputFields.addRow(6, SubmitClearMainView);

        vbox.getChildren().addAll(label1, crudOptions);
        borderpane.setTop(vbox);
        borderpane.setLeft(inputFields);
        borderpane.setRight(textArea);
        borderpane.setPadding(new Insets(10));

        Scene courseView = new Scene(borderpane, 1000, 600);
        stage.setScene(courseView);
        stage.show();
    }

    private void clearInputFields(TextField courseIdField, TextField courseNameField, TextField subjectField,
            TextArea introTextField, ChoiceBox<String> levelBox, TextArea textArea) {

        courseNameField.setText(null);
        subjectField.setText(null);
        introTextField.setText(null);
        levelBox.setValue(null);
        textArea.setText(null);
        courseIdField.setText(null);
    }

    private void createCourse(TextField courseNameTextField, TextField subjectTextField,
            TextArea introductionTextTextArea, ChoiceBox levelBox, CourseHandler courseHandler, TextArea textArea)
            throws SQLException {
        String courseName = courseNameTextField.getText();
        String subject = subjectTextField.getText();
        String introText = introductionTextTextArea.getText();
        String levelValue = levelBox.getValue().toString();

        Course course = new Course(courseName, subject, levelValue, introText);
        courseHandler.addCourse(course);
        textArea.setText("Course is now in the database!");
    }

    private void readCourses(CourseHandler courseHandler, TextArea textArea) throws SQLException {
        List<Course> courseList = courseHandler.getCourses();
        String output = "";
        for (int i = 0; i < courseList.size(); i++) {
            output += courseList.get(i).toString();
            output += "\n";
        }
        textArea.setText(output);
    }

    private void updateCourse(TextField courseIdField, TextField courseNameField, TextField subjectField,
            TextArea introField, ChoiceBox<String> levelBox, CourseHandler courseHandler,
            TextArea textArea) throws SQLException {
        String courseName = courseNameField.getText();
        String subject = subjectField.getText();
        String introText = introField.getText();
        String levelValue = levelBox.getValue().toString();
        int courseId = Integer.parseInt(courseIdField.getText());
        Course updateCourse = new Course(courseName, subject, levelValue, introText);
        courseHandler.updateCourse(updateCourse, courseId);
        textArea.setText("Course is updated");
    }

    private void deleteCourse(TextField courseIdField, CourseHandler courseHandler, TextArea textArea)
            throws SQLException {
        System.out.println(courseIdField.getText());
        int courseId = 14;
        courseHandler.deleteCourse(courseId);
        textArea.setText("Course has been deleted");
    }
}