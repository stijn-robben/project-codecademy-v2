package domain;

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
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(String birthDate) {
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