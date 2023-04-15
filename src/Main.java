import java.sql.Connection;
import java.sql.SQLException;

import data.*;
import logic.*;
import domain.*;
import gui.*;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws ClassNotFoundException, SQLException {
        StudentView studentView = new StudentView();
        OverviewView overviewView = new OverviewView();
        EnrollmentView enrollementView = new EnrollmentView();
        CourseView courseView = new CourseView();
        CertificateView certificateView = new CertificateView();
        //DatabaseConnection connection = new DatabaseConnection();
        StudentHandler studentHandler = new StudentHandler(DatabaseConnection.getConnection());

        MainView mainView = new MainView();
        mainView.setStudentView(studentView);
        mainView.setOverviewView(overviewView);
        mainView.setEnrollmentView(enrollementView);
        mainView.setCourseView(courseView);
        mainView.setCertificateView(certificateView);
        mainView.setStudentHandler(studentHandler);
        stage.setTitle(
                "Stijn Robben (2205997), Stefan Dekkers (2198892), Bas Dekker (2195403), Jozef van Dijk (2187288)");

        mainView.mainView(stage);
    }
}
