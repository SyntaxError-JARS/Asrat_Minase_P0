package com.revature.banking_application_p0.models;


import java.sql.Date;


public class Customer {

    private String firstName;
    private String lastName;
    private Date dob;
    private String ssn;
    private String username_u;
    private String password_p;
    private String email;

    public Customer(String firstName, String lastName, Date dob, String ssn, String username_u, String password_p, String email) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.ssn = ssn;
        this.username_u = username_u;
        this.password_p = password_p;
        this.email = email;
    }

    public Customer() {

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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getUsername_u() {
        return username_u;
    }

    public void setUsername_u(String username_u) {
        this.username_u = username_u;
    }

    public String getPassword_p() {
        return password_p;
    }

    public void setPassword_p(String password_p) {
        this.password_p = password_p;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firsName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob='" + dob + '\'' +
                ", ssn='" + ssn+ '\'' +
                ", username_u='" + username_u + '\'' +
                ", password_p='" + password_p + '\'' +
                ", email='" + email + '\'' +
                '}';
    }



}







