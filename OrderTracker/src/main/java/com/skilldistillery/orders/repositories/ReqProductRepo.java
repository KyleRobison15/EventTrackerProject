package com.skilldistillery.orders.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skilldistillery.orders.entities.ReqProduct;

public interface ReqProductRepo extends JpaRepository<ReqProduct, Integer> {

	
}
