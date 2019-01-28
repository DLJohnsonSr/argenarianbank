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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class BranchController {

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


//    Temparary link
    @GetMapping("/branchindex")
    public String branchIndex(){
        return "branch/branchindex";
    }
//    NOTE: customer profile management paths
    @GetMapping("/createcustomer")
    public String createCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "fragments/customerform";
    }
    @PostMapping("/savecustomer/{custId}")
    public String saveCustomer(@ModelAttribute Customer cust, @Valid BindingResult result, Model model){
        if (result.hasErrors()){
            return "fragments/customerform";
        }
        custRepo.save(cust);
        return "redirect:/branchindex";
    }
    @GetMapping("/viewallcustomers")
    public String viewAllCustomers(Model model){
        model.addAttribute("customers", custRepo.findAll());
        return "branch/customersearchresults";
    }
    @GetMapping("/viewcustomer/{custId}")
    public String viewCustomer(@PathVariable("custId") long custId, Model model){
        model.addAttribute("customer", custRepo.findById(custId).get());
        model.addAttribute("accounts", acctRepo.findAllByAccountCustomers(custRepo.findById(custId).get()));
        return "branch/customerpage";
    }
    //    NOTE: customer search paths
    @GetMapping("/searchcustomers")
    public String searchCustomer(){
        return "fragments/searchpage";
    }
    @PostMapping("/customersearchresults")
    public String viewCustomerSearchResults(@RequestParam("searchType") String searchType,@RequestParam("searchValue") String searchValue, Model model){
        model.addAttribute("customers", custServ.findCustomersFromSearch(searchType,searchValue));
        return "branch/customersearchresults";
    }
    @GetMapping("/searchaccounts")
    public String searchAccount(){
        return "fragments/searchpage";
    }
    @PostMapping("accountsearchresults")
    public String viewAccountSearchResults(@RequestParam("accountNumber") int accountNumber, Model model){
        model.addAttribute("account", acctRepo.findByAccountNumber(accountNumber));
        return "branch/accountsearchresults";
    }
//    NOTE: Account paths
    @GetMapping("/openaccount/{custId}")
    public String createAccount(@PathVariable("custId") long custId, Model model){
        model.addAttribute("account", new Account());
        model.addAttribute("customer", custRepo.findById(custId).get());
        return "fragments/accountform";
    }
    @PostMapping("/saveaccount/{custId}")
    public String createNewAccount(@PathVariable("custId") long custId, @ModelAttribute Account acct, @Valid BindingResult result){
        if (result.hasErrors()){
            return "fragments/accountform";
        }
        acctRepo.save(acct);
        custServ.AddNewAccountToCustomer(custId,acct);
        acctTransServ.newAccountSetup(acct);
        return "redirect:/viewcustomer/{custId}";
    }
//    NOTE: Transaction management paths
    @GetMapping("/accounttransactions/{custId}/{acctId}")
    public String listTransactions(@PathVariable("custId") long custId, @PathVariable("acctId") long acctId, Model model){
        model.addAttribute("customer", custRepo.findById(custId).get());
        model.addAttribute("account", acctRepo.findById(acctId).get());
        model.addAttribute("transactions", transRepo.findAllByAccount_IdOrderByDateAscIdAsc(acctId));
        return "fragments/accounttransactions";
    }
    @GetMapping("/addtransaction/{custId}/{acctId}")
    public String addTransactions(@PathVariable("custId") long custId, @PathVariable("acctId") long acctId, Model model){
        model.addAttribute("customer", custRepo.findById(custId).get());
        model.addAttribute("account", acctRepo.findById(acctId).get());
        model.addAttribute("transaction", new Transaction());
        return "branch/transactionform";
    }
    @PostMapping("/savetransaction/{custId}/{acctId}")
    public String saveTransactions(@PathVariable("acctId") long acctId, @ModelAttribute Transaction trans, @Valid  BindingResult result){
        Account account = acctRepo.findById(acctId).get();
        if (result.hasErrors()){
            return "branch/transactionform";
        }
        if (trans.isPosted()==true){

            acctTransServ.saveNewTransaction(account.getId(), trans);
            acctTransServ.updateAccountBalance(account.getId());
        }else {
            acctTransServ.saveNewTransaction(acctId, trans);
        }
        return "redirect:/accounttransactions/{custId}/{acctId}";
    }
    @GetMapping("/posttransaction/{custId}/{acctId}/{transId}")
    public String postTransaction(@PathVariable("transId") long transId, Model model){
        Transaction transaction = transRepo.findById(transId).get();
        acctTransServ.postTransaction(transaction.getId());
        acctTransServ.updateAccountBalance(transaction.getAccount().getId());
        return "redirect:/accounttransactions/{custId}/{acctId}";
    }
    @GetMapping("/edittransaction/{custId}/{acctId}/{transId}")
    public String editTransaction(@PathVariable("custId") long custId, @PathVariable("acctId") long acctId, @PathVariable("transId") long transId, Model model){
        model.addAttribute("customer", custRepo.findById(custId).get());
        model.addAttribute("account", acctRepo.findById(acctId).get());
        model.addAttribute("transaction", transRepo.findById(transId).get());
        return "branch/transactionform";
    }
    @GetMapping("/deletetransaction/{custId}/{acctId}/{transId}")
    public String deleteTransaction(@PathVariable("transId") long transId){
        transRepo.delete(transRepo.findById(transId).get());
        return "redirect:/accounttransactions/{custId}/{acctId}";
    }
}
