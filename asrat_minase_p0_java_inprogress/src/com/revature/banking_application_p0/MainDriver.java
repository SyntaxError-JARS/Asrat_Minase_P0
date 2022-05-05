package com.revature.banking_application_p0;

import com.revature.banking_application_p0.util.AppState;

public class MainDriver {

    public static void main(String[] args){

        System.out.println("1. AppState instantiated");
        AppState appState = new AppState();

        System.out.println("Banking Application starting up....");
        appState.startup();

    }
}


