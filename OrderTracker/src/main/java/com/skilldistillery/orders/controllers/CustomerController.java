package com.skilldistillery.orders.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.orders.entities.Customer;
import com.skilldistillery.orders.services.CustomerService;

@RequestMapping("api")
@RestController
public class CustomerController {

	@Autowired
	private CustomerService custSrv;
	
	@GetMapping("customers")
	public List<Customer> getAllCustomers(){
		return custSrv.getAllCustomers();
	}
	
	@GetMapping("customers/{id}")
	public Customer getCustomerById(@PathVariable("id") int custId, HttpServletResponse res) {
		
		Customer cust = custSrv.getCustomerById(custId);
		
		if (cust == null) {
			res.setStatus(404);
		}
		
		return cust;
		
	}
	
}
