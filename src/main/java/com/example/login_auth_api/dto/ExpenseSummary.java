package com.example.login_auth_api.dto;

import java.math.BigDecimal;

public record ExpenseSummary(
        String id,
        String name,
        BigDecimal amount
) {}