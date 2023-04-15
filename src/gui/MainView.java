package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.CourseHandler;
import logic.StudentHandler;

public class MainView {
    private StudentView studentView;
    private OverviewView overviewView;
    private EnrollmentView enrollementView;
    private CourseView courseView;
    private CertificateView certificateView;
    private StudentHandler studentHandler;
    private CourseHandler courseHandler;

    public void mainView(Stage stage) {
        VBox vbox = new VBox();
        vbox.setSpacing(14);
        vbox.setPadding(new Insets(8));

        Label label1 = new Label("Home");
        label1.setFont(Font.font("Arial", 16));
        Label label2 = new Label(
                "Choose one of the options to create, read, update or delete content in the database.");
        label2.setFont(Font.font("Arial", 13));
        Button button1 = new Button("Students");
        button1.setFont(Font.font("Arial", 13));
        Button button2 = new Button("Courses");
        button2.setFont(Font.font("Arial", 13));
        Button button3 = new Button("Enrollments");
        button3.setFont(Font.font("Arial", 13));
        Button button4 = new Button("Certificates");
        button4.setFont(Font.font("Arial", 13));
        Button button5 = new Button("Overviews");
        button5.setFont(Font.font("Arial", 13));

        HBox buttons = new HBox(20);
        buttons.getChildren().addAll(button1, button2, button3, button4, button5);

        vbox.getChildren().addAll(label1, label2, buttons);

        // Actions
        button1.setOnAction(e -> studentView.studentView(stage, this, studentHandler));
        button2.setOnAction(e -> courseView.courseView(stage, this, courseHandler));
        button3.setOnAction(e -> enrollementView.enrollmentView(stage, this));
        button4.setOnAction(e -> certificateView.certificateView(stage, this));
        button5.setOnAction(e -> overviewView.overviewView(stage, this));

        Scene mainView = new Scene(vbox, 600, 400);
        stage.setScene(mainView);
        stage.show();
    }

    public void setStudentView(StudentView studentView) {
        this.studentView = studentView;
    }

    public void setOverviewView(OverviewView overviewView) {
        this.overviewView = overviewView;
    }

    public void setEnrollmentView(EnrollmentView enrollementView) {
        this.enrollementView = enrollementView;
    }

    public void setCourseView(CourseView courseView) {
        this.courseView = courseView;
    }

    public void setCertificateView(CertificateView certificateView) {
        this.certificateView = certificateView;
    }

    public void setStudentHandler(StudentHandler studentHandler) {
        this.studentHandler = studentHandler;
    }

    public void setCourseHandler(CourseHandler courseHandler) {
        this.courseHandler = courseHandler;
    }
}