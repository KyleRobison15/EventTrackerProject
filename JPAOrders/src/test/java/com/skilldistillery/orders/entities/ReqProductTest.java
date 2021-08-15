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

class ReqProductTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private ReqProduct reqProduct;


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
//		ReqProductId rpId = new ReqProductId();
//		rpId.setReqId(1);
//		rpId.setProductId(1);
		reqProduct = em.find(ReqProduct.class, new ReqProductId(1,1)); 
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close(); 
		reqProduct=null; 
	}

	@Test
	@DisplayName("TEST: ReqProduct Mappings") 
	void test1() {
		assertEquals(2, reqProduct.getUnitsOrdered());
	}

}
