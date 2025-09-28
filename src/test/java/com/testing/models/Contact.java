package com.testing.models;

public class Contact {
    private String firstName;
    private String lastName;
    private String birthdate;
    private String email;
    private String phone;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    // Constructor
    public Contact(String firstName, String lastName, String birthdate, String email,
                   String phone, String street1, String city, String state, String postalCode, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }


    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getBirthdate() { return birthdate; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getPostalCode() { return postalCode; }
    public String getCountry() { return country; }
}