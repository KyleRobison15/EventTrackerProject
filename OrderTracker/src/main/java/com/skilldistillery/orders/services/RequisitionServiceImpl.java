package com.skilldistillery.orders.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.orders.entities.Customer;
import com.skilldistillery.orders.entities.Product;
import com.skilldistillery.orders.entities.Requisition;
import com.skilldistillery.orders.repositories.CustomerRepository;
import com.skilldistillery.orders.repositories.ProductRepository;
import com.skilldistillery.orders.repositories.RequisitionRepository;

@Service
public class RequisitionServiceImpl implements RequisitionService {
	
	@Autowired
	private RequisitionRepository reqRepo;
	
	@Autowired
	private CustomerRepository custRepo;
	
	@Autowired
	private ProductRepository prodRepo;
	
	@Override
	public List<Requisition> getAllReqs() {
		return reqRepo.findAll();
	}

	@Override
	public Requisition getReqById(int reqId) {
		
		Optional<Requisition> reqOpt = reqRepo.findById(reqId);
		
		if (reqOpt.isPresent()) {
			return reqOpt.get();
		}
		return null;
	}

	@Override
	public List<Requisition> getReqsByProductId(int productId) {
		
		List<Requisition> reqs = null;
		
		if (reqRepo.existsById(productId)) {
			reqs = reqRepo.findByProducts_Id(productId);
		}
		
		return reqs;
	}

	@Override
	public List<Requisition> getReqsByCustomerId(int customerId) {
		List<Requisition> reqs = null;
		
		if (reqRepo.existsById(customerId)) {
			reqs = reqRepo.findByCustomer_Id(customerId);
		}
		
		return reqs;
	}

	@Override
	public Requisition addReq(Requisition req) {
		
		Requisition persistedReq = null;
		
//		if (custRepo.existsById(cust.getId())) {
//			
//			req.setCustomer(cust);
//			
//		} else {
//			
//			Customer reqCust = new Customer(cust.getFirstName(), 
//					cust.getLastName(),
//					cust.getEmail(),
//					cust.getPhone(), 
//					cust.getStreet(),
//					cust.getCity(),
//					cust.getStateAbbreviation(),
//					cust.getPostalCode(),
//					cust.getReqs());
//			
//			custRepo.saveAndFlush(reqCust);
//			req.setCustomer(reqCust);
//			
//		}
//		
//		List<Product> reqProds = new ArrayList<>();
//		
//		for (Product prod : prods) {
//			reqProds.add(prod);
//		}
//		
//		req.setProducts(reqProds);
		
		 try {
			persistedReq = reqRepo.saveAndFlush(req);
			
		} catch (Exception e) {

			return null;
		}
		
		return persistedReq;
	}

	@Override
	public Requisition updateReq(Requisition req) {
		Requisition persistedReq = null;
		
		 try {
			persistedReq = reqRepo.saveAndFlush(req);
			
		} catch (Exception e) {

			return null;
		}
		
		return persistedReq;
	}

	@Override
	public boolean deleteReq(int reqId) {
		reqRepo.deleteById(reqId);
		return !reqRepo.existsById(reqId);
	}

}
