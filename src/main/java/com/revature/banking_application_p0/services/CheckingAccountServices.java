package com.revature.banking_application_p0.services;

import com.revature.banking_application_p0.models.Account;

import java.text.NumberFormat;

public class CheckingAccountServices extends Account {



    private static double FEE = 2.5;
    NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
    public CheckingAccountServices(int accountNumber, double fee){
        super(accountNumber);
        FEE = fee;
    }
    /* Method to deposit funds based on the requirements { Amount to be
       deposited should not be less than 0 and greater than 250000.00 dollars.}
    */
    public void deposit( double amount) {


        if (amount > 0){

            balance += amount;

            System.out.printf("Amount of " + defaultFormat.format(amount) + " was deposited.");
            balance -= FEE;
            System.out.printf("\nFee of " + defaultFormat.format(FEE) + " was applied.");
            System.out.printf("\nCurrent balance is "+ defaultFormat.format(balance) + ".");

        } else {
            System.out.println("Please deposit money. ");
        }
    }
    /* Method to withdraw funds based on the requirements { Amount to be
       withdrawn should not be less than 0 and less than balance.}
    */
    public void withdraw ( double amount){
        if ((amount + FEE) <= balance){
            balance -= amount;
            System.out.printf("Amount %.2f withdrawn from Account%n", amount);
            balance -= FEE;
            System.out.printf("Fee %.2f Applied%n", FEE);
            System.out.printf("Current balance is:  %.2f", balance);
        } else {
            System.out.println("You have not enough money to withdraw. ");
        }
    }
}

