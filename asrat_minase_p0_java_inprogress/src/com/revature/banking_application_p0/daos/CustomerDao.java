package com.revature.banking_application_p0.daos;

import com.revature.banking_application_p0.models.Customer;
import com.revature.banking_application_p0.util.ConnectionFactory;

import java.io.*;
import java.sql.*;

public class CustomerDao implements Genericable<Customer>{

    @Override
    public Customer create(Customer newCustomer) {
        System.out.println("Here is the newCustomer as it enters the DAO layer: "+ newCustomer);

        try(Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "insert into customer (first_name, last_name, dob, username_u, password, email) values (?, ?, ?, ?, ?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            System.out.println(newCustomer.getFirstName());
            System.out.println(newCustomer.getLastName());

            ps.setString(1, newCustomer.getFirstName());
            ps.setString(2, newCustomer.getLastName());
            ps.setString(3, newCustomer.getDob());
            ps.setString(4, newCustomer.getSsn());
            ps.setString(5, newCustomer.getUsername_u());
            ps.setString(6, newCustomer.getPassword_p());
            ps.setString(7, newCustomer.getEmail());



            int checkInsert = ps.executeUpdate();

            if(checkInsert == 0){
                throw new RuntimeException();
            }

        } catch (SQLException | RuntimeException e){
            e.printStackTrace();
            return null;
        }
        return newCustomer;
    }

    @Override
    public Customer[] findAll() throws IOException {

        Customer[] customers = new Customer[10];
        int index = 0;

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "select * from customer";
            Statement s = conn.createStatement();

            // conn.createStatement().executeQuery("select * from trainer"); fine but generally not used
            ResultSet rs =s.executeQuery(sql);

            while (rs.next()) { // the last line of the file is null
                Customer customer = new Customer();

                customer.setFirstName(rs.getString("firstName")); // this column lable MUST MATCH THE DB
                customer.setLastName(rs.getString("lastName"));
                customer.setDob(rs.getString("dob"));
                customer.setSsn(rs.getString("snn"));
                customer.setUsername_u(rs.getString("username_u"));
                customer.setPassword_p(rs.getString("password_p"));
                customer.setEmail(rs.getString("email"));


                System.out.println("Inserted customer into index" + index);
                customers[index] = customer;
                index++;
                System.out.println("Going to the next line for our following index.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        System.out.println("Returning customer information to user.");
        return customers;
    }

    @Override
    public Customer findById(String id) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection();){

            String sql = "select * from customer where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(id)); // Wrapper class example

            ResultSet rs = ps.executeQuery(); // remember dql, bc selects are the keywords

            Customer customers = new Customer();

            customers.setFirstName(rs.getString("firstName"));
            customers.setLastName(rs.getString("lastName"));
            customers.setDob(rs.getString("dob"));
            customers.setUsername_u(rs.getString("password"));
            customers.setPassword_p(rs.getString("password"));
            customers.setEmail(rs.getString("email"));

            return customers;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public boolean update(Customer updatedObj) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    public void checkEmail(String email) {
        String sql = "select email from customer where email = " + email;
    }
}

