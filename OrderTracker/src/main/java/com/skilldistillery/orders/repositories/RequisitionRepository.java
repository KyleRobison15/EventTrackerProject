package com.skilldistillery.orders.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.orders.entities.Requisition;

public interface RequisitionRepository extends JpaRepository<Requisition, Integer> {

	List<Requisition> findByProducts_Id(int productId);
	List<Requisition> findByCustomer_Id(int customerId);
	
}
