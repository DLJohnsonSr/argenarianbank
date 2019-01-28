package com.d2j2.argenarianbank.repositories;

import com.d2j2.argenarianbank.models.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    Iterable<Transaction> findAllByAccount_Id(long id);

    Iterable<Transaction> findAllByAccount_IdOrderByDateAscIdAsc(long acctId);

    Iterable<Transaction> findAllByAccount_IdOrderByTransactionNumberDesc(long acctId);
}
