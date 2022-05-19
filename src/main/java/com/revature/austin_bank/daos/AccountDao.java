package com.revature.austin_bank.daos;

import com.revature.austin_bank.exceptions.ResourcePersistanceException;
import com.revature.austin_bank.models.Account;
import com.revature.austin_bank.util.ConnectionFactory;
import com.revature.austin_bank.util.logging.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class AccountDao implements Genericable<Account> {

    private Logger logger = Logger.getLogger();

    @Override
    public Account create(Account newAccount) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "insert into account values (default, default, ?, ?, ?, ?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            // 1-indexed, so first ? starts are 1
            ps.setDouble(1, newAccount.getBalance());
            ps.setString(1, newAccount.getAccountNumber());
            ps.setString(2, newAccount.getAccountType());
            ps.setDouble(3, newAccount.getDepositeAmount());
            ps.setDouble(4, newAccount.getWithdrwalAmount());
            ps.setString(5, newAccount.getRegistrationDate());
            ps.setString(6, newAccount.getSsn());


            int checkInsert = ps.executeUpdate();

            if (checkInsert == 0) {
                throw new ResourcePersistanceException("Account was not persisted. ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return newAccount;
    }

    @Override
    public java.util.List<Account> findAll() throws IOException {

        List<Account> accounts = new LinkedList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "select * from account";
            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Account account = new Account();
                account.setCustID(rs.getInt("custID"));
                account.setBalance(rs.getDouble("balance"));
                account.setAccountNumber(rs.getString("account_number"));
                account.setAccountType(rs.getString("account_type"));
                account.setDepositeAmount(rs.getDouble("deposit"));
                account.setWithdrwalAmount(rs.getDouble("withdrawal"));
                account.setRegistrationDate(rs.getString("registration_date"));
                account.setSsn(rs.getString("ssn"));

                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return accounts;
    }

    @Override
    public List<Account> findById(String accountNumber) {
        List<Account> accounts = new LinkedList<>();
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) { // try with resources, because Connection extends the interface Auto-Closeable

            String sql = "select * from account where account_number = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, accountNumber);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                throw new ResourcePersistanceException("Account was not found in the database, please check ID entered was correct.");
            }

            Account account = new Account();
            account.setCustID(rs.getInt("custID"));
            account.setBalance(rs.getDouble("balance"));
            account.setAccountNumber(rs.getString("account_number"));
            account.setAccountType(rs.getString("account_type"));
            account.setDepositeAmount(rs.getDouble("deposit"));
            account.setWithdrwalAmount(rs.getDouble("withdrawal"));
            account.setRegistrationDate(rs.getString("registration_date"));
            account.setSsn(rs.getString("ssn"));

            accounts.add(account);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return accounts;
    }

    @Override
    public int update(String accountType, String accountNumber) {
        int affectedRow;
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = String.format("update account set account_type = ? where account_number = ? ");
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, accountType);
            ps.setString(2, accountNumber);

            affectedRow = ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return affectedRow;

    }

    @Override
    public int delete(String accountNumber) {
        int affectedRow = 0;
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = String.format("delete from account where account_number = ?");
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, accountNumber);
            affectedRow = ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return affectedRow;
    }

    public Account authenticateAccount(String accountType, String accountNumber) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "select * from account where account_type = ? and account_number = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, accountType);
            ps.setString(2, accountNumber);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            }

            Account account = new Account();

            account.setBalance(rs.getDouble("balance"));
            account.setAccountNumber(rs.getString("account_number"));
            account.setAccountType(rs.getString("account_type"));
            account.setDepositeAmount(rs.getDouble("deposit"));
            account.setWithdrwalAmount(rs.getDouble("withdrawal"));
            account.setRegistrationDate(rs.getString("registration_date"));
            account.setSsn(rs.getString("ssn"));


            return account;

        } catch (SQLException e) {
            System.out.println(" Please make sure that the data entered was correct");
            return null;
        }
    }

    public boolean checkAccountNumber(String accountNUmber) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "select * from account where account_number = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, accountNUmber);

            ResultSet rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}