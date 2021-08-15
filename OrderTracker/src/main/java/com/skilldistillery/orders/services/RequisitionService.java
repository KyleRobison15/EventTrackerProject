package com.skilldistillery.orders.services;

import java.util.List;

import com.skilldistillery.orders.entities.Customer;
import com.skilldistillery.orders.entities.ReqProduct;
import com.skilldistillery.orders.entities.Requisition;

public interface RequisitionService {

	List<Requisition> getAllReqs(String username);
	Requisition getReqById(int reqId, String username);
	List<Requisition> getReqsByProductId(int productId, String username);
	List<Requisition> getReqsByCustomerId(int customerId, String username);	
	Requisition addReq(Requisition req, List<ReqProduct> reqProds, Customer customer, String username);
	Requisition updateReq(Requisition req, int reqId, List<ReqProduct> reqProds, Customer customer, String username);
	boolean deleteReq(int reqId, String username);
	
//	Requisition addProductsForReqId(int reqId, List<Product> products, String username);
//	Requisition addCustomerForReqId(int reqId, Customer customer);
	
}
