package com.watsoo.expense.dto;

import jakarta.persistence.Column;

public class CategoryBudgetDto {
	private Long id;

	
	private CategoryDto categoryDto;

	private String month;

	private Double amount;

	private Long userId;
	
	private Boolean isExceeded ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CategoryDto getCategoryDto() {
		return categoryDto;
	}

	public void setCategoryDto(CategoryDto categoryDto) {
		this.categoryDto = categoryDto;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	

	public Boolean getIsExceeded() {
		return isExceeded;
	}

	public void setIsExceeded(Boolean isExceeded) {
		this.isExceeded = isExceeded;
	}

	public CategoryBudgetDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoryBudgetDto(Long id, CategoryDto categoryDto, String month, Double amount, Long userId) {
		super();
		this.id = id;
		this.categoryDto = categoryDto;
		this.month = month;
		this.amount = amount;
		this.userId = userId;
	}

	public CategoryBudgetDto(Long id, CategoryDto categoryDto, String month, Double amount, Long userId,
			Boolean isExceeded) {
		super();
		this.id = id;
		this.categoryDto = categoryDto;
		this.month = month;
		this.amount = amount;
		this.userId = userId;
		this.isExceeded = isExceeded;
	}
	
	
	
}
