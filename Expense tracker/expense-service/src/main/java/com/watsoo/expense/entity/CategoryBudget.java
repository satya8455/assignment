package com.watsoo.expense.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "category_budget")
public class CategoryBudget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "expense_category_id")
    private ExpenseCategory expenseCategory;

    @Column(name = "month") 
    private String month;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "is_exceeded")
    private Boolean isExceeded ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ExpenseCategory getExpenseCategory() {
		return expenseCategory;
	}

	public void setExpenseCategory(ExpenseCategory expenseCategory) {
		this.expenseCategory = expenseCategory;
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

	public CategoryBudget() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoryBudget(Long id, ExpenseCategory expenseCategory, String month, Double amount, Long userId) {
		super();
		this.id = id;
		this.expenseCategory = expenseCategory;
		this.month = month;
		this.amount = amount;
		this.userId = userId;
	}

	public CategoryBudget(Long id, ExpenseCategory expenseCategory, String month, Double amount, Long userId,
			Boolean isExceeded) {
		super();
		this.id = id;
		this.expenseCategory = expenseCategory;
		this.month = month;
		this.amount = amount;
		this.userId = userId;
		this.isExceeded = isExceeded;
	} 
    
    
}
