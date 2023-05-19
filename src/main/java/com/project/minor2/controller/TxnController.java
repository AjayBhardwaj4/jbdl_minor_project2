package com.project.minor2.controller;

import com.project.minor2.exception.TxnServiceException;
import com.project.minor2.service.TxnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TxnController {
    @Autowired
    TxnService txnService;

    @PostMapping("/transaction/issue")
    public String issueTxn(@RequestParam("studentId") int studentId, @RequestParam("bookId") int bookId) throws TxnServiceException {
        return txnService.issueTxn(studentId, bookId);
    }

    @PostMapping("/transaction/return")
    public String returnTxn(@RequestParam("studentId") int studentId, @RequestParam("bookId") int bookId) throws TxnServiceException {
        return txnService.returnTxn(studentId, bookId);
    }
}
