package com.project.minor2.repository;

import com.project.minor2.model.Book;
import com.project.minor2.model.Student;
import com.project.minor2.model.Transaction;
import com.project.minor2.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TxnRepository extends JpaRepository<Transaction, Integer> {
    Transaction findTopByBookAndStudentAndTransactionTypeOrderByTransactionDateDesc(Book book, Student student,
                                                                                          TransactionType transactionType);
}
