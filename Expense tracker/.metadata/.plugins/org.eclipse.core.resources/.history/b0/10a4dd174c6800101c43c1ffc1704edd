package com.watsoo.expense.entity;

import com.watsoo.expense.dto.CategoryDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "expense_category")
public class ExpenseCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "user_id")
    private Long userId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public ExpenseCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExpenseCategory(Long id, String name, Long userId) {
		super();
		this.id = id;
		this.name = name;
		this.userId = userId;
	}

	public CategoryDto convertToDto() {
		// TODO Auto-generated method stub
		return new CategoryDto(id,this.name!=null?this.name:null,this.userId!=null?this.userId:null);
	} 
    
    
    
}
