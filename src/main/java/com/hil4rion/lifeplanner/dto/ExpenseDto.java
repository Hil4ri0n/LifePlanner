package com.hil4rion.lifeplanner.dto;

import com.hil4rion.lifeplanner.model.ExpenseCategory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

public record ExpenseDto(
        String name,
        ExpenseCategory expenseCategory,
        BigDecimal amount,
        Currency currency,
        LocalDate date
) {
}
