package com.catalog.catalog.dto;

public class SearchDto {

	private int pageNo = 0;
	private int pageSize = 10;


	private String searchField;
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
//	public String getTitle() {
//		return title;
//	}
//	public void setTitle(String title) {
//		this.title = title;
//	}
//	public String getAuthor() {
//		return author;
//	}
//	public void setAuthor(String author) {
//		this.author = author;
//	}
//	public String getCategory() {
//		return category;
//	}
//	public void setCategory(String category) {
//		this.category = category;
//	}
	public SearchDto(int pageNo, int pageSize, String searchField) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
//		this.title = title;
//		this.author = author;
//		this.category = category;
	}
	public SearchDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getSearchField() {
		return searchField;
	}
	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	
	
}
