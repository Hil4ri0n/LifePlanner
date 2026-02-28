package com.hil4rion.lifeplanner.repository;

import com.hil4rion.lifeplanner.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExpenseRepository extends JpaRepository<Expense, UUID> {
}
