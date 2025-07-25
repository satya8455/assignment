package com.borrow.borrow.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.borrow.borrow.dto.BookDto;
import com.borrow.borrow.dto.Response;

@FeignClient(name = "catalog-service")
public interface CatalogClient {

	@GetMapping("/books/{bookId}")
    Response<BookDto> getBookById(@PathVariable Long bookId);

}
