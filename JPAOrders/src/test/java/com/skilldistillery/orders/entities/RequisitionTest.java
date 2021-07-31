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
	private Requisition order;


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
		order = em.find(Requisition.class, 1); 
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close(); 
		order=null; 
	}

	@Test
	@DisplayName("TEST: Order Mappings") 
	void test1() {
		assertNotNull(order);
		
		assertEquals(2021, order.getDatePlaced().getYear());
		assertEquals(7, order.getDatePlaced().getMonthValue()); //datePlaced mappings
		assertEquals(30, order.getDatePlaced().getDayOfMonth());
		
		assertEquals(2021, order.getDueDate().getYear());
		assertEquals(8, order.getDueDate().getMonthValue()); //dueDate mappings
		assertEquals(02, order.getDueDate().getDayOfMonth());

	}

}
