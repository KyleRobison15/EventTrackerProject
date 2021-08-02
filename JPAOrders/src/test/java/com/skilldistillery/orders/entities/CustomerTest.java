package com.skilldistillery.orders.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CustomerTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Customer customer;


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
		customer = em.find(Customer.class, 1); 
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close(); 
		customer=null; 
	}

	@Test
	@DisplayName("TEST: Customer Mappings") 
	void test1() {
		assertNotNull(customer);
		assertEquals("Erin", customer.getFirstName());
		assertEquals("Gates", customer.getLastName());
		assertEquals("eringates@example.com",customer.getEmail());
		assertEquals("1234567890",customer.getPhone());
		assertEquals("1234 N Nevada Ave",customer.getStreet());
		assertEquals("Colorado Springs",customer.getCity());
		assertEquals("CO",customer.getStateAbbreviation());
		assertEquals("80903",customer.getPostalCode());
		
		assertEquals(1, customer.getReqs().size());

	}

}
