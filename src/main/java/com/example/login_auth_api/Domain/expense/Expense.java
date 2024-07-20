package com.example.login_auth_api.Domain.expense;

import com.example.login_auth_api.Domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "expenses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Amount is mandatory")
    @Min(value = 0, message = "Amount must be positive")
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Type is mandatory")
    private ExpenseType type;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Payment type is mandatory")
    private PaymentType paymentType;

    public enum ExpenseType {
        FOOD, TRANSPORT, ENTERTAINMENT, OTHER
    }

    public enum PaymentType {
        DEBIT, CREDIT, PIX, CASH
    }
}
