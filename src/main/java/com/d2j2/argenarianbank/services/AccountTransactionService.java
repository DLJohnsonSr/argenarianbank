package com.d2j2.argenarianbank.services;


import com.d2j2.argenarianbank.models.Account;
import com.d2j2.argenarianbank.models.Transaction;
import com.d2j2.argenarianbank.repositories.AccountRepository;
import com.d2j2.argenarianbank.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class AccountTransactionService {

    @Autowired
    AccountRepository acctRepo;
    @Autowired
    TransactionRepository transRepo;


//    NOTE: 1) add account number
//          2) creates start balance transaction
//          3) saves new transaction
//          4) updates account balance
    public void newAccountSetup(Account acct){
        acct.setAccountNumber(1 + acctRepo.findAll(Sort.by(Sort.Order.desc("accountNumber"))).iterator().next().getAccountNumber());
        acctRepo.save(acct);
        Transaction thisTrans = new Transaction(true,0, acct.getOpenDate(),"Start Balance","Credit", acct.getStartBalance(),true, acct);
        saveNewTransaction(acct.getId(), thisTrans);
        updateAccountBalance(acct.getId());
    }

//    NOTE: 1) updates transaction balance of when each transaction is posted
//          2) updates end account balance after last transaction is posted
    public void updateAccountBalance(long acctId){
        Double currentBalance = 0.0;
        for (Transaction eachTrans:transRepo.findAllByAccount_IdOrderByDateAscIdAsc(acctId)) {
            if (eachTrans.isPosted() == true){
                if (eachTrans.getCreditOrDebit().equalsIgnoreCase("Credit")){
                    currentBalance = currentBalance + eachTrans.getAmount();
                }else {
                    currentBalance = currentBalance - eachTrans.getAmount();
                }
                eachTrans.setTransactionBalance(currentBalance);
                transRepo.save(eachTrans);
            }
        }
        Account thisAcct = acctRepo.findById(acctId).get();
        thisAcct.setCurrentBalance(currentBalance);
        acctRepo.save(thisAcct);
    }

//    NOTE:  1) adds transaction number
//           2) converts string date to LocalDate
//           3) add account to transaction
//           4) saves transaction
    public void saveNewTransaction(long acctId, Transaction trans){
        trans.setAccount(acctRepo.findById(acctId).get());
        transRepo.save(trans);
        trans.setTransactionNumber(1 + transRepo.findAllByAccount_IdOrderByTransactionNumberDesc(acctId).iterator().next().getTransactionNumber());
        trans.setDate(trans.convertStringDate(trans.getStringDate()));
        transRepo.save(trans);
    }

    public void postTransaction(long transId){
        Transaction thisTrans = transRepo.findById(transId).get();
        thisTrans.setPosted(!thisTrans.isPosted());
        transRepo.save(thisTrans);
    }
}
