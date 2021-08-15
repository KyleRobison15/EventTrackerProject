package com.skilldistillery.orders.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;


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
		user = em.find(User.class, 1); 
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close(); 
		user=null; 
	}

	@Test
	@DisplayName("TEST: User Mappings") 
	void test1() {
		assertNotNull(user);
		assertEquals("Kyle", user.getFirstName());
		assertEquals("Robison", user.getLastName());
		assertEquals("test@example.com", user.getEmail());
		assertEquals("ExampleUser", user.getUsername());
		assertEquals("password", user.getPassword());
		assertEquals("ExampleBiz", user.getBusinessName());
		assertTrue(user.getEnabled());
		assertEquals("standard", user.getRole());
		
		assertEquals(10, user.getReqs().size());

	}

}
