package com.d2j2.argenarianbank.models;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @Size(min = 2, max = 25)
    private String firstName;
    @NotNull
    @Size(min = 2, max = 25)
    private String lastName;
    @NotNull
    @Size(min = 4, max = 32)
    private String addressStreet;
    @NotNull
    @Size(min = 2, max = 16)
    private String addressCity;
    @NotNull
    @Size(min = 2, max = 2)
    private String addressState;
    @NotNull
    @Digits(integer = 5, fraction = 0)
    private String addressZip;
    @NotNull
    @Size(min = 10, max = 20)
    private String phone;
    @NotNull
    @Email
    @Size(min = 7, max = 50)
    private String email;
    @ManyToMany
    private Set<Account>customerAccounts;

    public Customer() {
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressStreet = addressStreet;
        this.addressCity = addressCity;
        this.addressState = addressState;
        this.addressZip = addressZip;
        this.customerAccounts = new HashSet<>();
    }
    public Customer(String firstName, String lastName, String addressStreet, String addressCity, String addressState, String addressZip, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressStreet = addressStreet;
        this.addressCity = addressCity;
        this.addressState = addressState;
        this.addressZip = addressZip;
        this.phone = phone;
        this.email = email;
        this.customerAccounts = new HashSet<>();
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressState() {
        return addressState;
    }

    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }

    public String getAddressZip() {
        return addressZip;
    }

    public void setAddressZip(String addressZip) {
        this.addressZip = addressZip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Account> getCustomerAccounts() {
        return customerAccounts;
    }

    public void setCustomerAccounts(Set<Account> customerAccounts) {
        this.customerAccounts = customerAccounts;
    }

    public void addAccounts(Account account){
        customerAccounts.add(account);
    }
}
