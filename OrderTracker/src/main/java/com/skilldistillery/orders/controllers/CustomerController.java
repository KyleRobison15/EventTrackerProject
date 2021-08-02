package com.skilldistillery.orders.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping("customers")
	public Customer addCustomer(@RequestBody Customer cust, HttpServletResponse res, HttpServletRequest req) {
		
		cust = custSrv.addCustomer(cust);
		
		try {
			if (cust == null) {
				res.setStatus(404);
			}
			else {
				res.setStatus(201);
				
				StringBuffer url = req.getRequestURL();
				url.append("/").append(cust.getId());
				res.setHeader("Location", url.toString());
			}
		} catch (Exception e) {
			res.setStatus(400);
		}
		
		return cust;
	}
	
	@PutMapping("customers")
	public Customer updateCustomer(@RequestBody Customer cust, HttpServletResponse res) {
		
		cust = custSrv.updateCustomer(cust);
		
		if (cust == null) {
			res.setStatus(404);
		}
		
		return cust;
		
	}
	
	@DeleteMapping("customers/{id}")
	public boolean deleteCustomer(@PathVariable ("id") int custId, HttpServletResponse res) {
		
		boolean isDeleted = false;
		
		Customer cust = custSrv.getCustomerById(custId);
		
		if (cust == null) {
			res.setStatus(404);
			return false;
		}

		isDeleted = custSrv.deleteCustomer(custId);
		
		if (!isDeleted) {
			res.setStatus(400);
		}
		else {
			res.setStatus(204);
		}
		
		return isDeleted;
	}
	
	@GetMapping("customers/name/search/{name}")
	public List<Customer> findCustomerByName(@PathVariable String name){
		return custSrv.findByNameSearch(name);
	}
	
	@GetMapping("customers/email/search/{email}")
	public List<Customer> findCustomerByEmail(@PathVariable String email){
		return custSrv.findByEmailSearch(email);
	}
	
	@GetMapping("customers/phone/search/{phone}")
	public List<Customer> findCustomerByPhone(@PathVariable String phone){
		return custSrv.findByPhoneSearch(phone);
	}
	
	@GetMapping("customers/address/search/{keyword}")
	public List<Customer> findCustomerByAddress(@PathVariable String keyword){
		return custSrv.findByAddressSearch(keyword);
	}
	
}
