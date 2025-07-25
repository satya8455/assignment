package com.watsoo.expense.service;

public interface EmailService {
	public void sendBudgetExceededMail(String email, String name, String categoryName);
}
