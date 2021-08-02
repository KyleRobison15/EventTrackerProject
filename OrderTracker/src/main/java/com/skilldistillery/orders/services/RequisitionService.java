package com.skilldistillery.orders.services;

import java.util.List;

import com.skilldistillery.orders.entities.Requisition;

public interface RequisitionService {

	List<Requisition> getAllReqs();
	Requisition getReqById(int reqId);
	List<Requisition> getReqsByProductId(int productId);
	List<Requisition> getReqsByCustomerId(int customerId);	
	Requisition addReq(Requisition req);
	Requisition updateReq(Requisition req);
	boolean deleteReq(int reqId);
	
}
