package com.watsoo.expense.entity;

import java.util.Date;

import com.watsoo.expense.dto.ExpenseDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="amount")
    private Double amount;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ExpenseCategory category;

    @Column(name = "wallet_id")
    private Long walletId;

    @Column(name = "remarks")
    private String remarks;
    
    @Column(name = "user_id")
    private Long userId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ExpenseCategory getCategory() {
		return category;
	}

	public void setCategory(ExpenseCategory category) {
		this.category = category;
	}

	public Long getWalletId() {
		return walletId;
	}

	public void setWalletId(Long walletId) {
		this.walletId = walletId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Expense() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Expense(Long id, Double amount, Date date, ExpenseCategory category, Long walletId, String remarks,
			Long userId) {
		super();
		this.id = id;
		this.amount = amount;
		this.date = date;
		this.category = category;
		this.walletId = walletId;
		this.remarks = remarks;
		this.userId = userId;
	}

	public ExpenseDto convertToDto() {
		return new ExpenseDto(id,this.amount!=null?this.amount:null,
								this.date!=null?this.date:null,
								this.category!=null?this.category.convertToDto():null,
								null,
								this.walletId!=null?this.walletId:null,
								this.remarks!=null?this.remarks:null,
								this.userId!=null?this.userId:null);
	}

    
    

}

