package com.revature.banking_application_p0.services;

import com.revature.banking_application_p0.daos.CustomerDao;
import com.revature.banking_application_p0.models.Customer;
import com.revature.banking_application_p0.util.ConnectionFactory;

import java.sql.Connection;


public class CustomerServices {
    private CustomerDao customerDao = new CustomerDao();

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
       validateEmailNotUsed(newCustomer.getEmail());

        Customer persistedCustomer = customerDao.create(newCustomer);

      System.out.println("Start deleting data from customer table: ");


        // TODO: 5/6/2022 - Implement the delete method so that I can delete data from a table 
/*      Connection conn = ConnectionFactory.getInstance().getConnection();
        customerDao.delete(conn, "customer", "asrat");*/

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
        if(newCustomer.getDob() == null || newCustomer.getDob().equals("")) return false;
        if(newCustomer.getSsn() == null || newCustomer.getSsn().trim().equals("")) return false;
        if(newCustomer.getUsername_u() == null || newCustomer.getUsername_u().trim().equals("")) return false;
        if(newCustomer.getPassword_p() == null || newCustomer.getPassword_p().trim().equals("")) return false;
        return newCustomer.getEmail() != null || !newCustomer.getEmail().trim().equals("");
    }
}

