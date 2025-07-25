package com.watsoo.expense.service;

import com.watsoo.expense.dto.Response;

public interface DashboardService {


	Response<?> getDashboardSummary(Long userId, Long walletId, String month);

}
