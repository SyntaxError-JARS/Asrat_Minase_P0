package com.revature.austin_bank.daos;

import com.revature.austin_bank.exceptions.ResourcePersistanceException;
import com.revature.austin_bank.models.TransactionHistory;
import com.revature.austin_bank.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class TransactionDao {

    public static List<TransactionHistory> getBalance(String accountNumber) {

        List<TransactionHistory> accounts = new LinkedList<>();
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {
            String sql = "select * from account where account_number = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, accountNumber);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                throw new ResourcePersistanceException("Account was not found in the database, please check ID entered was correct.");
            }

            TransactionHistory transactionHistory = new TransactionHistory();

            transactionHistory.setBalance(rs.getDouble("balance"));
            transactionHistory.setAccountNUmber(rs.getString("account_number"));

            accounts.add(transactionHistory);

        } catch (SQLException e) {
            System.out.println(" Please make sure that the data entered was correct");
        }
        return accounts;
    }

    public static int addToBalance(String deposit, String accountNumber) {
        int affectedRow = 0;
        try {
            if (Double.parseDouble(deposit) < 0) {
                System.out.println(" Please enter a positive value. ");
            } else {
                try (Connection conn = ConnectionFactory.getInstance().getConnection()) {


                    //String sql = String.format("update account set deposit = ? where account_number = ? ");

                    String sql = String.format("update account set deposit = deposit + ?  where account_number = ?");

                    double x = Double.parseDouble(deposit);

                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setDouble(1, x);
                    ps.setString(2, accountNumber);

                    affectedRow = ps.executeUpdate();

                } catch (SQLException e) {
                    System.out.println(" Please make sure that the data entered was correct");
                }
            }

        }catch (NumberFormatException e) {
            System.out.println("Hey, you entered something wrong...");
        }
        return affectedRow;
    }


    public static int subtractFromBalance(String withdrawal, String accountNumber) {
        int affectedRow = 0;
        try {
            if (Double.parseDouble(withdrawal) < 0) {

                System.out.println(" Please enter a positive value. ");

            } else {
                try (Connection conn = ConnectionFactory.getInstance().getConnection()) {


                    String sql = String.format("update account set withdrawal = withdrawal+?  where account_number = ? ");
                    double x = Double.parseDouble(withdrawal);

                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setDouble(1, x);
                    ps.setString(2, accountNumber);

                    affectedRow = ps.executeUpdate();

                } catch (SQLException e) {

                    System.out.println(" Please make sure that the data entered was correct");
                }
            }
        }catch (NumberFormatException e) {
            System.out.println("Hey, you entered something wrong...");
        }
        return affectedRow;
    }
}
