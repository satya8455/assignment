package com.watsoo.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.watsoo.wallet.dto.LoginRequest;
import com.watsoo.wallet.dto.Response;
import com.watsoo.wallet.service.UserService;
import com.watsoo.wallet.service.ValidationService;

@RestController
public class LoginController {
	@Autowired
	private ValidationService validationService;
	
	@Autowired
	private UserService userService;
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest  loginRequest) {
		Response<?> validationResponse = validationService.checkForLoginPayload(loginRequest);
		if (validationResponse.getResponseCode() == HttpStatus.OK.value()) {
			Response<Object> response = userService.generateToken(loginRequest);
			return new ResponseEntity<>(response, HttpStatus.valueOf(response.getResponseCode()));
		}
		return new ResponseEntity<>(validationResponse, HttpStatus.valueOf(validationResponse.getResponseCode()));
	}
}
