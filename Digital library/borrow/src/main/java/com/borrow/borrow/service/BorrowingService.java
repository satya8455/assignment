package com.borrow.borrow.service;

import com.borrow.borrow.dto.BorrowDto;
import com.borrow.borrow.dto.Response;

public interface BorrowingService {

	Response<?>borrowBook(BorrowDto borrowDto);

	Response<?> returnBook(Long borrowId);
}
