package com.d2j2.argenarianbank.repositories;

import com.d2j2.argenarianbank.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Iterable<Customer>findCustomersByFirstNameContainsAndLastNameAllIgnoreCaseContainsOrderByLastNameAscFirstNameAsc(String firstName, String lastName);

    Iterable<Customer>findCustomersByAddressStreetContainsAllIgnoreCaseOrderByLastNameAscFirstNameAsc(String streetAddress);

    Iterable<Customer>findCustomersByPhoneOrderByLastNameAscFirstNameAsc(String phone);

    Iterable<Customer>findCustomersByEmailIgnoreCaseOrderByLastNameAscFirstNameAsc(String email);

}
