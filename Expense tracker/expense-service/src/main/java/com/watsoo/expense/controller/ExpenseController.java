package com.watsoo.expense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.watsoo.expense.dto.ExpenseDto;
import com.watsoo.expense.dto.Response;
import com.watsoo.expense.service.ExpenseService;
import com.watsoo.expense.service.ValidationService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "💰 Expense Management", description = "APIs for managing expenses")
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;

	@Autowired
	private ValidationService validationService;

	@PostMapping("create/expense")
	public ResponseEntity<?> createExpense(@RequestBody ExpenseDto expenseDto) {
		Response<?> validationResponse = validationService.checkForExpensePayload(expenseDto);
		if (validationResponse.getResponseCode() == HttpStatus.OK.value()) {
			Response<?> response = expenseService.createExpense(expenseDto);
			return new ResponseEntity<>(response, HttpStatus.valueOf(response.getResponseCode()));
		}
		return new ResponseEntity<>(validationResponse, HttpStatus.valueOf(validationResponse.getResponseCode()));
	}

	@GetMapping("/expenses/grouped/{userId}")
	public ResponseEntity<?> getAllExpenses(@PathVariable Long userId,
											@RequestParam(required = false) Long walletId,
											@RequestParam(required = false) String month){
		Response<?> response = expenseService.getAllExpenses(userId,walletId,month);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getResponseCode()));
	}
	

	

}
