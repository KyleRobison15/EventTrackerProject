package com.skilldistillery.orders.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.orders.entities.Product;
import com.skilldistillery.orders.services.ProductService;

@CrossOrigin({"*", "http://localhost:4203"})
@RequestMapping("api")
@RestController
public class ProductController {

	@Autowired
	private ProductService prodSrv;
	
	@GetMapping("products")
	public List<Product> getAllProducts(){
		return prodSrv.getAllProducts();
	}
	
	@GetMapping("products/{id}")
	public Product getProductById(@PathVariable("id") int prodId, HttpServletResponse res) {
		
		Product prod = prodSrv.getProductById(prodId);
		
		if (prod == null) {
			res.setStatus(404);
		}
		
		return prod;
		
	}
	
	@PostMapping("products")
	public Product addProduct(@RequestBody Product product, HttpServletResponse res, HttpServletRequest req) {
		
		product = prodSrv.addProduct(product);
		
		try {
			if (product == null) {
				res.setStatus(404);
			}
			else {
				res.setStatus(201);
				
				StringBuffer url = req.getRequestURL();
				url.append("/").append(product.getId());
				res.setHeader("Location", url.toString());
			}
		} catch (Exception e) {
			res.setStatus(400);
		}
		
		return product;
	}
	
	@PutMapping("products")
	public Product updateProduct(@RequestBody Product product, HttpServletResponse res) {
		
		Product prod = prodSrv.updateProduct(product);
		
		if (prod == null) {
			res.setStatus(404);
		}
		
		return prod;
		
	}
	
	@DeleteMapping("products/{id}")
	public boolean deleteReq(@PathVariable("id") int prodId, HttpServletResponse res) {
		boolean isDeleted = false;
		
		Product prod = prodSrv.getProductById(prodId);
		
		if (prod == null) {
			res.setStatus(404);
			return false;
		}

		isDeleted = prodSrv.deleteProduct(prodId);
		
		if (!isDeleted) {
			res.setStatus(400);
		}
		else {
			res.setStatus(204);
		}
		
		return isDeleted;
	}
	
}
