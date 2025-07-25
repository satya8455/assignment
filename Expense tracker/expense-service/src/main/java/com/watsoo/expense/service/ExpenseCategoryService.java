package com.watsoo.expense.service;

import com.watsoo.expense.dto.CategoryBudgetDto;
import com.watsoo.expense.dto.CategoryDto;
import com.watsoo.expense.dto.Response;

public interface ExpenseCategoryService {

	Response<?> createExpenseCategory(CategoryDto categoryDto);

	Response<?> getAllExpenseCategory(Long userId);

	Response<?> createMonthlyBudget(CategoryBudgetDto categoryBudgetDto);

}
