package com.eta.service;

import com.eta.commons.exceptions.ExpenseNotFoundException;
import com.eta.commons.exceptions.UserNotFoundException;
import com.eta.model.*;
import com.eta.repository.ExpenseRepository;
import com.eta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private UserRepository userRepository;

    public ExpenseLite createExpense(Expense expense) {
        /*User user = userRepository.findById(expense.getUser().getUserId())
                .orElseThrow(UserNotFoundException::new);*/

        Expense expenseToSave = new Expense();
        //expenseToSave.setUser(expense.getUser().getUserId());
        expenseToSave.setCategory(expense.getCategory());
        expenseToSave.setAmount(expense.getAmount());
        expenseToSave.setDescription(expense.getDescription());
        expenseToSave.setDate(expense.getDate());
        expenseToSave.setPaymentMode(expense.getPaymentMode());

        Expense savedExpense = expenseRepository.save(expenseToSave);
        System.out.println(savedExpense);

        return expenseRepository.getExpenseLiteById(savedExpense.getExpenseId());
    }

    public ExpenseLite updateExpense(Long expenseId, Expense expense) {
        Expense expenseToUpdate = expenseRepository.findById(expenseId)
                .orElseThrow(ExpenseNotFoundException::new);

        expenseToUpdate.setDescription(expense.getDescription());
        expenseToUpdate.setCategory(expense.getCategory());
        expenseToUpdate.setPaymentMode(expense.getPaymentMode());
        expenseToUpdate.setAmount(expense.getAmount());
        expenseToUpdate.setDate(expense.getDate());
        expenseToUpdate.setUser(expense.getUser());

        Expense updatedExpense = expenseRepository.save(expenseToUpdate);

        return expenseRepository.getExpenseLiteById(updatedExpense.getExpenseId());
    }
    public boolean deleteExpense(Long id) {
       Expense expenseToDelete = expenseRepository.findById(id)
               .orElseThrow(ExpenseNotFoundException::new);
       if(expenseToDelete.getExpenseId() == id) {
           expenseRepository.delete(expenseToDelete);
           return true;
       }
       return false;
    }
    public List<Expense> getExpensesByCategory(Category category) {
        return expenseRepository.getExpensesByCategory(category);
    }

    public ExpenseLite getExpenseLite(Long expenseId) {
        return expenseRepository.getExpenseLiteById(expenseId);
    }

    public List<ExpenseLite> getExpenseLites() {
        return expenseRepository.getAllExpenseLite();
    }
}
