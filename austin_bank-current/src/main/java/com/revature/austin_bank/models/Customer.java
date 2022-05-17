package com.revature.austin_bank.models;


public class Customer {

    private String firstName;
    private String lastName;

    private String dob; // Think of changing it.

    private String ssn;
    private String username_u;

    private String password_p;
    private String email;

    public Customer(String firstName, String lastName, String dob, String ssn, String username_u, String password_p, String email) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.ssn = ssn;
        this.username_u = username_u;
        this.password_p = password_p;
        this.email = email;
    }
    public Customer(String password){
        this.password_p = password;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
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
    public String toFileString() {
        // StringBuilder, there is also a StringBuffer (it's thread-safe)
        // Is another class for Strings that allows them to be mutated
        StringBuilder mutableString = new StringBuilder();
        mutableString
                .append(firstName).append(",")
                .append(lastName).append(",")
                .append(dob)
                .append(ssn).append(",")
                .append(username_u).append(",")
                .append(password_p).append(",")
                .append(email).append(",");
        // Without changing the mutableString class from StringBuilder we wont' have an appropriate return type
        return mutableString.toString(); // We need the toString to return it to it's appropriate type
    }
    @Override
    public String toString() {
        return "Customer{" +
                "firsName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob='" + dob + '\'' +
                ", ssn='" + ssn+ '\'' +
                ", username_u='" + username_u + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}







