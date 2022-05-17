package com.revature.austin_bank.daos;

import com.revature.austin_bank.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionDao {

    public static int addToBalance(String deposit, String accountNumber) {
        int affectedRow;
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {


            //String sql = String.format("update account set deposit = ? where account_number = ? ");

            String sql = String.format("update account set deposit = deposit + ?  where account_number = ?");

            double x = Double.parseDouble(deposit);

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, x);
            ps.setString(2, accountNumber);




            affectedRow = ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return affectedRow;

    }


    public static int subtractFromBalance(String withdrawal, String accountNumber) {
        int affectedRow;
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            double x = Double.parseDouble(withdrawal);
            String sql = String.format("update account set withdrawal = withdrawal - ?  where account_number = ? ");
               PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, x);
            ps.setString(2, accountNumber);


            double userAmount = x;
            x = x - userAmount;

            affectedRow = ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return affectedRow;

    }


}
