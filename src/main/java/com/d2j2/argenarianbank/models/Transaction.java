package com.d2j2.argenarianbank.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private boolean isStartBalance;
    private int transactionNumber;
    private String stringDate;
    private LocalDate date;
    private String description;
    private String IncomeOrExpense;
    private String creditOrDebit;
    private double amount;
    private double transactionBalance;
    private boolean posted;
    @ManyToOne
    private Account account;

    public Transaction() {
        this.stringDate = stringDate;
        this.description = description;
        this.creditOrDebit = creditOrDebit;
        this.amount = amount;
        this.posted = posted;
    }

    public Transaction(int transactionNumber, String stringDate, String description, String creditOrDebit, double amount,boolean posted, Account account) {
        this.transactionNumber = transactionNumber;
        this.stringDate = stringDate;
        this.description = description;
        this.creditOrDebit = creditOrDebit;
        this.amount = amount;
        this.posted = posted;
        this.account = account;
    }

    public Transaction(boolean isStartBalance, int transactionNumber, String stringDate, String description, String creditOrDebit, double amount, boolean posted, Account account) {
        this.isStartBalance = isStartBalance;
        this.transactionNumber = transactionNumber;
        this.stringDate = stringDate;
        this.description = description;
        this.creditOrDebit = creditOrDebit;
        this.amount = amount;
        this.posted = posted;
        this.account = account;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isStartBalance() {
        return isStartBalance;
    }

    public void setStartBalance(boolean startBalance) {
        isStartBalance = startBalance;
    }

    public int getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(int transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public String getStringDate() {
        return stringDate;
    }

    public void setStringDate(String stringDate) {
        this.stringDate = stringDate;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIncomeOrExpense() {
        return IncomeOrExpense;
    }

    public void setIncomeOrExpense(String incomeOrExpense) {
        IncomeOrExpense = incomeOrExpense;
    }

    public String getCreditOrDebit() {
        return creditOrDebit;
    }

    public void setCreditOrDebit(String creditOrDebit) {
        this.creditOrDebit = creditOrDebit;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTransactionBalance() {
        return transactionBalance;
    }

    public void setTransactionBalance(double transactionBalance) {
        this.transactionBalance = transactionBalance;
    }

    public boolean isPosted() {
        return posted;
    }

    public void setPosted(boolean posted) {
        this.posted = posted;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public LocalDate convertStringDate(String date){
        DateTimeFormatter stringDateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate localDate = LocalDate.parse(date, stringDateFormat);
        return localDate;
    }
}
