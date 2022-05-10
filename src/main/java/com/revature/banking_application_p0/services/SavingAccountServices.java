package com.revature.banking_application_p0.services;

import com.revature.banking_application_p0.models.Account;

public class SavingAccountServices extends Account {

    private double intrestRate;

    public SavingAccountServices(int accountNumber, double intrestRate){
        super(accountNumber);
        this.intrestRate = intrestRate;
    }

    public double getIntresetRate(double intrestRate) {
        return this.intrestRate;
    }
    public void setIntrestRate(double intrestRate){
        this.intrestRate = intrestRate;
    }

    public double calcIntrestRate(){
        return balance * intrestRate;
    }
    public void applyIntrest(){
        double intrest = calcIntrestRate();
        System.out.printf("Interest amount %.2f added to balance %n", intrest);
        deposit(intrest);

    }

    /* Method to deposit funds based on the requirements { Amount to be
       deposited should not be less than 0 and greater than 250000.00 dollars.}
    */

    public void deposit( double amount) {

        if (amount > 0){
            balance += amount;
            System.out.printf("Amount %.2f deposited%n", amount);
            System.out.printf("Current balance is:  %.2f", balance);

        } else {
            System.out.println("Please deposit money. ");
        }

    }

    /* Method to withdraw funds based on the requirements { Amount to be
       withdrawn should not be less than 0 and less than balance.}
    */

    public void withdraw ( double amount){
        if ((amount ) <= balance){
            balance -= amount;
            System.out.printf("Amount %.2f withdrawn from Account%n", amount);
            System.out.printf("Current balance is:  %.2f", balance);

        } else {
            System.out.println("You have not enough money to withdraw. ");
        }


    }

}
