package com.skilldistillery.orders.services;

import java.util.List;

import com.skilldistillery.orders.entities.Customer;


public interface CustomerService {

	List<Customer> getAllCustomers();
	Customer getCustomerById(int custId);	
	Customer addCustomer(Customer cust);
	Customer updateCustomer(Customer cust);
	boolean deleteCustomer(int custId);
	List<Customer> findByNameSearch(String name);
	List<Customer> findByPhoneSearch(String phone);
	List<Customer> findByEmailSearch(String email);
	List<Customer> findByAddressSearch(String keyword);
	
}
