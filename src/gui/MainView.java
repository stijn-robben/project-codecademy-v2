package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainView {
    private StudentView studentView;
    private OverviewView overviewView;
    private EnrollementView enrollementView;
    private CourseView courseView;
    private CertificateView certificateView;

    public void mainView(Stage stage) {
        BorderPane borderPane = new BorderPane();

        Label label1 = new Label(
                "Choose one of the options to create, read, update or delete content in the database.");
        Button button1 = new Button("Students");
        Button button2 = new Button("Courses");
        Button button3 = new Button("Enrollments");
        Button button4 = new Button("Certificates");
        Button button5 = new Button("Overviews");

        HBox buttons = new HBox(20);
        buttons.getChildren().addAll(button1, button2, button3, button4, button5);
        borderPane.setTop(label1);
        borderPane.setCenter(buttons);

        // on actions
        button1.setOnAction(e -> studentView.studentView(stage, this));
        button2.setOnAction(e -> courseView.courseView(stage, this));
        button3.setOnAction(e -> enrollementView.enrollementView(stage, this));
        button4.setOnAction(e -> certificateView.certificateView(stage, this));
        button5.setOnAction(e -> overviewView.overviewView(stage, this));

        Scene mainView = new Scene(borderPane, 800, 300);
        stage.setScene(mainView);
        stage.show();
    }

    public void setStudentView(StudentView studentView) {
        this.studentView = studentView;
    }

    public void setOverviewView(OverviewView overviewView) {
        this.overviewView = overviewView;
    }

    public void setEnrollementView(EnrollementView enrollementView) {
        this.enrollementView = enrollementView;
    }

    public void setCourseView(CourseView courseView) {
        this.courseView = courseView;
    }

    public void setCertificateView(CertificateView certificateView) {
        this.certificateView = certificateView;
    }
}