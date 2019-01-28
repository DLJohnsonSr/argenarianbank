package com.d2j2.argenarianbank.services;

import com.d2j2.argenarianbank.models.Account;
import com.d2j2.argenarianbank.models.Customer;
import com.d2j2.argenarianbank.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository custRepo;


    public void AddNewAccountToCustomer(long custId, Account acct){
        Customer thisCustomer = custRepo.findById(custId).get();
        thisCustomer.addAccounts(acct);
        custRepo.save(thisCustomer);
    }

//    NOTE:   1) Determines the type of search
//            2) returns the appropriate search query
    public Iterator <Customer> findCustomersFromSearch(String searchType, String searchValue){

        Set<Customer> set = new HashSet<>();
        Iterator<Customer> customers = set.iterator();
        int typeNum = new Integer(searchType);
        String firstName, lastName;
        List names = new ArrayList();
        switch (typeNum){
            case 1:
                String[] stringName = searchValue.split("\\s");
                for (String eachString:stringName){
                    names.add(eachString);
                }
                firstName = names.get(0).toString();
                lastName = names.get(1).toString();
                customers = custRepo.findCustomersByFirstNameContainsAndLastNameAllIgnoreCaseContainsOrderByLastNameAscFirstNameAsc(firstName, lastName).iterator();
                break;
            case 2:
                customers = custRepo.findCustomersByAddressStreetContainsAllIgnoreCaseOrderByLastNameAscFirstNameAsc(searchValue).iterator();
                break;
            case 3:
                customers = custRepo.findCustomersByPhoneOrderByLastNameAscFirstNameAsc(searchValue).iterator();
                break;
            case 4:
                customers = custRepo.findCustomersByEmailIgnoreCaseOrderByLastNameAscFirstNameAsc(searchValue).iterator();
                break;
        }

        return customers;
    }
}
