package com.skilldistillery.orders.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.orders.entities.Requisition;
import com.skilldistillery.orders.services.RequisitionService;

@RequestMapping("api")
@RestController
public class RequisitionController {

	@Autowired
	private RequisitionService reqSrv;
	
	@GetMapping("reqs")
	public List<Requisition> getAllReqs(){
		return reqSrv.getAllReqs();
	}
	
}
