package com.eta.repository;

import com.eta.model.Category;
import com.eta.model.Expense;
import com.eta.model.ExpenseLite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserUserId(Long userId);
    List<Expense> getExpensesByCategory(Category category);

    @Query("SELECT new com.eta.model.ExpenseLite(e.expenseId, e.category," +
            "e.description,e.date, e.amount, e.paymentMode, u.userId) " +
            "FROM Expense e JOIN e.user u WHERE e.expenseId = :expenseId")
    ExpenseLite getExpenseLiteById(Long expenseId);

    @Query("SELECT new com.eta.model.ExpenseLite(e.expenseId, e.category, " +
            "e.description, e.date, e.amount, e.paymentMode, u.userId)"+
            " FROM Expense e JOIN e.user u")
    List<ExpenseLite> getAllExpenseLite();

}
