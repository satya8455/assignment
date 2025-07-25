package com.catalog.catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.catalog.catalog.dto.BookDto;
import com.catalog.catalog.dto.Response;
import com.catalog.catalog.dto.SearchDto;
import com.catalog.catalog.service.BookService;
import com.catalog.catalog.service.ValidationService;

@RestController
public class BookController {

	@Autowired
	private ValidationService validationService;
	@Autowired
	private BookService bookService;

	@PostMapping("/book/registration")
	public ResponseEntity<?> BookRegistration(@RequestBody BookDto registrationDto) {
		Response<Object> validationResponse = validationService.checkForBookRegistrationPayload(registrationDto);
		if (validationResponse.getResponseCode() == HttpStatus.OK.value()) {
			Response<?> response = bookService.addBook(registrationDto);
			return new ResponseEntity<>(response, HttpStatus.valueOf(response.getResponseCode()));
		}
		return new ResponseEntity<>(validationResponse, HttpStatus.valueOf(validationResponse.getResponseCode()));
	}
	
	@PostMapping("/search")
	public ResponseEntity<?> searchUsers(@RequestBody SearchDto searchDTO) {
		 Response<?> searchUsers = bookService.getAllBooks(searchDTO);
			return new ResponseEntity<>(searchUsers, HttpStatus.valueOf(searchUsers.getResponseCode()));
	}
	@GetMapping("/books/{bookId}")
	public ResponseEntity<?> getBookById(@PathVariable Long bookId) {
		Response<?> response = bookService.getBookById(bookId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
