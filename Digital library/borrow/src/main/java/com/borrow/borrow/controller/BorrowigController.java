package com.borrow.borrow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.borrow.borrow.dto.BorrowDto;
import com.borrow.borrow.dto.Response;
import com.borrow.borrow.service.BorrowingService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
public class BorrowigController {

	@Autowired
	private BorrowingService borrowingService;
	@Operation(summary = "BORROW-BOOK ")
	@PostMapping("/book/borrow")
	public ResponseEntity<?> borrowBook(@RequestBody BorrowDto borrowDto) {
		Response<?> response = borrowingService.borrowBook(borrowDto);
		
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getResponseCode()));
	}
	
	@PostMapping("/return/borrow/{borrowId}")
	public ResponseEntity<?> returnBook(@PathVariable Long borrowId) {
		Response<?> response = borrowingService.returnBook(borrowId);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getResponseCode()));
	}
}
