package com.revature.banking_application_p0.options;

import com.revature.banking_application_p0.models.Customer;
import com.revature.banking_application_p0.services.CustomerServices;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class RegisterMenu extends Menu{

    private CustomerServices CustomerServices = new CustomerServices();

    public RegisterMenu(BufferedReader terminalReader) {
        super("Register", "/register", terminalReader);
    }

    @Override
    public void render() throws Exception {
        System.out.println("What is your first name?");
        String firstName = terminalReader.readLine();

        System.out.println("What is your last name?");
        String lastName = terminalReader.readLine();

        System.out.println("Please enter your date of birth (mm/dd/yyyy):");
        String dob= terminalReader.readLine();

        System.out.println("What is your SSN?");
        String ssn = terminalReader.readLine();

        System.out.println("What is your username?");
        String username_u = terminalReader.readLine();

        System.out.println("What is your password?");
        String password_p = terminalReader.readLine();

        System.out.println("Re-enter password");
        String passwordCheck = terminalReader.readLine();

        System.out.println("What is your email?");
        String email = terminalReader.readLine();


        if (!password_p.equals(passwordCheck)) {
            System.out.println("Passwords don't match");
            return;
        }

        /*Class SimpleDateFormat. SimpleDateFormat is a concrete class for formatting and parsing
        dates in a locale-sensitive manner. It allows for formatting (date → text), parsing (text → date),
        and normalization. SimpleDateFormat allows you to start by choosing any user-defined patterns for
        date-time formatting.*/

        SimpleDateFormat dobformater = new SimpleDateFormat("dd-mm-yyy");
        java.util.Date dobDate = dobformater.parse(dob);
        long ms = dobDate.getTime();
        java.sql.Date sqldob = new java.sql.Date(ms);

        Customer newCustomer = new Customer(firstName, lastName,sqldob, ssn,username_u, password_p, email);
        System.out.println("Here is the Customer that was provided by the user: " + newCustomer);
        CustomerServices.registerCustomer(newCustomer);

    }
}

