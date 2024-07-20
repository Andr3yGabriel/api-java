package com.example.login_auth_api.dto;

import com.example.login_auth_api.Domain.expense.Expense;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateUpdateExpenseRequest(
        @NotBlank(message = "Name is mandatory")
        String name,
        @NotNull(message = "Amount is mandatory")
        @Min(value = 0, message = "Amount must be positive")
        BigDecimal amount,
        @NotNull(message = "Type is mandatory")
        Expense.ExpenseType type,
        @NotNull(message = "Payment type is mandatory")
        Expense.PaymentType paymentType
) {}