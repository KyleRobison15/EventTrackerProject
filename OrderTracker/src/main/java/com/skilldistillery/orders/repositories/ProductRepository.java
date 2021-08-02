package com.skilldistillery.orders.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.orders.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
