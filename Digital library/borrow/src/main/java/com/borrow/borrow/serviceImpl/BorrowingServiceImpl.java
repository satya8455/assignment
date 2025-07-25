package com.borrow.borrow.serviceImpl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.borrow.borrow.dto.BookDto;
import com.borrow.borrow.dto.BorrowDto;
import com.borrow.borrow.dto.Response;
import com.borrow.borrow.entity.Borrowing;
import com.borrow.borrow.entity.User;
import com.borrow.borrow.enums.BorrowStatus;
import com.borrow.borrow.feignclient.CatalogClient;
import com.borrow.borrow.repository.BorrowingRepository;
import com.borrow.borrow.repository.UserRepository;
import com.borrow.borrow.service.BorrowingService;

@Service
public class BorrowingServiceImpl implements BorrowingService {

	@Autowired
	private BorrowingRepository borrowingRepository;
	@Autowired
	private CatalogClient catalogClient;
	@Autowired
	private UserRepository userRepository;

	@Override
	public Response<?> borrowBook(BorrowDto borrowDto) {

		try {
			Optional<User> existingUser = userRepository.findById(borrowDto.getUserId());
			if (existingUser.isEmpty()) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "User not found", null);
			} Optional<Borrowing> optBorrow = borrowingRepository
	                .findByUserIdAndBookIdAndStatus(borrowDto.getUserId(), borrowDto.getBookId(), BorrowStatus.BORROWED);
	        if (optBorrow.isPresent()) {
	            return new Response<>(HttpStatus.BAD_REQUEST.value(),
	                    "You have already borrowed this book. Please return it before borrowing again.", null);
	        }

			Response<BookDto>	book=catalogClient.getBookById(borrowDto.getBookId());
			if(book==null||book.getResponseCode() != HttpStatus.OK.value()) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(),"No book id present",null);
			}
			int activeBorrows = borrowingRepository.countByBookIdAndStatus(book.getData().getId(), BorrowStatus.BORROWED);
			if (book.getData().getTotalCopies() <= activeBorrows) {
				return  new Response<>(HttpStatus.BAD_REQUEST.value(),"No copies available to borrow",null);
			}

			Borrowing borrowing = new Borrowing();
			borrowing.setUser(existingUser.get());
			borrowing.setBookId(book.getData().getId());
			borrowing.setBorrowDate(new Date());
			borrowing.setDueDate(Date.from(LocalDate.now().plusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant()));
			borrowing.setStatus(BorrowStatus.BORROWED);
			borrowingRepository.save(borrowing);
			return new Response<>(HttpStatus.OK.value(), " Book borrowed successfully", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Something went wrong", null);
		}
	}

	@Override
	public Response<?> returnBook(Long borrowId) {
		try {
			if(borrowId==null) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(),"Please provide borrowid",null);
			}
			
			  Optional<Borrowing> borrowOpt = borrowingRepository
		                .findByIdAndStatus(borrowId, BorrowStatus.BORROWED);

		        if (borrowOpt.isEmpty()) {
		            return new Response<>(HttpStatus.BAD_REQUEST.value(),
		                    "No active borrow found with given borrowId", null);
		        }
			           
			    Borrowing borrowing = borrowOpt.get();
			    borrowing.setReturnDate(new Date());
			    borrowing.setStatus(BorrowStatus.RETURNED);
			    borrowingRepository.save(borrowing);
			    return new Response<>(HttpStatus.OK.value(), "Book returned successfully", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Something went wrong", null);

		}
	}

}
