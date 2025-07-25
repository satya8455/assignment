package com.catalog.catalog.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.catalog.catalog.dto.CategoryDto;
import com.catalog.catalog.dto.Response;
import com.catalog.catalog.entity.Category;
import com.catalog.catalog.repository.CategoryRepository;
import com.catalog.catalog.service.CategoryService;

@Service
public class CategoryServiceImpl  implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	@Override
	public Response<Object> addCategory(CategoryDto dto) {
		 if (dto == null || dto.getName() == null || dto.getName().trim().isEmpty()) {
	            return new Response<>(HttpStatus.BAD_REQUEST.value(), "Category name is required", null);
	        }

	        if (dto.getId() == null) {
	            Optional<Category> existing = categoryRepository.findByNameIgnoreCase(dto.getName().trim());
	            if (existing.isPresent()) {
	                return new Response<>(HttpStatus.BAD_REQUEST.value(), "Category already exists", null);
	            }

	            Category newCategory = new Category();
	            newCategory.setName(dto.getName());
	            categoryRepository.save(newCategory);

	            return new Response<>(HttpStatus.OK.value(), "Category created successfully", null);

	        } else {
	            Optional<Category> existing = categoryRepository.findById(dto.getId());
	            if (existing.isEmpty()) {
	                return new Response<>(HttpStatus.BAD_REQUEST.value(), "Invalid Category ID", null);
	            }
	            Category category = existing.get();
	            category.setName(dto.getName());
	            categoryRepository.save(category);
	            return new Response<>(HttpStatus.OK.value(), "Category updated successfully", null);
	        }
	    }
	@Override
	public Response<?> getAllCategory() {
		try {
			List<Category> allCategory = categoryRepository.findAll();
			if(allCategory.isEmpty()) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(),"No category present",null); 
			}
			List<CategoryDto> categoryDtos = allCategory.stream().filter(e->e!=null).map(Category::convertToDto).collect(Collectors.toList());
			return new Response<>(HttpStatus.OK.value(),"All category fetched",categoryDtos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Response<>(HttpStatus.BAD_REQUEST.value(),"Something went wrong",null);

		} 
	}
	

}
