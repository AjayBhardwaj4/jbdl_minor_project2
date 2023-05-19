package com.project.minor2.service;

import com.project.minor2.exception.TxnServiceException;
import com.project.minor2.model.Book;
import com.project.minor2.model.Student;
import com.project.minor2.model.Transaction;
import com.project.minor2.model.TransactionType;
import com.project.minor2.model.request.BookFilterType;
import com.project.minor2.repository.TxnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TxnService {
    @Autowired
    StudentService studentService;

    @Autowired
    BookService bookService;

    @Autowired
    TxnRepository txnRepository;

    @Value("${book.return.due_date}")
     int MAX_BOOK_RETURN_TIME;

    public String issueTxn(int sId, int bookId) throws TxnServiceException {
        Student student = studentService.findStudentByStudentId(sId);
        if(student == null) {
            throw new TxnServiceException("Student is not present");
        }

        List<Book> bookList = bookService.find(BookFilterType.BOOK_ID, String.valueOf(bookId));
        if(bookList == null || bookList.size() != 1 || bookList.get(0).getStudent() != null) {
            throw new TxnServiceException("Book is not present in the library");
        }

        Transaction transaction = Transaction.builder()
                .externalTxnId(UUID.randomUUID().toString())
                .transactionType(TransactionType.ISSUE)
                .payment(bookList.get(0).getCost())
                .book(bookList.get(0))
                .student(student)
                .build();

        txnRepository.save(transaction);

        bookList.get(0).setStudent(student);
        bookService.createOrUpdate(bookList.get(0));

        return transaction.getExternalTxnId();
    }

    public String returnTxn(int sId, int bookId) throws TxnServiceException{
        Student student = studentService.findStudentByStudentId(sId);
        if(student == null) {
            throw new TxnServiceException("Student is not present");
        }

        List<Book> bookList = bookService.find(BookFilterType.BOOK_ID, String.valueOf(bookId));
        if(bookList == null || bookList.size() != 1) {
            throw new TxnServiceException("Book is not present in the library");
        }

        if(bookList.get(0).getStudent().getId() != sId) {
            throw new TxnServiceException("Book is issued to this student");
        }

        Transaction issueTxn = txnRepository.findTopByBookAndStudentAndTransactionTypeOrderByTransactionDateDesc(bookList.get(0),
                student, TransactionType.ISSUE);

        calculateFine(issueTxn);

        Transaction transaction = Transaction.builder()
                .transactionType(TransactionType.RETURN)
                .externalTxnId(UUID.randomUUID().toString())
                .book(bookList.get(0))
                .student(student)
                .payment(calculateFine(issueTxn))
                .build();

        txnRepository.save(transaction);
        bookList.get(0).setStudent(null);
        bookService.createOrUpdate(bookList.get(0));

        return transaction.getExternalTxnId();
    }

    private double calculateFine(Transaction issueTxn) {
        long issueTime = issueTxn.getTransactionDate().getTime();
        long returnTime = System.currentTimeMillis();

        long diff = returnTime-issueTime;
        long daysPassed = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        if(daysPassed >= MAX_BOOK_RETURN_TIME) {
            return (daysPassed - MAX_BOOK_RETURN_TIME) * 1.0;
        } else {
            return 0.0;
        }
    }
}
