package com.skilldistillery.climbing.entities;

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

class ClimbingTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Climb climb;  

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPAClimbing");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		climb = em.find(Climb.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		climb = null; 
	}

	@Test 
	@DisplayName("TEST: Film Mappings") 
	void test1() {
		assertNotNull(climb);
		assertEquals("New Era", climb.getName());

	}

}
