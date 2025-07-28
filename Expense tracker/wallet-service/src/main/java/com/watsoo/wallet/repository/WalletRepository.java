package com.watsoo.wallet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.watsoo.wallet.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

	List<Wallet> findByUser_Id(Long userId);	
	
	@Query(value = "SELECT * FROM wallets WHERE id = ?1 AND user_id = ?2", nativeQuery = true)
	Optional<Wallet> findByIdAndUser_Id(Long id, Long userId);




}
