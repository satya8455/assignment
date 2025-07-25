package com.catalog.catalog.service;

import com.catalog.catalog.dto.CategoryDto;
import com.catalog.catalog.dto.Response;

public interface CategoryService {
	public Response<Object> addCategory(CategoryDto dto);

	public Response<?> getAllCategory();
}
