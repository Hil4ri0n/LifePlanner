package com.hil4rion.lifeplanner.service;

import com.hil4rion.lifeplanner.mapper.ExpenseMapper;
import com.hil4rion.lifeplanner.repository.ExpenseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ExpenseServiceTest {

    @Mock
    private ExpenseRepository expenseRepository;

    private ExpenseMapper expenseMapper = Mappers.getMapper(ExpenseMapper.class);

    private ExpenseService expenseService;

    @BeforeEach
    void setUp() {
       expenseService = new ExpenseService(expenseRepository, expenseMapper);

    }
}