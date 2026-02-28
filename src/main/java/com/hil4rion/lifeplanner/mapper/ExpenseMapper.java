package com.hil4rion.lifeplanner.mapper;

import com.hil4rion.lifeplanner.dto.ExpenseDto;
import com.hil4rion.lifeplanner.model.Expense;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patch(ExpenseDto expenseDto, @MappingTarget Expense expense);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userProfile", ignore = true)
    Expense expenseDtoToExpense(ExpenseDto expenseDto);

    ExpenseDto expenseToDto(Expense expense);
}
