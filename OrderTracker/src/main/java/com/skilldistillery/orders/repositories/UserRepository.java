package com.skilldistillery.orders.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skilldistillery.orders.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);
	
}
