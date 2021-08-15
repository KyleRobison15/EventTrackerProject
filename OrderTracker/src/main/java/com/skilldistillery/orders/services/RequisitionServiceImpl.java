package com.skilldistillery.orders.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.orders.entities.Customer;
import com.skilldistillery.orders.entities.Product;
import com.skilldistillery.orders.entities.ReqProduct;
import com.skilldistillery.orders.entities.Requisition;
import com.skilldistillery.orders.entities.User;
import com.skilldistillery.orders.repositories.CustomerRepository;
import com.skilldistillery.orders.repositories.ProductRepository;
import com.skilldistillery.orders.repositories.ReqProductRepo;
import com.skilldistillery.orders.repositories.RequisitionRepository;
import com.skilldistillery.orders.repositories.UserRepository;

@Service
public class RequisitionServiceImpl implements RequisitionService {

	@Autowired
	private RequisitionRepository reqRepo;

	@Autowired
	private CustomerRepository custRepo;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ReqProductRepo reqProdRepo;
	
	@Autowired
	private ProductRepository prodRepo;

	@Override
	public List<Requisition> getAllReqs(String username) {

		List<Requisition> reqs = null;
		User user = userRepo.findByUsername(username);

		if (user != null) {
			reqs = reqRepo.findByUser_Username(username);
		}

		return reqs;
	}

	@Override
	public Requisition getReqById(int reqId, String username) {

		Requisition req = null;
		User user = userRepo.findByUsername(username);

		if (user != null) {
			req = reqRepo.findByIdAndUser_Username(reqId, username);
		}

		return req;
	}

	@Override
	public List<Requisition> getReqsByProductId(int productId, String username) {

		List<Requisition> reqs = null;
		User user = userRepo.findByUsername(username);

		if (reqRepo.existsById(productId) && user != null) {
			reqs = reqRepo.findByProducts_IdAndUser_Username(productId, username);
		}

		return reqs;
	}

	@Override
	public List<Requisition> getReqsByCustomerId(int customerId, String username) {
		List<Requisition> reqs = null;
		User user = userRepo.findByUsername(username);

		if (reqRepo.existsById(customerId) && user != null) {
			reqs = reqRepo.findByCustomer_IdAndUser_Username(customerId, username);
		}

		return reqs;
	}

//	@Override
//	public Requisition addCustomerForReqId(int reqId, Customer customer) {
//		Optional<Requisition> reqOpt = reqRepo.findById(reqId);
//		List<Requisition> custReqs = null;
//		
//		
//		if (reqOpt.isPresent()) {
//			
//			Requisition req = reqOpt.get();
//			
//			custReqs = customer.getReqs();
//			custReqs.add(req);
//			customer.setReqs(custReqs);
//			custRepo.saveAndFlush(customer);
//			
//			req.setCustomer(customer);
//			
//			return reqRepo.saveAndFlush(req);
//			
//		}
//		
//		return null;
//	}

//	@Override
//	public Requisition addProductsForReqId(int reqId, List<Product> products, String username) {
//
//		User user = userRepo.findByUsername(username);
//
//		if (user != null) {
//
//			Optional<Requisition> reqOpt = reqRepo.findById(reqId);
//
//			if (reqOpt.isPresent()) {
//				Requisition req = reqOpt.get();
//				for (Product product : products) {
//					req.addProduct(product);
//					product.addReq(req);
//				}
//
//				return reqRepo.saveAndFlush(req);
//
//			}
//
//		}
//		return null;
//	}

	@Override
	public Requisition addReq(Requisition req, List<ReqProduct> reqProds, Customer customer, String username) {
		
		User user = userRepo.findByUsername(username);
		Requisition requisition = null;
		List<Product> products = new ArrayList<>();
		
		
		if (user != null) {
			req.setUser(user);
			req.setCustomer(custRepo.findById(customer.getId()).get());
			requisition = reqRepo.saveAndFlush(req);
		}
		
		for (ReqProduct reqProduct : reqProds) {
			Product prod = prodRepo.findById(reqProduct.getId().getProductId()).get();
			products.add(prod);
			reqProduct.setReq(req);
			reqProduct.setProd(prod);
			reqProdRepo.saveAndFlush(reqProduct);
		}
		
		requisition.setProducts(products);
		
		return requisition;
	}

	@Override
	public Requisition updateReq(Requisition req, int reqId, List<ReqProduct> reqProds, Customer customer, String username) {
		
		Requisition managedReq = reqRepo.findByIdAndUser_Username(reqId, username);
		List<Product> products = new ArrayList<>();
		List<ReqProduct> oldRps = managedReq.getReqProducts();
		
		if (managedReq != null) {
			
			managedReq.setDatePlaced(req.getDatePlaced());
			managedReq.setDueDate(req.getDueDate());
			managedReq.setCompleted(req.isCompleted());
			managedReq.setCustomer(custRepo.findById(customer.getId()).get());
			
			for (ReqProduct reqProduct : oldRps) {
				reqProduct.setReq(null);
				reqProduct.setProd(null);
				reqProdRepo.delete(reqProduct);
			}

			for (ReqProduct rp : reqProds) {
				Product prod = prodRepo.findById(rp.getId().getProductId()).get();
				products.add(prod);
				rp.setReq(managedReq);
				rp.setProd(prod);
				reqProdRepo.saveAndFlush(rp);
			}
			managedReq.setReqProducts(reqProds);
			managedReq.setProducts(products);
			
			reqRepo.saveAndFlush(managedReq);
		}
		
		return managedReq;
	}

	@Override
	public boolean deleteReq(int reqId, String username) {
		User user = userRepo.findByUsername(username);
		Requisition managedReq = reqRepo.findByIdAndUser_Username(reqId, username);
		List<ReqProduct> oldRps = managedReq.getReqProducts();
		
		for (ReqProduct reqProduct : oldRps) {
			reqProduct.setReq(null);
			reqProduct.setProd(null);
			reqProdRepo.delete(reqProduct);
		}
		
		if (user != null) {
			reqRepo.deleteById(reqId);
		}
		
		return !reqRepo.existsById(reqId);
	}

}
