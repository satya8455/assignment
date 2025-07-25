package com.watsoo.wallet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.watsoo.wallet.entity.WalletType;
@Repository
public interface WalletTypeRepository extends JpaRepository<WalletType, Long> {

	@Query(value = "SELECT * FROM wallet_types WHERE user_id = ?1 OR user_id IS NULL", nativeQuery = true)
	List<WalletType> findAllAndfindByUserId(Long id);
	
	
	 
}
