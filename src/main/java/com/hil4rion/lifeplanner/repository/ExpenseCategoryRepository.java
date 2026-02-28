package com.hil4rion.lifeplanner.repository;

import com.hil4rion.lifeplanner.model.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, String> {
}
