package com.d2j2.argenarianbank.configuration;

import com.d2j2.argenarianbank.models.Account;
import com.d2j2.argenarianbank.models.Customer;
import com.d2j2.argenarianbank.models.Transaction;
import com.d2j2.argenarianbank.repositories.AccountRepository;
import com.d2j2.argenarianbank.repositories.CustomerRepository;
import com.d2j2.argenarianbank.repositories.TransactionRepository;
import com.d2j2.argenarianbank.services.AccountTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    AccountRepository acctRepo;
    @Autowired
    CustomerRepository custRepo;
    @Autowired
    TransactionRepository transRepo;
    @Autowired
    AccountTransactionService acctTransServ;

    @Override
    public void run(String... args) throws Exception {

        Customer aCustomer = new Customer("John","Smith","123 Main St.","Sometown","DC","11111","555-555-5555","me@you.com");
        custRepo.save(aCustomer);

        Account anAccount = new Account("Checking","01/01/2019",100);
        acctRepo.save(anAccount);
        acctTransServ.newAccountSetup(anAccount);
        aCustomer.addAccounts(anAccount);
        custRepo.save(aCustomer);

        Transaction aTransaction = new Transaction(2,"01/09/2019","Payday","Credit",1000,true,anAccount);
        aTransaction.setDate(aTransaction.convertStringDate(aTransaction.getStringDate()));
        transRepo.save(aTransaction);

        aTransaction = new Transaction(3,"01/10/2019","Gas","Debit",19.52,true,anAccount);
        aTransaction.setDate(aTransaction.convertStringDate(aTransaction.getStringDate()));
        transRepo.save(aTransaction);

        aTransaction = new Transaction(4,"01/13/2019","Lunch","Debit",9.14,true,anAccount);
        aTransaction.setDate(aTransaction.convertStringDate(aTransaction.getStringDate()));
        transRepo.save(aTransaction);

        aTransaction = new Transaction(5, "01/19/2019","Date","Debit",100,true,anAccount);
        aTransaction.setDate(aTransaction.convertStringDate(aTransaction.getStringDate()));
        transRepo.save(aTransaction);

        aTransaction = new Transaction(6,"01/23/2019","Payday","Credit",1000,false,anAccount);
        aTransaction.setDate(aTransaction.convertStringDate(aTransaction.getStringDate()));
        transRepo.save(aTransaction);

        aTransaction = new Transaction(7,"01/24/2019","Gas","Debit",19.77,false,anAccount);
        aTransaction.setDate(aTransaction.convertStringDate(aTransaction.getStringDate()));
        transRepo.save(aTransaction);

       acctTransServ.updateAccountBalance(anAccount.getId());
    }
}
