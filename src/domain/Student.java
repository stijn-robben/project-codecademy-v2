package Domain;

import java.util.List;

public class Student {

    private String email;
    private String name;
    private String birthDate;
    private String gender;
    private String address;
    private String city;
    private String country;
    private List<Course> coursesEnrolled;

    public Student(String email, String name, String birthDate, String gender, String address, String city,
            String country, List<Course> coursesEnrolled) {
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.country = country;
        this.coursesEnrolled = coursesEnrolled;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public List<Course> getCoursesEnrolled() {
        return coursesEnrolled;
    }

}