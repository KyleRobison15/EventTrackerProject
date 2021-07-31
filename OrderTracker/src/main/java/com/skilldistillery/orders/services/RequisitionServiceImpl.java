package com.skilldistillery.orders.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.orders.entities.Requisition;
import com.skilldistillery.orders.repositories.RequisitionRepository;

@Service
public class RequisitionServiceImpl implements RequisitionService {
	@Autowired
	private RequisitionRepository reqRepo;
	
	@Override
	public List<Requisition> getAllReqs() {
		return reqRepo.findAll();
	}

}
