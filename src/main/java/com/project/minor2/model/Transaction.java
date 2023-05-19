package com.project.minor2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String externalTxnId;

    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;

    private double payment;

    @ManyToOne
    @JoinColumn
    private Book book;

    @ManyToOne
    @JoinColumn
    private Student student;

    @CreationTimestamp
    private Date transactionDate;
}
