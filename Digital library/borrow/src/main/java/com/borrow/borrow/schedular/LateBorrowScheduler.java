package com.borrow.borrow.schedular;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.borrow.borrow.entity.Borrowing;
import com.borrow.borrow.enums.BorrowStatus;
import com.borrow.borrow.repository.BorrowingRepository;

@Component
public class LateBorrowScheduler {
	@Autowired
	private BorrowingRepository borrowingRepository;

	@Scheduled(cron = "0 0 0 * * *") 
	public void markLateBorrowings() {
		Date now = new Date();
		List<Borrowing> lateBorrows = borrowingRepository.findByDueDateBeforeAndStatus(now, BorrowStatus.BORROWED);

		lateBorrows.forEach(borrow -> borrow.setStatus(BorrowStatus.LATE));
		
		
		borrowingRepository.saveAll(lateBorrows);

	}
}
