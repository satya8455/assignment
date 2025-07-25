package com.borrow.borrow.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.borrow.borrow.entity.Borrowing;
import com.borrow.borrow.enums.BorrowStatus;
@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {

	int countByBookIdAndStatus(Long id, BorrowStatus borrowed);

	Optional<Borrowing> findByUserIdAndBookIdAndStatus(Long userId, Long bookId, BorrowStatus borrowed);

	boolean existsByUserIdAndStatus(Long userId, BorrowStatus borrowed);

	List<Borrowing> findByDueDateBeforeAndStatus(Date now, BorrowStatus borrowed);

	Optional<Borrowing> findByIdAndStatus(Long borrowId, BorrowStatus borrowed);

}
