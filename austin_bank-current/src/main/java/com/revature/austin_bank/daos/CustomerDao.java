package com.revature.austin_bank.daos;

import com.revature.austin_bank.exceptions.ResourcePersistanceException;
import com.revature.austin_bank.models.Customer;
import com.revature.austin_bank.util.ConnectionFactory;
import com.revature.austin_bank.util.logging.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CustomerDao implements Genericable<Customer>{

    private Logger logger = Logger.getLogger();

    @Override
    public Customer create(Customer newCustomer) {
        System.out.println("Here is the newCustomer as it enters the DAO layer: "+ newCustomer);

        try(Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "insert into customer (first_name, last_name, dob, ssn, username_u, password_p, email) values (?, ?, ?, ?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, newCustomer.getFirstName());
            ps.setString(2, newCustomer.getLastName());
            ps.setString(3, newCustomer.getDob());
            ps.setString(4, newCustomer.getSsn());
            ps.setString(5, newCustomer.getUsername_u());
            ps.setString(6, newCustomer.getPassword_p());
            ps.setString(7, newCustomer.getEmail());

            int checkInsert = ps.executeUpdate();

            if(checkInsert == 0){
                throw new ResourcePersistanceException("User was not entered into database due to some issue.");
            }

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return newCustomer;
    }

    @Override
    public List<Customer> findAll() throws IOException {

    List<Customer> customers = new LinkedList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "SELECT * FROM customer";//WHERE first_name like ?
/*          PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "A%");
            ResultSet rs = ps.executeQuery(sql);*/
            Statement s = conn.createStatement();
            ResultSet rs =s.executeQuery(sql);
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setDob(rs.getString("dob"));
                customer.setSsn(rs.getString("ssn"));
                customer.setUsername_u(rs.getString("username_u"));
                customer.setPassword_p(rs.getString("password_p"));
                customer.setEmail(rs.getString("email"));

                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        System.out.println("Returning customer information to user.");
        return customers;
    }

    @Override
    public List<Customer> findById(String last_name) {

        List<Customer> customers = new LinkedList<>();
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "select * from customer where last_name = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, last_name);

            ResultSet rs = ps.executeQuery(); // remember sql, bc selects are the keywords

            Customer customer = new Customer();

            if(!rs.next()){
                throw new ResourcePersistanceException("User was not found in the database, please check last name entered was correct.");
            }

            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));
            customer.setDob(rs.getString("dob"));
            customer.setSsn(rs.getString("ssn"));
            customer.setUsername_u(rs.getString("username_u"));
            customer.setPassword_p(rs.getString("password_p"));
            customer.setEmail(rs.getString("email"));

            customers.add(customer);

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return customers;
    }

    @Override
    public int update(String last_name, String email) {
        //System.out.println("Hello there Dao");
        int affectedRow;
        try (Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = String.format( "update customer set last_name = ? where email = ? ");
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, last_name);
            ps.setString(2, email);

            affectedRow = ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return affectedRow;
    }

    @Override
    public int delete (String first_name) {

        int affectedRow = 0;
        try (Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = String.format( "delete from customer where first_name = ?");
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, first_name);
            affectedRow = ps.executeUpdate();

         } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return affectedRow;
    }
    public Customer authenticateCustomer(String username, String password){

        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select * from customer where username_u = ? and password_p = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if(!rs.next()){
                return null;
            }

            Customer customer = new Customer();

            customer.setFirstName(rs.getString("first_name")); // this column lable MUST MATCH THE DB
            customer.setLastName(rs.getString("last_name"));
            customer.setDob(rs.getString("dob"));
            customer.setSsn(rs.getString( "ssn"));
            customer.setUsername_u(rs.getString("username_u"));
            customer.setPassword_p(rs.getString("password_p"));
            customer.setEmail(rs.getString("email"));

            return customer;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean checkEmail(String email) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select email from customer where email = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if(!rs.isBeforeFirst()){
                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

