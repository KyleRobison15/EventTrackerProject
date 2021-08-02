package com.skilldistillery.orders.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.orders.entities.Customer;
import com.skilldistillery.orders.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository custRepo;
	
	@Override
	public List<Customer> getAllCustomers() {
		return custRepo.findAll();
	}

	@Override
	public Customer getCustomerById(int custId) {
		Optional<Customer> custOpt = custRepo.findById(custId);
		
		if (custOpt.isPresent()) {
			return custOpt.get();
		}
		return null;
	}

	@Override
	public Customer addCustomer(Customer cust) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer updateCustomer(Customer cust) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteCustomer(int custId) {
		// TODO Auto-generated method stub
		return false;
	}

}
