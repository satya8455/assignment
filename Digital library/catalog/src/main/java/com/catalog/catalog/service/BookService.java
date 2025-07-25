package com.catalog.catalog.service;

import com.catalog.catalog.dto.BookDto;
import com.catalog.catalog.dto.Response;
import com.catalog.catalog.dto.SearchDto;

public interface BookService {
	 Response<?> addBook(BookDto dto);
	 Response<?> getAllBooks(SearchDto searchDto);
	 Response<?>getBookById(Long bookId);
	  
}
