package com.skilldistillery.orders.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.orders.entities.Requisition;

public interface RequisitionRepository extends JpaRepository<Requisition, Integer> {

	List<Requisition> findByProducts_IdAndUser_Username(int productId, String username);
	List<Requisition> findByCustomer_IdAndUser_Username(int customerId, String username);
	List<Requisition> findByUser_Username(String username);
	Requisition findByIdAndUser_Username(int rId, String username);
	
}
