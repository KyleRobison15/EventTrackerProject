package com.skilldistillery.orders.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skilldistillery.orders.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	List<Customer> findByFirstNameLikeOrLastNameLike(String keyword1, String keyword2);
	List<Customer> findByPhoneLike(String phone);
	List<Customer> findByEmailLike(String email);
	List<Customer> findByStreetLikeOrCityLikeOrStateAbbreviationLikeOrPostalCodeLike(
			String street, 
			String city,
			String stateAbbrv,
			String postalCode);
}
