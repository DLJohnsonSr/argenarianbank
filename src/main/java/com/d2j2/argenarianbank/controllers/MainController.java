package com.d2j2.argenarianbank.controllers;

import com.d2j2.argenarianbank.models.Account;
import com.d2j2.argenarianbank.models.Customer;
import com.d2j2.argenarianbank.models.Transaction;
import com.d2j2.argenarianbank.repositories.AccountRepository;
import com.d2j2.argenarianbank.repositories.CustomerRepository;
import com.d2j2.argenarianbank.repositories.TransactionRepository;
import com.d2j2.argenarianbank.services.AccountTransactionService;
import com.d2j2.argenarianbank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    AccountRepository acctRepo;
    @Autowired
    CustomerRepository custRepo;
    @Autowired
    TransactionRepository transRepo;
    @Autowired
    AccountTransactionService acctTransServ;
    @Autowired
    CustomerService custServ;

    @GetMapping("/")
    public String showBranchMainPage(Model model){
//        assign customer by user from Customer index!!!!!!!!!!!!!!!!
        Customer customer = custRepo.findAll().iterator().next();
        model.addAttribute("customer", customer);
        model.addAttribute("accounts", customer.getCustomerAccounts());
        return "index";
    }
    @GetMapping("/login")
    public String loginUser(){
        return "login";
    }


}
