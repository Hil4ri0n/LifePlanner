package com.hil4rion.lifeplanner.service;

import com.hil4rion.lifeplanner.dto.ExpenseDto;
import com.hil4rion.lifeplanner.mapper.ExpenseMapper;
import com.hil4rion.lifeplanner.model.Expense;
import com.hil4rion.lifeplanner.repository.ExpenseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;

    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public Expense getExpense(UUID id) {
        return expenseRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Expense updateExpense(ExpenseDto expenseDto, UUID id) {
        Expense expense = getExpense(id);
        expenseMapper.patch(expenseDto, expense);
        return expense;
    }

    public void deleteExpense(UUID id) {
        expenseRepository.deleteById(id);
    }
}
