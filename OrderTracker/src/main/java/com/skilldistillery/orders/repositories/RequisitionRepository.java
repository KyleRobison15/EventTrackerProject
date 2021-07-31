package com.skilldistillery.orders.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.orders.entities.Requisition;

public interface RequisitionRepository extends JpaRepository<Requisition, Integer> {

}
