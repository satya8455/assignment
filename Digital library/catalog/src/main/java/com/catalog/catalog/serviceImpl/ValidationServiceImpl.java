package com.catalog.catalog.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.catalog.catalog.dto.BookDto;
import com.catalog.catalog.dto.Response;
import com.catalog.catalog.service.ValidationService;


@Service
public class ValidationServiceImpl implements ValidationService {

	@Override
	public Response<Object> checkForBookRegistrationPayload(BookDto registrationDto) {
		if (registrationDto.getAuthor() == null || registrationDto.getAuthor().isEmpty()) {
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Author is required.", null);
		}
		if (registrationDto.getTitle() == null || registrationDto.getTitle().isEmpty()) {
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "title is required.", null);
		}
		if (registrationDto.getCategory() == null || registrationDto.getCategory().getId()==null) {
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "category is required.", null);
		}
		if (registrationDto.getTotalCopies() == null) {
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Totalcopies is required.", null);
		}
		return new Response<>(HttpStatus.OK.value(), "OK", null);
	}

//	@Override
//	public Response<Object> checkForLoginPayload(LoginRequest loginRequest) {
//		if (loginRequest.getUsername() == null || loginRequest.getUsername().isEmpty()) {
//			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Please provide the email.", null);
//		}
//		if (loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
//			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Please provide the passowrd.", null);
//		}
//		return new Response<>(HttpStatus.OK.value(), "OK", null);
//	}
//
//	@Override
//	public Response<Object> checkForForgetPasswordPayload(LoginRequest loginRequest) {
//		if (loginRequest.getUsername().isEmpty()) {
//			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Please provide a valid email.", null);
//		}
//		if (loginRequest.getNewPassword() == null) {
//			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Invalid details.", null);
//		}
//		return new Response<>(HttpStatus.OK.value(), "OK", null);
//	}

//	@Override
//	public Response<Object> checkForBookRegistrationPayload(BookDto registrationDto) {
//		if (registrationDto.getEmail() == null || registrationDto.getEmail().isEmpty()) {
//			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Email is required.", null);
//		}
//		if (registrationDto.getFirstName() == null || registrationDto.getFirstName().isEmpty()) {
//			return new Response<>(HttpStatus.BAD_REQUEST.value(), "First name is required.", null);
//		}
//		return new Response<>(HttpStatus.OK.value(), "OK", null);
//	}

}
