package com.catalog.catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.catalog.catalog.dto.CategoryDto;
import com.catalog.catalog.dto.Response;
import com.catalog.catalog.service.CategoryService;

@RestController
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@PostMapping("/category/registration")
	public ResponseEntity<?> BookRegistration(@RequestBody CategoryDto registrationDto) {
		Response<Object> response = categoryService.addCategory(registrationDto);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getResponseCode()));
	}
	
	@GetMapping("/get/all/category")
	public ResponseEntity<?> getAllCategory() {
		Response<?> response = categoryService.getAllCategory();
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getResponseCode()));
	}
}
