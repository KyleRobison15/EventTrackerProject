package com.skilldistillery.orders.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RequisitionTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Requisition req;


	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPAOrders");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close(); 
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		req = em.find(Requisition.class, 1); 
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close(); 
		req=null; 
	}

	@Test
	@DisplayName("TEST: Req Mappings") 
	void test1() {
		assertNotNull(req);
		
		assertEquals(2021, req.getDatePlaced().getYear());
		assertEquals(7, req.getDatePlaced().getMonthValue()); //datePlaced mappings
		assertEquals(30, req.getDatePlaced().getDayOfMonth());
		
		assertEquals(2021, req.getDueDate().getYear());
		assertEquals(8, req.getDueDate().getMonthValue()); //dueDate mappings
		assertEquals(02, req.getDueDate().getDayOfMonth());
		
		assertEquals("Erin",req.getCustomer().getFirstName());
		assertEquals("Gates",req.getCustomer().getLastName());
		assertEquals("eringates@example.com",req.getCustomer().getEmail());
		assertEquals("1234567890",req.getCustomer().getPhone());
		assertEquals("1234 N Nevada Ave",req.getCustomer().getStreet());
		assertEquals("Colorado Springs",req.getCustomer().getCity());
		assertEquals("CO",req.getCustomer().getStateAbbreviation());
		assertEquals("80903",req.getCustomer().getPostalCode());
		

	}

}
