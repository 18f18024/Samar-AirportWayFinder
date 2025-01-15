package com.example.samar_airportwayfinder;

public class UserProfile {
    private String firstName;
    private String lastName;
    private String userAddress;
    private String userAge;
    private String userGender;
    private String userPhone;

    public UserProfile() {
    }

    public UserProfile(String firstName, String lastName, String userAddress, String userAge, String userGender, String userPhone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userAddress = userAddress;
        this.userAge = userAge;
        this.userGender = userGender;
        this.userPhone = userPhone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
