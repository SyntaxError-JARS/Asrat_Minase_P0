package com.revature.banking_application_p0.options;



import com.revature.banking_application_p0.models.Customer;
import com.revature.banking_application_p0.services.CustomerServices;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Scanner;

public class RegisterMenu extends Menu{

    private CustomerServices CustomerServices = new CustomerServices();

    public RegisterMenu(BufferedReader terminalReader) {
        super("Register", "/register", terminalReader);
    }
    Scanner scanner = new Scanner(System.in);
    @Override
    public void render() throws Exception {
        System.out.println("What is your first name?");
        String firstName = terminalReader.readLine();

        System.out.println("What is your last name?");
        String lastName = terminalReader.readLine();

        System.out.println("What is your DOB?");
        String dob = terminalReader.readLine();

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
        Customer newCustomer = new Customer(firstName, lastName,dob, ssn,username_u, password_p, email);
        System.out.println("Here is the Customer that was provided by the user: " + newCustomer);
        CustomerServices.registerCustomer(newCustomer);
    }
}

