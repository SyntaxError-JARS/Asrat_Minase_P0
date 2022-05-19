package com.revature.austin_bank.models;

public class TransactionHistory {

    protected double balance;

    protected String accountNUmber;

    public TransactionHistory() {
        super();
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public TransactionHistory(double balance, String accountNumber, String accountType, double depositeAmount, double withdrwalAmount, String registrationDate) {
        super();
        this.balance = balance;
        this.accountNUmber = accountNumber;

    }

    public String getAccountNUmber() {
        return accountNUmber;
    }

    public void setAccountNUmber(String accountNUmber) {
        this.accountNUmber = accountNUmber;
    }
}


