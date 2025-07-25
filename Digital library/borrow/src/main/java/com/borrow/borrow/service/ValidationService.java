package com.borrow.borrow.service;

import com.borrow.borrow.dto.LoginRequest;
import com.borrow.borrow.dto.RegistrationDto;
import com.borrow.borrow.dto.Response;

public interface ValidationService {

	Response<?> checkForLoginPayload(LoginRequest loginRequest);

	boolean checkMail(String mail);

	boolean checkFullName(String name);

	boolean checkPhoneNo(String phoneNo);

	Response<Object> checkForUserRegistrationPayload(RegistrationDto registrationDto);


}
