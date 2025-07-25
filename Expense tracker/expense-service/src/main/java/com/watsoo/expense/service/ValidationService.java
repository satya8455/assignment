package com.watsoo.expense.service;

import com.watsoo.expense.dto.ExpenseDto;
import com.watsoo.expense.dto.Response;

public interface ValidationService {

	Response<?> checkForExpensePayload(ExpenseDto expenseDto);

}
