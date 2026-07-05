package com.bigboss.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bigboss.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
	
	Optional<User> findByEmail(String email);

}
