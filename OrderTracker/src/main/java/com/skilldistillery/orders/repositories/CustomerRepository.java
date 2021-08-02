package com.skilldistillery.orders.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skilldistillery.orders.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	
}
