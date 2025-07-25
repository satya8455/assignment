package com.watsoo.wallet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.watsoo.wallet.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

	Optional<Wallet> findByIdAndUser_Id(Long id, Long userId);

	List<Wallet> findByUser_Id(Long userId);

	Optional<Wallet> findByName(String name);



}
