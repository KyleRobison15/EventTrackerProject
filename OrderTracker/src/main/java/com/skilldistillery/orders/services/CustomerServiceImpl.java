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
		try {
			return custRepo.saveAndFlush(cust);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Customer updateCustomer(Customer cust) {
		try {
			return custRepo.saveAndFlush(cust);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean deleteCustomer(int custId) {
		custRepo.deleteById(custId);
		return !custRepo.existsById(custId);
	}

	@Override
	public List<Customer> findByNameSearch(String name) {
		name = "%" + name + "%";
		return custRepo.findByFirstNameLikeOrLastNameLike(name, name);
	}

	@Override
	public List<Customer> findByPhoneSearch(String phone) {
		phone = "%" + phone + "%";
		return custRepo.findByPhoneLike(phone);
	}

	@Override
	public List<Customer> findByEmailSearch(String email) {
		email = "%" + email + "%";
		return custRepo.findByEmailLike(email);
	}

	@Override
	public List<Customer> findByAddressSearch(String keyword) {
		keyword = "%" + keyword + "%";
		return custRepo.findByStreetLikeOrCityLikeOrStateAbbreviationLikeOrPostalCodeLike(
				keyword, keyword, keyword, keyword);
	}

}
