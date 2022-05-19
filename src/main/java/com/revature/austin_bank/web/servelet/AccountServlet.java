package com.revature.austin_bank.web.servelet;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.austin_bank.exceptions.ResourcePersistanceException;
import com.revature.austin_bank.models.Account;
import com.revature.austin_bank.services.AccountService;
import com.revature.austin_bank.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.revature.austin_bank.web.servelet.Authable.checkAuth;

public class AccountServlet extends HttpServlet implements Authable {

    private final AccountService accountService;
    private final ObjectMapper mapper;
    private final Logger logger = Logger.getLogger();

    public AccountServlet(AccountService accountService, ObjectMapper mapper) {
        this.accountService = accountService;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(!checkAuth(req, resp)) return;
        if(req.getParameter("account_number") != null){
            List<Account> account = accountService.readById(req.getParameter("account_number"));
            String payload = mapper.writeValueAsString(account);
            resp.getWriter().write(payload);
            return;
        }

        List<Account> accounts = accountService.readAll();
        String payload = mapper.writeValueAsString(accounts);

        resp.getWriter().write(payload);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(!checkAuth(req, resp)) return;
        // TODO: Let's create an account
        try {
            Account newAccount = mapper.readValue(req.getInputStream(), Account.class); // from JSON to Java Object (Account)
            Account persistedAccount = accountService.create(newAccount);

            String payload = mapper.writeValueAsString(persistedAccount); // Mapping from Java Object (Account) to JSON

            resp.getWriter().write("Persisted the provided account as show below \n");
            resp.getWriter().write(payload);
            resp.setStatus(201);
        }catch (Exception e){
            resp.getWriter().write("Customer's account is already registered. ");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(!Authable.checkAuth(req, resp)) return;
        try {
            //System.out.println("Hello there");
            //System.out.println(req.getParameter("last_name"));
            //System.out.println(req.getParameter("email"));
            String payload = mapper.writeValueAsString(accountService.update(req.getParameter("account_type"), req.getParameter("account_number")));
            resp.getWriter().write(payload);
        } catch (ResourcePersistanceException e){
            logger.warn(e.getMessage());
            resp.setStatus(404);
            resp.getWriter().write(e.getMessage());
        }
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(!Authable.checkAuth(req, resp)) return;
        try {
            String payload = mapper.writeValueAsString(accountService.delete(req.getParameter("account_number")));
            resp.getWriter().write(payload);
        } catch (ResourcePersistanceException e){
            logger.warn(e.getMessage());
            resp.setStatus(404);
            resp.getWriter().write(e.getMessage());
        }

    }
}