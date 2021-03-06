package com.d2j2.argenarianbank.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private int accountNumber;
    @NotNull
    private String accountName;
    @NotEmpty
    private String openDate;
    @NotNull
    private BigDecimal startBalance;
    @NotNull
    private BigDecimal currentBalance;
    @OneToMany(mappedBy = "account")
    private Set<Transaction>transactionSet;
    @ManyToMany(mappedBy = "customerAccounts")
    private Set<Customer>accountCustomers;

    public Account() {
        this.transactionSet = new HashSet<>();
        this.accountCustomers = new HashSet<>();
    }

    public Account(String accountName, String openDate, double startBalance) {
        this.transactionSet = new HashSet<>();
        this.accountCustomers = new HashSet<>();
        this.accountName = accountName;
        this.openDate = openDate;
        this.startBalance = new BigDecimal(startBalance);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public BigDecimal getStartBalance() {
        return startBalance;
    }

    public void setStartBalance(BigDecimal startBalance) {
        this.startBalance = startBalance;
    }

    public void setStartBalance(double startBalance){
        this.startBalance = new BigDecimal(startBalance);
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public void  setCurrentBalance(double startBalance){
        this.currentBalance = new BigDecimal(startBalance);
    }

    public Set<Transaction> getTransactionSet() {
        return transactionSet;
    }

    public void setTransactionSet(Set<Transaction> transactionSet) {
        this.transactionSet = transactionSet;
    }

    public Set<Customer> getAccountCustomers() {
        return accountCustomers;
    }

    public void setAccountCustomers(Set<Customer> accountCustomers) {
        this.accountCustomers = accountCustomers;
    }

    public void addTransactions(Transaction transaction){
        this.transactionSet.add(transaction);
    }

    public void addCustomer(Customer customer){
        accountCustomers.add(customer);
    }

}
