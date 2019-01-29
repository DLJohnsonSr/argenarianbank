package com.d2j2.argenarianbank.repositories;


import com.d2j2.argenarianbank.models.Account;
import com.d2j2.argenarianbank.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Iterable<Account>findAllByAccountCustomers(Customer customer);

    Account findByAccountNumber (int accountNumber);

}
