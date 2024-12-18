package com.eta.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDate;

//@Data
@Getter
@Setter
//@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseId;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String description;
    private LocalDate date;
    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    //@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    /*public Expense(double amount, Category category, LocalDate now, String s, PaymentMode paymentMode, Long i) {
        this.amount = amount;
        this.category = category;
        this.date = now;
        this.paymentMode = paymentMode;
        this.description = s;
    }*/
}
