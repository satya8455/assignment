package com.watsoo.wallet.service;

import java.util.Date;

import com.watsoo.wallet.dto.IncomeTransactionDto;
import com.watsoo.wallet.dto.Response;

public interface IncomeTransactionService {

	Response<?> createTransaction(IncomeTransactionDto incomeTransactionDto);
	
	public Response<?> getAllTransactions(Long userId,Long walletId, String startDate, String endDate); 
	}
