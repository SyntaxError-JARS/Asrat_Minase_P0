package com.revature.banking_application_p0.models;

import java.util.Date;

public abstract class Account {

    private int accountNumber;

    protected double balance;

    public Account() {

    };

    public Account ( int accountNumber){
        this.accountNumber = accountNumber;
        balance = 0;
    }

    public double getBalance(){
        return this.balance;
    }
    public int getAccountNumber(){
        return this.accountNumber;
    }

    public abstract void deposit( double amount);

    public abstract void withdraw ( double amount);
}


