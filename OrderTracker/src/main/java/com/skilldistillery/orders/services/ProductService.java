package com.skilldistillery.orders.services;

import java.util.List;

import com.skilldistillery.orders.entities.Product;

public interface ProductService {

	List<Product> getAllProducts();
	Product getProductById(int prodId);	
	Product addProduct(Product prod);
	Product updateProduct(Product prod);
	boolean deleteProduct(int prodId);
	
}
