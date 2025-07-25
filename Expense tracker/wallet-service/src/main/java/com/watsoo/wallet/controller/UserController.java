package com.watsoo.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.watsoo.wallet.dto.RegistrationDto;
import com.watsoo.wallet.dto.Response;
import com.watsoo.wallet.service.UserService;
import com.watsoo.wallet.service.ValidationService;

@RestController
public class UserController {

	@Autowired
	private ValidationService validationService;

	@Autowired
	private UserService userService;

	@PostMapping("user/registration")
	public ResponseEntity<?> userRegistration(@RequestBody RegistrationDto registrationDto) {
		Response<Object> validationResponse = validationService.checkForUserRegistrationPayload(registrationDto);
		if (validationResponse.getResponseCode() == HttpStatus.OK.value()) {
			Response<Object> response = userService.registerUser(registrationDto);
			return new ResponseEntity<>(response, HttpStatus.valueOf(response.getResponseCode()));
		}
		return new ResponseEntity<>(validationResponse, HttpStatus.valueOf(validationResponse.getResponseCode()));
	}

	@PostMapping("/signup/admin")
	public ResponseEntity<?> adminRegistration(@RequestBody RegistrationDto registrationDto) {
		Response<Object> validationResponse = validationService.checkForUserRegistrationPayload(registrationDto);
		if (validationResponse.getResponseCode() == HttpStatus.OK.value()) {
			Response<Object> response = userService.registerAdmin(registrationDto);
			return new ResponseEntity<>(response, HttpStatus.valueOf(response.getResponseCode()));
		}
		return new ResponseEntity<>(validationResponse, HttpStatus.valueOf(validationResponse.getResponseCode()));
	}

	@GetMapping("api/get/all/users")
	public ResponseEntity<?> getAllUsers(@RequestParam(required = false) Long id) {
		Response<?> response = userService.getAllUsers(id);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getResponseCode()));
	}
	@GetMapping("/get/user/by/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Long id) {
		Response<?> response = userService.getUserById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
