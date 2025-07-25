package com.catalog.catalog.service;

import com.catalog.catalog.dto.BookDto;
import com.catalog.catalog.dto.Response;

public interface ValidationService {

//	Response<Object> checkForLoginPayload(LoginRequest loginRequest);

//	Response<Object> checkForForgetPasswordPayload(LoginRequest loginRequest);

	Response<Object> checkForBookRegistrationPayload(BookDto registrationDto);

	
}
