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

import com.skilldistillery.orders.entities.Requisition;
import com.skilldistillery.orders.services.RequisitionService;

@CrossOrigin({"*", "http://localhost:4203"})
@RequestMapping("api")
@RestController
public class RequisitionController {

	@Autowired
	private RequisitionService reqSrv;
	
	String username = "ExampleUser";
	
	@GetMapping("reqs")
	public List<Requisition> getAllReqs(HttpServletResponse res){
		
		List<Requisition> reqs = reqSrv.getAllReqs(username);
		
		if (reqs == null) {
			res.setStatus(404);
		}
		
		return reqs;
	}
	
	@GetMapping("reqs/{id}")
	public Requisition getReqById(@PathVariable("id") int reqId, HttpServletResponse res) {
		
		Requisition req = reqSrv.getReqById(reqId, username);
		
		if (req == null) {
			res.setStatus(404);
		}
		
		return req;
	}
	
	@GetMapping("products/{id}/reqs")
	public List<Requisition> getReqsByProductId(@PathVariable("id") int productId, HttpServletResponse res){
		
		List<Requisition> reqs = reqSrv.getReqsByProductId(productId, username);
		
		if (reqs == null) {
			res.setStatus(404);
		}
		
		return reqs;
	}
	
	@GetMapping("customers/{id}/reqs")
	public List<Requisition> getReqsByCustomerId(@PathVariable("id") int customerId, HttpServletResponse res){
		
		List<Requisition> reqs = reqSrv.getReqsByCustomerId(customerId, username);
		
		if (reqs == null) {
			res.setStatus(404);
		}
		
		return reqs;
	}
	
	@PostMapping("reqs")
	public Requisition addReq(@RequestBody Requisition req, HttpServletResponse res, HttpServletRequest request) {
		
		try {
			if (req == null) {
				res.setStatus(404);
			}
			else {
				
				reqSrv.addReq(req, req.getReqProducts(), req.getCustomer(), username);
				res.setStatus(201);
				
				StringBuffer url = request.getRequestURL();
				url.append("/").append(req.getId()); 
				res.setHeader("Location", url.toString());
				
			}
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
			req = null;
		}
		
		return req;
	}
	
//	@PostMapping("reqs/{id}/products")
//	public Requisition addProductForReq(@RequestBody List<Product> prods, @PathVariable("id") Integer reqId, HttpServletResponse res, HttpServletRequest req) {
//		
//		Requisition order = reqSrv.addProductsForReqId(reqId, prods, username);
//		
//		try {
//			if (order == null) {
//				res.setStatus(404);
//			}
//			else {
//				res.setStatus(201);
//				
//				StringBuffer url = req.getRequestURL();
//				url.append("/").append(order.getId());
//				res.setHeader("Location", url.toString());
//				
//			}
//		} catch (Exception e) {
//			res.setStatus(404);
//		}
//		
//		return order;
//	}
	
	@PutMapping("reqs")
	public Requisition updateReq(@RequestBody Requisition req, HttpServletResponse res, HttpServletRequest request) {
		
		req = reqSrv.updateReq(req, req.getId(), req.getReqProducts(), req.getCustomer(), username);
		
		if (req == null) {
			res.setStatus(404);
		}
		
		return req;
	}
	
	@DeleteMapping("reqs/{id}")
	public boolean deleteReq(@PathVariable("id") int reqId, HttpServletResponse res) {
		boolean isDeleted = false;
		
		Requisition req = reqSrv.getReqById(reqId, username);
		
		
		if (req == null) {
			res.setStatus(404);
			return false;
		}

		isDeleted = reqSrv.deleteReq(reqId, username);
		
		if (!isDeleted) {
			res.setStatus(400);
		}
		else {
			res.setStatus(204);
		}
		
		return isDeleted;
	}
	
}
