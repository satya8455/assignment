package com.watsoo.expense.dto;


import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class DashboardDto {
    
    private Double totalSpent;
    private Double totalIncome;
    private String month;
    private Long userId;
    private Long walletId;
    private Boolean isExceed;

    private List<IncomeTransactionDto> incomeTransactions;
    private List<ExpenseDto> expenses;
    private Map<String, Double> expenseByCategory;
    private Map<String, Double> incomeByCategory;
    private Map<String, Double> categorySummary;


    public Double getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(Double totalSpent) {
        this.totalSpent = totalSpent;
    }

    public Double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public Boolean getIsExceed() {
        return isExceed;
    }

    public void setIsExceed(Boolean isExceed) {
        this.isExceed = isExceed;
    }

    public List<IncomeTransactionDto> getIncomeTransactions() {
        return incomeTransactions;
    }

    public void setIncomeTransactions(List<IncomeTransactionDto> incomeTransactions) {
        this.incomeTransactions = incomeTransactions;
    }

    public List<ExpenseDto> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<ExpenseDto> expenses) {
        this.expenses = expenses;
    }

    public Map<String, Double> getCategorySummary() {
        return categorySummary;
    }

    public void setCategorySummary(Map<String, Double> categorySummary) {
        this.categorySummary = categorySummary;
    }

    // ====== CONSTRUCTORS ======

    public DashboardDto() {}

    public Map<String, Double> getExpenseByCategory() {
		return expenseByCategory;
	}

	public void setExpenseByCategory(Map<String, Double> expenseByCategory) {
		this.expenseByCategory = expenseByCategory;
	}

	public Map<String, Double> getIncomeByCategory() {
		return incomeByCategory;
	}

	public void setIncomeByCategory(Map<String, Double> incomeByCategory) {
		this.incomeByCategory = incomeByCategory;
	}

	public DashboardDto(Double totalSpent, Double totalIncome, String month, Long userId, Long walletId,
                        Boolean isExceed, List<IncomeTransactionDto> incomeTransactions,
                        List<ExpenseDto> expenses, Map<String, Double> categorySummary) {
        this.totalSpent = totalSpent;
        this.totalIncome = totalIncome;
        this.month = month;
        this.userId = userId;
        this.walletId = walletId;
        this.isExceed = isExceed;
        this.incomeTransactions = incomeTransactions;
        this.expenses = expenses;
        this.categorySummary = categorySummary;
    }

	public DashboardDto(Double totalSpent, Double totalIncome, String month, Long userId, Long walletId,
			Boolean isExceed, List<IncomeTransactionDto> incomeTransactions, List<ExpenseDto> expenses,
			Map<String, Double> expenseByCategory, Map<String, Double> incomeByCategory,
			Map<String, Double> categorySummary) {
		super();
		this.totalSpent = totalSpent;
		this.totalIncome = totalIncome;
		this.month = month;
		this.userId = userId;
		this.walletId = walletId;
		this.isExceed = isExceed;
		this.incomeTransactions = incomeTransactions;
		this.expenses = expenses;
		this.expenseByCategory = expenseByCategory;
		this.incomeByCategory = incomeByCategory;
		this.categorySummary = categorySummary;
	}
	
}
