package com.watsoo.wallet.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.watsoo.wallet.entity.IncomeTransaction;

@Repository
public interface IncometransactionRepository extends JpaRepository<IncomeTransaction, Long> {
	@Query(value = "SELECT * FROM income_transactions "
			+ "WHERE wallet_id = ?1 AND DATE(date) BETWEEN ?2 AND ?3 ORDER BY date DESC", nativeQuery = true)
	List<IncomeTransaction> findByWalletAndDateRange(Long walletId, LocalDate startdate, LocalDate enddate);

	List<IncomeTransaction> findByWallet_Id(Long walletId);

	@Query(value = "SELECT it.* FROM income_transactions it " + "JOIN wallets w ON w.id = it.wallet_id "
			+ "WHERE w.user_id = ?1 AND DATE(it.date) BETWEEN ?2 AND ?3 ORDER BY it.date DESC", nativeQuery = true)
	List<IncomeTransaction> findByUserIdAndDateRange(Long userId, LocalDate startDate, LocalDate endDate);

	@Query(value = "SELECT it.* FROM income_transactions it " + "JOIN wallets w ON w.id = it.wallet_id "
			+ "WHERE w.user_id = ?1 ORDER BY it.date DESC", nativeQuery = true)
	List<IncomeTransaction> findByUserId(Long userId);

}
