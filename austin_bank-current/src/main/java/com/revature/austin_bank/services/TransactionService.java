package com.revature.austin_bank.services;

import com.revature.austin_bank.daos.TransactionDao;
import com.revature.austin_bank.exceptions.ResourcePersistanceException;
import com.revature.austin_bank.util.logging.Logger;

public class TransactionService {

    private final TransactionDao transactionDao;
    public TransactionService (TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }
    private Logger logger = Logger.getLogger();
    public String addToBalance (String deposit, String accountNumber){

        double updatedAccount = TransactionDao.addToBalance(deposit, accountNumber);
        if(updatedAccount == 0){
            throw new ResourcePersistanceException("Amount was not deposited in the database. ");
        }
        logger.info("Amount " + deposit + " of account number " + accountNumber + " has been deposited: " );
        return "Amount has been successful deposited.";
    }

    public String subtractFromBalance (String withdrawal, String accountNumber){

        double updatedAccount = TransactionDao.subtractFromBalance(withdrawal, accountNumber);
        if(updatedAccount == 0){
            throw new ResourcePersistanceException("Amount was not deposited in the database. ");
        }
        logger.info("Amount " + withdrawal + " of account number " + accountNumber + " has been withdrawn: " );
        return "Amount has been successful withdrawn.";

    }
}
