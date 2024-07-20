package com.example.login_auth_api.controllers;

import com.example.login_auth_api.Domain.expense.Expense;
import com.example.login_auth_api.dto.CreateUpdateExpenseRequest;
import com.example.login_auth_api.dto.ExpenseSummary;
import com.example.login_auth_api.services.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<List<ExpenseSummary>> getAllExpenses() {
        List<ExpenseSummary> expenses = expenseService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable String id) {
        Optional<Expense> expense = expenseService.getExpenseById(id);
        return expense.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Expense> createExpense(@Valid @RequestBody CreateUpdateExpenseRequest request) {
        Expense newExpense = expenseService.createExpense(
                request.name(),
                request.amount(),
                request.type(),
                request.paymentType()
        );
        return ResponseEntity.ok(newExpense);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable String id, @Valid @RequestBody CreateUpdateExpenseRequest request) {
        Optional<Expense> updatedExpense = expenseService.updateExpense(
                id,
                request.name(),
                request.amount(),
                request.type(),
                request.paymentType()
        );
        return updatedExpense.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable String id) {
        boolean deleted = expenseService.deleteExpense(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
