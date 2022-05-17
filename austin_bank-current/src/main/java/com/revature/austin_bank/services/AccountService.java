package com.revature.austin_bank.services;

import com.revature.austin_bank.daos.AccountDao;
import com.revature.austin_bank.exceptions.AuthenticationException;
import com.revature.austin_bank.exceptions.InvalidRequestException;
import com.revature.austin_bank.exceptions.ResourcePersistanceException;
import com.revature.austin_bank.models.Account;
import com.revature.austin_bank.util.logging.Logger;

import java.io.IOException;
import java.util.List;

public class AccountService implements Serviceable<Account> {

    private final AccountDao accountDao;

    private Logger logger = Logger.getLogger();

    public AccountService (AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public List<Account> readAll(){
        logger.info("Begin reading Account in our database.");

        try {
            // TODO: Array List
            List<Account> accounts = accountDao.findAll();
            logger.info("All Accounts have been found here are the results: \n");
            return accounts;

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public List<Account> readById(String accountNumber) throws ResourcePersistanceException {

        logger.info("Begin reading Customers in the database using account number.");

        try {
            List<Account> customer = accountDao.findById(accountNumber);
            return customer;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public String update(String accountType, String accountNumber) {
        //System.out.println("Hello there");
        int updatedCustomer = accountDao.update(accountType, accountNumber);
        if(updatedCustomer == 0){
            throw new ResourcePersistanceException("Account was not updated in the database. ");
        }
        logger.info("Account " + accountNumber + " of type " + accountType + " has been updated: " );
        return "Account has been successful updated.";

    }

    @Override
    public String delete(String accountNumber) {

        int deletedCustomer = accountDao.delete(accountNumber);
        if(deletedCustomer == 0){
            throw new ResourcePersistanceException("No account found with this account number. ");
        }
        logger.info("Account has been deleted: " + accountNumber);
        return "Account has been successful deleted.";
    }

    public boolean validateAccountNotUsed(String accountNUmber){
        return accountDao.checkAccountNumber(accountNUmber);
    }

    public Account create(Account newAccount){

        logger.info("Registering Account : " + newAccount);
        if(!validateInput(newAccount)){ // checking if false
            // TODO:
            throw new InvalidRequestException("User input was not validated, either empty String or null values");
        }
        //System.out.println(newAccount.getAccountNumber());
        if(validateAccountNotUsed(newAccount.getAccountNumber())){
            throw new InvalidRequestException("User account number is already in use. Please try again with another account number or login into previous made account.");

        }

        Account persistedAccount = accountDao.create(newAccount);

        if(persistedAccount == null){
            throw new ResourcePersistanceException("Account was not persisted to the database upon registration");
        }
        logger.info("Account has been persisted: " + newAccount);
        return persistedAccount;
    }
    @Override
    public boolean validateInput(Account newAccount) {

        logger.debug("Validating Account: " + newAccount);
        if(newAccount == null) return false;
        if(newAccount.getBalance() <= 25.0 ) return false;
        if(newAccount.getAccountNumber()== null || newAccount.getAccountNumber().trim().equals("")) return false;
        if(newAccount.getAccountType() == null || newAccount.getAccountType().trim().equals("")) return false;
        if(newAccount.getDepositeAmount() == 0 ) return false;
        if(newAccount.getWithdrwalAmount() == 0 ) return false;
        if(newAccount.getRegistrationDate() == null || newAccount.getAccountType().trim().equals("")) return false;
        return newAccount.getSsn() != null || newAccount.getSsn().trim().equals("");
    }

    public Account authenticateAccount(String accountType, String accountNumber){

        if(accountNumber == null || accountNumber.trim().equals("") || accountNumber  == null || accountNumber.trim().equals("")) {
            throw new InvalidRequestException("Either account type or account number is an invalid entry. Please try logging in again");
        }

        Account authenticatedAccount = accountDao.authenticateAccount(accountType, accountNumber);
        if (authenticatedAccount == null){
            throw new AuthenticationException("Unauthenticated user, information provided was not consistent with our database.");
        }
        System.out.println(authenticatedAccount);

        return authenticatedAccount;

    }

}


