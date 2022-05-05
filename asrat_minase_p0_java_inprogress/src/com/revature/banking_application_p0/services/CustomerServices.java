package com.revature.banking_application_p0.services;

import com.revature.banking_application_p0.daos.CustomerDao;
import com.revature.banking_application_p0.models.Customer;

import java.io.IOException;

public class CustomerServices {
    private CustomerDao customerDao = new CustomerDao();

    public void readTrainers(){
        System.out.println("Begin reading Customer in our file database.");
        Customer[] customers = new Customer[0];
        try {
            customers = customerDao.findAll();
            System.out.println("All Customer have been found here are the results: \n");
            for (int i = 0; i < customers.length; i++) {
                Customer trainer = customers[i];
                System.out.println(trainer.toString());
            }
        } catch (IOException | NullPointerException e) {
            // e.printStackTrace();
        }
    }
    // TODO: Implement me to check that the email is not already in our database.
    public boolean validateEmailNotUsed(String email){
        customerDao.checkEmail(email);
        return false;
    }

    public boolean registerCustomer(Customer newCustomer){
        System.out.println("Customer trying to be registered: " + newCustomer);
        if(!validateCustomerInput(newCustomer)){ // checking if false
            System.out.println("User was not validated");
            throw new RuntimeException();
        }
        // TODO: Will implement with JDBC (connecting to our database)
        validateEmailNotUsed(newCustomer.getEmail());

        Customer persistedCustomer = customerDao.create(newCustomer);

        if(persistedCustomer == null){
            throw new RuntimeException();
        }
        System.out.println("Customers has been persisted: " + newCustomer);
        return true;
    }
    private boolean validateCustomerInput(Customer newCustomer) {
        System.out.println("Validating Customer: " + newCustomer);
        if(newCustomer == null) return false;
        if(newCustomer.getFirstName() == null || newCustomer.getFirstName().trim().equals("")) return false;
        if(newCustomer.getLastName() == null || newCustomer.getLastName().trim().equals("")) return false;
        if(newCustomer.getSsn() == null || newCustomer.getSsn().trim().equals("")) return false;
        if(newCustomer.getDob() == null || !newCustomer.getDob().equals("")) return false;
        if(newCustomer.getSsn() == null || !newCustomer.getSsn().equals("")) return false;
        if(newCustomer.getUsername_u() == null || newCustomer.getUsername_u().trim().equals("")) return false;
        if(newCustomer.getPassword_p() == null || newCustomer.getPassword_p().trim().equals("")) return false;
        return newCustomer.getEmail() == null || newCustomer.getEmail().trim().equals("");
    }
}

