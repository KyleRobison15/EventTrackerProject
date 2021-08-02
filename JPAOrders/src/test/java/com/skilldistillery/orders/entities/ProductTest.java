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

class ProductTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Product product;


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
		product = em.find(Product.class, 1); 
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close(); 
		product=null; 
	}

	@Test
	@DisplayName("TEST: Req Mappings") 
	void test1() {
		assertNotNull(product);
		
		assertEquals("Bagels", product.getName());
		assertEquals(6, product.getUnitQuantity());
		assertEquals(8.00, product.getUnitPrice());
		assertEquals("https://images.unsplash.com/photo-1585445490387-f47934b73b54?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=2850&q=80", 
				product.getImageUrl());
		
		assertEquals(1, product.getReqs().size());
		
	}

}
