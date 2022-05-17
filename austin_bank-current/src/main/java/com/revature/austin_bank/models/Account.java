package com.revature.austin_bank.models;

public class Account {

    private int custID;
    protected double balance;
    private String accountNumber;
    private String accountType;
    private double depositeAmount;
    private double withdrwalAmount;
    private String registrationDate;
    private String ssn;

    public Account(){
        super();
    }

    public Account(double balance, String accountNumber, String accountType, double depositeAmount, double withdrwalAmount, String registrationDate){
        super();
        this.balance= balance;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.depositeAmount = depositeAmount;
        this.withdrwalAmount = withdrwalAmount;
        this.registrationDate = registrationDate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getDepositeAmount() {
        return depositeAmount;
    }

    public void setDepositeAmount(double depositeAmount) {
        this.depositeAmount = depositeAmount;
    }

    public double getWithdrwalAmount() {
        return withdrwalAmount;
    }

    public void setWithdrwalAmount(double withdrwalAmount) {
        this.withdrwalAmount = withdrwalAmount;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }


    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance='" + balance + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountType ='" + accountType + '\'' +
                ", depositeAmount ='" + depositeAmount + '\'' +
                ", withdrawalAmount='" + withdrwalAmount + '\'' +
                ", registrationDate'" + registrationDate + '\'' +
                ", snn = " + ssn + '\''+
                '}';
    }

}




