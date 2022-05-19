package com.revature.austin_bank.web.servelet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.austin_bank.exceptions.ResourcePersistanceException;
import com.revature.austin_bank.services.TransactionService;
import com.revature.austin_bank.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TransactionHServlet extends HttpServlet {
    private final TransactionService transactionService;
    private final ObjectMapper mapper;
    private final Logger logger = Logger.getLogger();

    public TransactionHServlet(TransactionService transactionService, ObjectMapper mapper) throws ServletException, IOException {
        this.transactionService = transactionService;
        this.mapper = mapper;
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!Authable.checkAuth(req, resp)) return;


        if (req.getParameter("deposit") != null) {
            try {

                String payload = mapper.writeValueAsString(transactionService.addToBalance(req.getParameter("deposit"), req.getParameter("account_number")));
                resp.getWriter().write(payload);

            } catch (ResourcePersistanceException e) {
                logger.warn(e.getMessage());
                resp.setStatus(404);
                resp.getWriter().write(e.getMessage());
            }
        }

        if (req.getParameter("withdrawal") != null) {
            try {
                String payload = mapper.writeValueAsString(transactionService.subtractFromBalance(req.getParameter("withdrawal"), req.getParameter("account_number")));
                resp.getWriter().write(payload);

            } catch (ResourcePersistanceException e) {
                logger.warn(e.getMessage());
                resp.setStatus(404);
                resp.getWriter().write(e.getMessage());
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!Authable.checkAuth(req, resp)) return;

        if (req.getParameter("balance") != null) {

            try {

                String payload = mapper.writeValueAsString(transactionService.getBalance(req.getParameter("accountNumber")));
                resp.getWriter().write(payload);

            } catch (ResourcePersistanceException e) {
                logger.warn(e.getMessage());
                resp.setStatus(404);
                resp.getWriter().write(e.getMessage());
            }

        }
    }
}