package gui;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

import domain.Course;
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

        Label updateCourseNameLB = new Label("New Name For Course:");
        TextField updateCourseNameTF = new TextField();
        TextField courseNameTextField = new TextField();
        TextField subjectTextField = new TextField();
        TextArea introductionTextTextArea = new TextArea();
        ObservableList<String> optionsList = FXCollections.observableArrayList(
                "beginner",
                "gevordert",
                "expert");

        ChoiceBox<String> levelBox = new ChoiceBox<>(optionsList);

        radioButton1.selectedProperty().addListener((observable, oldValue, newValue) -> {
            updateCourseNameTF.setVisible(false);
            updateCourseNameLB.setVisible(false);
            courseNameTextField.setDisable(false);
            subjectTextField.setDisable(false);
            levelBox.setDisable(false);
            introductionTextTextArea.setDisable(false);

            clearInputFields(courseNameTextField, subjectTextField, levelBox, textArea);
        });

        radioButton2.selectedProperty().addListener((observable, oldValue, newValue) -> {
            updateCourseNameTF.setVisible(false);
            updateCourseNameLB.setVisible(false);
            courseNameTextField.setDisable(true);
            subjectTextField.setDisable(true);
            levelBox.setDisable(true);
            introductionTextTextArea.setDisable(true);
            clearInputFields(courseNameTextField, subjectTextField, levelBox, textArea);
        });

        radioButton3.selectedProperty().addListener((observable, oldValue, newValue) -> {
            updateCourseNameTF.setVisible(true);
            updateCourseNameLB.setVisible(true);
            courseNameTextField.setDisable(false);
            subjectTextField.setDisable(false);
            levelBox.setDisable(false);
            introductionTextTextArea.setDisable(false);
            clearInputFields(courseNameTextField, subjectTextField, levelBox, textArea);
        });

        radioButton4.selectedProperty().addListener((observable, oldValue, newValue) -> {
            updateCourseNameTF.setVisible(false);
            updateCourseNameLB.setVisible(false);
            courseNameTextField.setDisable(false);
            subjectTextField.setDisable(true);
            levelBox.setDisable(true);
            introductionTextTextArea.setDisable(true);
            clearInputFields(courseNameTextField, subjectTextField, levelBox, textArea);
        });

        Button clear = new Button("Clear fields");
        clear.setOnAction((event) -> {
            clearInputFields(courseNameTextField, subjectTextField, levelBox, textArea);
        });

        Button submit = new Button("Submit");
        submit.setOnAction(e -> {
            Toggle selectedToggle = toggleGroup.getSelectedToggle();

            RadioButton selectedRadioButton = (RadioButton) selectedToggle;
            String selectedText = selectedRadioButton.getText();
            if (selectedText.equals("Create a Course")) {
                try {
                    createCourse(courseNameTextField, subjectTextField, introductionTextTextArea, levelBox,
                            courseHandler, textArea);
                } catch (Exception err) {
                    err.printStackTrace();
                }
            } else if (selectedText.equals("Get all Courses")) {
                try {
                    readCourses(courseHandler, textArea);
                } catch (Exception err) {
                    err.printStackTrace();
                }
            } else if (selectedText.equals("Update a Course")) {
                try {
                    updateCourse(courseNameTextField, subjectTextField, introductionTextTextArea, levelBox,
                            courseHandler, textArea, updateCourseNameTF);
                } catch (Exception err) {
                    err.printStackTrace();
                }

            } else if (selectedText.equals("Delete a Course")) {
                try {
                    deleteCourse(courseNameTextField, courseHandler, textArea);
                } catch (Exception err) {
                    err.printStackTrace();
                }
            }
        });

        
        Label courseNamLabel = new Label("Course Name:");
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

        inputFields.addRow(1, updateCourseNameLB, updateCourseNameTF);
        inputFields.addRow(0, courseNamLabel, courseNameTextField);
        inputFields.addRow(2, subjectLabel, subjectTextField);
        inputFields.addRow(3, levelLabel, levelBox);
        inputFields.addRow(4, introLabel, introductionTextTextArea);
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
        for (Course course : courseList) {
            output += course.toString();
            output += "\n";
        }
        textArea.setText(output);
    }

    private void updateCourse(TextField courseNameTextField, TextField subjectTextField,
            TextArea introductionTextTextArea, ChoiceBox<String> levelBox, CourseHandler courseHandler,
            TextArea textArea, TextField originalCourseNamTextField) throws SQLException {
        String courseName = courseNameTextField.getText();
        String subject = subjectTextField.getText();
        String introText = introductionTextTextArea.getText();
        String levelValue = levelBox.getValue().toString();
        String originalCourseName = originalCourseNamTextField.getText();

        Course updateCourse = new Course(courseName, subject, levelValue, introText);
        courseHandler.updateCourse(updateCourse, originalCourseName);
        textArea.setText("Course is updated");
    }

    private void deleteCourse(TextField courseNameTextField, CourseHandler courseHandler, TextArea textArea) throws SQLException {
        String courseName = courseNameTextField.getText();
        courseHandler.deleteCourse(courseName);
        textArea.setText("Course has been deleted");

        
    }
}
