package com.catalog.catalog.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.catalog.catalog.dto.BookDto;
import com.catalog.catalog.dto.Pagination;
import com.catalog.catalog.dto.Response;
import com.catalog.catalog.dto.SearchDto;
import com.catalog.catalog.dto.UserDto;
import com.catalog.catalog.entity.Book;
import com.catalog.catalog.entity.Category;
import com.catalog.catalog.feignclient.BorrowClient;
import com.catalog.catalog.repository.BookRepository;
import com.catalog.catalog.repository.CategoryRepository;
import com.catalog.catalog.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private BorrowClient borrowClient;

	@Override
	public Response<?> addBook(BookDto dto) {
		try {
			if (dto == null) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Provide Book Detalis.", null);
			}
			Response<UserDto> response = borrowClient.getUsersById(dto.getUserId());
			if (response == null || response.getData() == null) {
				return response;
			}
			if (!"ADMIN".equalsIgnoreCase(response.getData().getRole())) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Only ADMIN can perform this action", null);
			}
			Optional<Category> categoryOptional = categoryRepository.findById(dto.getCategory().getId());
			if (categoryOptional.isEmpty()) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Invalid Category ID.", null);
			}
			Category category = categoryOptional.get();

			if (dto.getId() == null) {
				Optional<Book> existing = bookRepository.existsByTitleAndAuthorAndCategory(
						dto.getTitle().trim().toLowerCase(), dto.getAuthor().trim().toLowerCase(),
						dto.getCategory().getId());
				if (existing != null && existing.isPresent()) {
					return new Response<>(HttpStatus.BAD_REQUEST.value(), "Book already exists.", null);
				}
				Book book = new Book();
				book.setAuthor(dto.getAuthor().trim().toLowerCase());
				book.setCategory(category);
				book.setTitle(dto.getTitle());
				book.setTotalCopies(dto.getTotalCopies());
				book.setCreatedAt(new Date());
				bookRepository.save(book);
				return new Response<>(HttpStatus.OK.value(), "Book registration successful.", null);
			} else {
				Optional<Book> exitingBook = bookRepository.findById(dto.getId());

				if (exitingBook == null || exitingBook.isEmpty()) {
					return new Response<>(HttpStatus.BAD_REQUEST.value(), "Provide Valid Book Id.", null);
				}
				Book book = exitingBook.get();

				if (dto.getTitle() != null && !dto.getTitle().isBlank()) {
					book.setTitle(dto.getTitle().trim());
				}

				if (dto.getAuthor() != null && !dto.getAuthor().isBlank()) {
					book.setAuthor(dto.getAuthor().trim().toLowerCase());
				}

				if (dto.getCategory() != null || dto.getCategory().getId() != null) {
					book.setCategory(category);
				}
				if (dto.getTotalCopies() != null) {
					book.setTotalCopies(dto.getTotalCopies());
				}

				book.setUpdatedAt(new Date());

				bookRepository.save(book);
				return new Response<>(HttpStatus.OK.value(), "Book Update successful.", null);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Something went wrong", null);

		}
	}

	@Override
	public Response<?> getAllBooks(SearchDto searchDto) {
		try {
			// TODO Auto-generated method stub
			Pageable pageable = PageRequest.of(searchDto.getPageNo(), searchDto.getPageSize(),
					Sort.by("id").descending());
			System.out.println("Searching for: " + searchDto.getSearchField());

			Page<Book> bookpage = bookRepository.findAll(searchDto, pageable);
			System.out.println(bookpage.getContent().toString());
			List<BookDto> userDtos = bookpage.getContent().stream().map(Book::convertEntityToDto)
					.collect(Collectors.toList());
			Pagination<List<?>> pagination = new Pagination<>();
			pagination.setData(userDtos);
			pagination.setTotalElements(bookpage.getTotalElements());
			pagination.setTotalPages(bookpage.getTotalPages());
			pagination.setNumberOfElements(bookpage.getNumberOfElements());

			Response<Object> response = new Response<>();
			response.setPaginationData(pagination);
			response.setResponseCode(HttpStatus.OK.value());

			return new Response<>(HttpStatus.OK.value(), "Data fetch succesfully", response);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Something went wrong", null);
		}
	}

	@Override
	public Response<?> getBookById(Long bookId) {
		try {
			if (bookId == null) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Provide Book Id", null);
			}
			Optional<Book> existingBook = bookRepository.findById(bookId);
			if (existingBook.isPresent()) {
				BookDto bookDto = existingBook.get().convertEntityToDto();
				return new Response<>(HttpStatus.OK.value(), "Book found", bookDto);
			}
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Book not found", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Something went wrong", null);

		}
	}

}
