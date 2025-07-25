package com.watsoo.expense.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.watsoo.expense.dto.ExpenseDto;
import com.watsoo.expense.dto.Response;
import com.watsoo.expense.service.ValidationService;

@Service
public class ValidationServiceImpl implements ValidationService {

	@Override
	public Response<?> checkForExpensePayload(ExpenseDto expenseDto) {
		if (expenseDto == null) {
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Payload is empty.", null);
		} else if (expenseDto.getAmount() == null || expenseDto.getAmount() <= 0) {
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Amount must be greater than 0 or amount id null",
					null);
		} else if (expenseDto.getCategoryId() == null || expenseDto.getCategoryId() <= 0) {
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Category ID must be valid.", null);
		} else if (expenseDto.getWalletId() == null || expenseDto.getWalletId() <= 0) {
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Wallet ID must be valid.", null);
		} else if (expenseDto.getRemarks() == null || expenseDto.getRemarks().isEmpty()) {
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Remarks required.", null);
		} else if (expenseDto.getUserId() == null) {
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "User id is required.", null);
		} 
		

		return new Response<>(HttpStatus.OK.value(), "OK.", null);
	}

}
