package com.eta.controller;

import com.eta.model.Expense;
import com.eta.model.ExpenseLite;
import com.eta.service.ExpenseService;
//import com.eta.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    /*@Autowired
    private UserService userService;*/
/*
    @PostMapping()
    public ResponseEntity<Expense> createExpense(@Valid @RequestBody Expense expense) throws URISyntaxException {

        Expense newExpense = expenseService.addExpense(expense);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newExpense.getExpenseId()).toUri();
        return ResponseEntity.created(location).body(newExpense);
    }
    */

    @GetMapping()
    public ResponseEntity<List<ExpenseLite>> getAllExpenseLites() {
        List<ExpenseLite> expenseLite = expenseService.getExpenseLites();
        if(expenseLite.size()==0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(expenseLite);
    }

    @GetMapping("/{expenseId}")
    public ResponseEntity<ExpenseLite> getExpenseByExpenseId(@PathVariable Long expenseId) {
        ExpenseLite expenseLite = expenseService.getExpenseLite(expenseId);
        if (expenseLite == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(expenseLite);
    }

    @PostMapping
    public ResponseEntity<ExpenseLite> createExpense(@Valid @RequestBody Expense expense) throws URISyntaxException {
        ExpenseLite newExpense = expenseService.createExpense(expense);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newExpense.expenseId()).toUri();
        return ResponseEntity.created(location).body(newExpense);
    }

    @PutMapping("/{expenseId}")
    public ResponseEntity<ExpenseLite> updateExpense(@PathVariable Long expenseId,  @Valid @RequestBody Expense expense) {
        ExpenseLite updatedExpense = expenseService.updateExpense(expenseId,expense);
        return ResponseEntity.ok(updatedExpense);
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<Expense> deleteExpense(@PathVariable Long expenseId) {
        if (expenseService.deleteExpense(expenseId))
            return ResponseEntity.noContent().build();
        else {
            return ResponseEntity.notFound().build();
        }
    }
}

