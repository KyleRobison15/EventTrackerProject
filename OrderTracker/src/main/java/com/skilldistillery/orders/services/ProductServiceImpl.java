package com.skilldistillery.orders.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.orders.entities.Product;
import com.skilldistillery.orders.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository prodRepo;
	
	@Override
	public List<Product> getAllProducts() {
		return prodRepo.findAll();
	}

	@Override
	public Product getProductById(int prodId) {
		Optional<Product> prodOpt = prodRepo.findById(prodId);
		
		if (prodOpt.isPresent()) {
			return prodOpt.get();
		}
		return null;
	}

	@Override
	public Product addProduct(Product prod) {
		try {
			return prodRepo.saveAndFlush(prod);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Product updateProduct(Product prod) {
		try {
			return prodRepo.saveAndFlush(prod);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean deleteProduct(int prodId) {
		prodRepo.deleteById(prodId);
		return !prodRepo.existsById(prodId);
	}

}
