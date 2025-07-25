package com.borrow.borrow.dto;

import java.util.Date;

public class BorrowDto {
    private Long userId;
    private Long bookId;
    private Date borrowDate;
    private Date dueDate;
    private Date returnDate;
    private String status;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public Date getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BorrowDto(Long userId, Long bookId, Date borrowDate, Date dueDate, Date returnDate, String status) {
		super();
		this.userId = userId;
		this.bookId = bookId;
		this.borrowDate = borrowDate;
		this.dueDate = dueDate;
		this.returnDate = returnDate;
		this.status = status;
	}
	public BorrowDto() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
