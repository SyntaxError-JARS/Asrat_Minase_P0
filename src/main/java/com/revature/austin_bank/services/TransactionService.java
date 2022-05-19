package com.revature.austin_bank.services;

import com.revature.austin_bank.daos.TransactionDao;
import com.revature.austin_bank.exceptions.ResourcePersistanceException;
import com.revature.austin_bank.util.logging.Logger;

import java.text.NumberFormat;

public class TransactionService {

    private final TransactionDao transactionDao;
    public TransactionService (TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }
    private Logger logger = Logger.getLogger();

    NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
    public String addToBalance (String deposit, String accountNumber){

        double updatedAccount = TransactionDao.addToBalance(deposit, accountNumber);
        if(updatedAccount == 0){
            throw new ResourcePersistanceException("Deposit amount was not deposited in the database, either negative or empty numbers are provided. ");
        }
        return ("Amount of " + defaultFormat.format(Double.parseDouble(deposit)) + " has been successful deposited in to account number." + accountNumber );
    }

    public String subtractFromBalance (String withdrawal, String accountNumber){

        double updatedAccount = TransactionDao.subtractFromBalance(withdrawal, accountNumber);
        if(updatedAccount == 0){
            throw new ResourcePersistanceException("Amount was not withdrawn from the account either negative or empty numbers are provided.. ");
        }
        return ("Amount of "+ defaultFormat.format(Double.parseDouble(withdrawal)) +" has been successfully withdrawn from account number" + accountNumber);
    }

    public String getBalance(String NewTransactionHistory) {

       transactionDao.getBalance(NewTransactionHistory);
        if(NewTransactionHistory == null){
            throw new ResourcePersistanceException("In appropriate balance. ");
        }
        return " ";
    }
}
