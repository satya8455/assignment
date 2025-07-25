package com.catalog.catalog.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.catalog.catalog.dto.Response;
import com.catalog.catalog.dto.UserDto;

@FeignClient(name = "borrow-service")
public interface BorrowClient {
	@GetMapping("/get/user/by/{id}")
	Response<UserDto> getUsersById(@PathVariable("id") Long id);

}
