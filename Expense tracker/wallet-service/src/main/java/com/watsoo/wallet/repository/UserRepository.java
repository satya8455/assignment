package com.watsoo.wallet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.watsoo.wallet.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String username);

	Optional<User>  findByEmailOrPhoneNumber(String email, String phoneNumber);
}
