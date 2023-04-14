package domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Student {

    private String email;
    private String name;
    private String birthDate;
    private String gender;
    private String address;
    private String city;
    private String country;
    private String zipCode;

    public Student(String email, String name, String birthDate, String gender, String address, String city,
            String country, String zipCode) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email address format.");
        }
        if (!isValidBirthDate(birthDate)) {
            throw new IllegalArgumentException("Invalid birth date.");
        }
        if (!isValidZipCode(zipCode)) {
            throw new IllegalArgumentException("Invalid zip code format.");
        }

        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$";
        return email.matches(emailRegex);
    }

    private boolean isValidBirthDate(String birthDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate parsedDate = LocalDate.parse(birthDate, formatter);
            LocalDate currentDate = LocalDate.now();

            return parsedDate.isBefore(currentDate) || parsedDate.isEqual(currentDate);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private boolean isValidZipCode(String zipCode) {
        String zipCodeRegex = "^[1-9][0-9]{3}\\s[A-Z]{2}$";
        return zipCode.matches(zipCodeRegex);
    }

    public void setEmail(String email) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email address format.");
        }

        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(String birthDate) {
        if (!isValidBirthDate(birthDate)) {
            throw new IllegalArgumentException("Invalid birth date.");
        }

        this.birthDate = birthDate;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setZipCode(String zipCode) {
        if (!isValidZipCode(zipCode)) {
            throw new IllegalArgumentException("Invalid zip code format.");
        }

        this.zipCode = zipCode;
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

    public String getZipCode() {
        return zipCode;
    }

    @Override
    public String toString() {
        return "Student [email=" + email + ", name=" + name + ", birthDate=" + birthDate + ", gender=" + gender
                + ", address=" + address + ", city=" + city + ", country=" + country + ", zipCode=" + zipCode + "]";
    }

}