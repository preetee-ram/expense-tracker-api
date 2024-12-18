package com.eta.model;

import java.time.LocalDate;

public record ExpenseLite(Long expenseId, Category category, String description,
                          LocalDate date, Double amount,PaymentMode paymentMode, Long userId ) {
}
