package com.borrow.borrow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.borrow.borrow.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String username);

	Optional<User>  findByEmailOrPhoneNumber(String email, String phoneNumber);
}
