package com.revature.austin_bank.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.austin_bank.daos.AccountDao;
import com.revature.austin_bank.daos.CustomerDao;
import com.revature.austin_bank.daos.TransactionDao;
import com.revature.austin_bank.services.AccountService;
import com.revature.austin_bank.services.CustomerServices;
import com.revature.austin_bank.services.TransactionService;
import com.revature.austin_bank.web.servelet.AccountServlet;
import com.revature.austin_bank.web.servelet.AuthServlet;
import com.revature.austin_bank.web.servelet.CustomerServlet;
import com.revature.austin_bank.web.servelet.TransactionHServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import java.io.IOException;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Make our single ObjectMapper instance
        ObjectMapper mapper = new ObjectMapper();

        // Instantiate all Daos first
        CustomerDao customerDao = new CustomerDao();
        AccountDao accountDao = new AccountDao();
        TransactionDao transactionDao = new TransactionDao();

        // Instantiate and initialize the services with their dao dependency
        CustomerServices customerServices = new CustomerServices(customerDao);
        AccountService accountService = new AccountService(accountDao);
        TransactionService transactionService = new TransactionService(transactionDao);

        AuthServlet authServlet = new AuthServlet(customerServices, mapper);
        CustomerServlet customerServlet = new CustomerServlet(customerServices, mapper);
        AccountServlet accountServlet = new AccountServlet(accountService, mapper);
        TransactionHServlet transcationServlet = null;
        try {
            transcationServlet = new TransactionHServlet(transactionService, mapper);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        ServletContext context = sce.getServletContext();
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
        context.addServlet("CustomerServlet", customerServlet).addMapping("/customer/*");
        context.addServlet("AccountServlet", accountServlet).addMapping("/account/*");
        context.addServlet("TransactionServlet", transcationServlet).addMapping("/transaction/*");

        //context.addServlet("AccountServlet", accountServlet).addMapping("/account/*");

    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
