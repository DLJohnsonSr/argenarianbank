package com.d2j2.argenarianbank.controllers;

import com.d2j2.argenarianbank.models.Customer;
import com.d2j2.argenarianbank.repositories.AccountRepository;
import com.d2j2.argenarianbank.repositories.CustomerRepository;
import com.d2j2.argenarianbank.repositories.TransactionRepository;
import com.d2j2.argenarianbank.services.AccountTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {

    @Autowired
    AccountRepository acctRepo;
    @Autowired
    CustomerRepository custRepo;
    @Autowired
    TransactionRepository transRepo;
    @Autowired
    AccountTransactionService acctTransServ;

    //    Temparary link
    @GetMapping("/customerindex")
    public String showAccountSummaryPage(Model model){
//        assign customer by user from Customer index!!!!!!!!!!!!!!!!
        Customer customer = custRepo.findAll().iterator().next();
        model.addAttribute("customer", customer);
        model.addAttribute("accounts", customer.getCustomerAccounts());
        return "customer/customerindex";
    }
}
