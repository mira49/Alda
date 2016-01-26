package unit_tests;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ejb.AdministratorDAO;
import entities.Administrator;

public class AdministratorDAOTest {

	AdministratorDAO admin_dao = new AdministratorDAO();
	
	@PersistenceContext
	EntityManager em;
	
	@Test
	public void testCreate() {
		
		Administrator admin = new Administrator();
		admin.setPassword("root");
		admin.setPseudo("root");
		
		admin_dao.create(admin);
		
		Administrator check = em.find(Administrator.class, 0);
		
		assertTrue("Error in Administration create" ,check == admin);
	}

	@Test
	public void testFindAdministrator() {
		
		Administrator check = admin_dao.findAdministrator("root", "root");
		
		assertTrue("Error in Administration findAdministrator" ,check.getPassword() == "root" && check.getPseudo() == "root");
	}

}
