package com.watsoo.expense.serviceImpl;

import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.watsoo.expense.dto.DashboardDto;
import com.watsoo.expense.dto.ExpenseDto;
import com.watsoo.expense.dto.IncomeTransactionDto;
import com.watsoo.expense.dto.Response;
import com.watsoo.expense.feignClient.WalletFeignClient;
import com.watsoo.expense.service.DashboardService;
import com.watsoo.expense.service.ExpenseService;
import com.watsoo.expense.util.DateUtil;

@Service
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	private ExpenseService expenseService;
	@Autowired
	private WalletFeignClient walletFeignClient;
	@Override
	public Response<?> getDashboardSummary(Long userId, Long walletId, String month) {
		try {
			if (month == null || month.trim().isEmpty()) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Month required (yyyy/MM)", null);
			}
			try {
				YearMonth.parse(month, DateTimeFormatter.ofPattern("yyyy/MM"));
			} catch (DateTimeParseException e) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Invalid month format. Use yyyy/MM", null);
			}
			Date[] range = DateUtil.getMonthRange(month);			
			Date startDate = range[0];
			Date endDate = range[1];
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

			Response<?> expensesResp = expenseService.getAllExpenses(userId, walletId, month);
			if (expensesResp.getResponseCode() != HttpStatus.OK.value()) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Could not fetch expenses", null);
			}
			List<ExpenseDto> expenses = (List<ExpenseDto>) expensesResp.getData();
			
			Map<String, Double> expenseByCategory = expenses.stream().collect(Collectors
					.groupingBy(e -> e.getCategoryDto().getName(), Collectors.summingDouble(ExpenseDto::getAmount)));

			double totalSpent = expenses.stream().mapToDouble(ExpenseDto::getAmount).sum();
			Response<List<IncomeTransactionDto>> incomeResp;
			List<IncomeTransactionDto> incomes;
			incomeResp = walletFeignClient.getAllIncomeTransactions(userId, walletId, formatter.format(startDate),
					formatter.format(endDate));

			if (incomeResp == null || incomeResp.getData() == null) {
				return incomeResp;
			}
			incomes = incomeResp.getData();
			Map<String, Double> incomeByCategory = incomes.stream().collect(Collectors.groupingBy(
					i -> i.getWalletDto().getName(), Collectors.summingDouble(IncomeTransactionDto::getAmount)));

			double totalIncome = incomes.stream().mapToDouble(IncomeTransactionDto::getAmount).sum();

			DashboardDto dashboardDto = new DashboardDto();
			dashboardDto.setMonth(month);
			dashboardDto.setUserId(userId);
			dashboardDto.setWalletId(walletId);
			dashboardDto.setExpenses(expenses);
			dashboardDto.setIncomeTransactions(incomes);
			dashboardDto.setTotalIncome(totalIncome);
			dashboardDto.setTotalSpent(totalSpent);
			dashboardDto.setExpenseByCategory(expenseByCategory);
			dashboardDto.setIncomeByCategory(incomeByCategory);

			return new Response<>(HttpStatus.OK.value(), "Dashboard summary fetched", dashboardDto);

		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Something went wrong", null);
		}
	}



}
