package com.borrow.borrow.service;

import com.borrow.borrow.dto.LoginRequest;
import com.borrow.borrow.dto.RegistrationDto;
import com.borrow.borrow.dto.Response;

public interface UserService {

	Response<Object> generateToken(LoginRequest loginRequest);

	Response<Object> registerUser(RegistrationDto registrationDto);

	Response<Object> registerAdmin(RegistrationDto registrationDto);

	Response<?> getAllUsers(Long id);

	Response<?> getUserById(Long id);

}
