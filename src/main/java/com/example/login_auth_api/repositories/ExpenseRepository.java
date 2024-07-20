package com.example.login_auth_api.repositories;

import com.example.login_auth_api.Domain.expense.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, String> {
    List<Expense> findByUserId(String userId);
}
