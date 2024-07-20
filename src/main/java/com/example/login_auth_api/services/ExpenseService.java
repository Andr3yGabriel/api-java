package com.example.login_auth_api.services;

import com.example.login_auth_api.Domain.expense.Expense;
import com.example.login_auth_api.Domain.user.User;
import com.example.login_auth_api.dto.ExpenseSummary;
import com.example.login_auth_api.repositories.ExpenseRepository;
import com.example.login_auth_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    public List<ExpenseSummary> getAllExpenses() {
        User currentUser = getAuthenticatedUser();
        return expenseRepository.findByUserId(currentUser.getId()).stream()
                .map(expense -> new ExpenseSummary(expense.getId(), expense.getName(), expense.getAmount()))
                .collect(Collectors.toList());
    }

    public Optional<Expense> getExpenseById(String expenseId) {
        User currentUser = getAuthenticatedUser();
        return expenseRepository.findById(expenseId)
                .filter(expense -> expense.getUser().getId().equals(currentUser.getId()));
    }

    public Expense createExpense(String name, BigDecimal amount, Expense.ExpenseType type, Expense.PaymentType paymentType) {
        User currentUser = getAuthenticatedUser();
        Expense newExpense = new Expense();
        newExpense.setName(name);
        newExpense.setAmount(amount);
        newExpense.setType(type);
        newExpense.setPaymentType(paymentType);
        newExpense.setUser(currentUser);
        return expenseRepository.save(newExpense);
    }

    public Optional<Expense> updateExpense(String expenseId, String newName, BigDecimal newAmount, Expense.ExpenseType newType, Expense.PaymentType newPaymentType) {
        User currentUser = getAuthenticatedUser();
        Optional<Expense> optionalExpense = expenseRepository.findById(expenseId)
                .filter(expense -> expense.getUser().getId().equals(currentUser.getId()));

        if (optionalExpense.isPresent()) {
            Expense expense = optionalExpense.get();
            expense.setName(newName);
            expense.setAmount(newAmount);
            expense.setType(newType);
            expense.setPaymentType(newPaymentType);
            return Optional.of(expenseRepository.save(expense));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteExpense(String expenseId) {
        User currentUser = getAuthenticatedUser();
        Optional<Expense> optionalExpense = expenseRepository.findById(expenseId)
                .filter(expense -> expense.getUser().getId().equals(currentUser.getId()));

        if (optionalExpense.isPresent()) {
            expenseRepository.delete(optionalExpense.get());
            return true;
        } else {
            return false;
        }
    }

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}
