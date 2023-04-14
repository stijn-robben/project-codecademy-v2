import data.*;
import logic.*;
import domain.*;
import gui.*;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        // Student s1 = new Student("test@test.nl", "Stefan Dekkers", "01/01/2000",
        //  "Male", "Lovensdijkstraat", "Breda", "Netherlands", "4818 AJ");
        // System.out.println(s1);
        
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Create view instances
        StudentView studentView = new StudentView();
        OverviewView overviewView = new OverviewView();
        EnrollmentView enrollementView = new EnrollmentView();
        CourseView courseView = new CourseView();
        CertificateView certificateView = new CertificateView();

        // Initialize the MainView with the views
        MainView mainView = new MainView();
        mainView.setStudentView(studentView);
        mainView.setOverviewView(overviewView);
        mainView.setEnrollmentView(enrollementView);
        mainView.setCourseView(courseView);
        mainView.setCertificateView(certificateView);

        stage.setTitle("Stijn Robben (2205997), Stefan Dekkers (2198892), Bas Dekker (2195403), Jozef van Dijk (2187288)");

        mainView.mainView(stage);
    }
}
