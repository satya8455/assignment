package com.watsoo.expense.service;

import com.watsoo.expense.dto.ExpenseDto;
import com.watsoo.expense.dto.Response;

public interface ExpenseService {

	Response<?> createExpense(ExpenseDto expenseDto);

	Response<?> getAllExpenses(Long userId, Long walletId, String month);

}
