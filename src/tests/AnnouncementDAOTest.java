package tests;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.AnnouncementDAO;
import entities.Annonces;
import entities.User;

public class AnnouncementDAOTest {

	private User user;
	private Annonces annonce;
	private AnnouncementDAO dao;
	
	@PersistenceContext
	EntityManager em;
	
	@Before
	public void setUp() throws Exception {
		user = new User("test@gmail.com", "blalbla123");
		annonce = new Annonces();
		annonce.setDescription("blabla");
		annonce.setImage1("coucou/coucou");
		annonce.setImage2("coucou/coucou");
		annonce.setName("ma villa");
		annonce.setPostal_code(33200);
		annonce.setPrice(65000);
		annonce.setSold(0);
		annonce.setSurface(65);
		annonce.setTown("Bordeaux");
		annonce.setUser(user);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreate() {
		
		dao.create(annonce);
		Annonces check = em.find(Annonces.class, annonce.getId());
		assertTrue(check.equals(annonce));
	}
	
	@Test
	public void testUpdate() {
		annonce.setDescription("modification");
		dao.create(annonce);
		Annonces check = em.find(Annonces.class, annonce.getId());
		assertTrue(check.getDescription().equals("modification"));
	}

	@Test
	public void testDelete() {
		Annonces check = em.find(Annonces.class, annonce.getId());
		dao.delete(check.getId());
		
		Annonces check_delete = em.find(Annonces.class, annonce.getId());
		assertTrue(check_delete == null);
	}

	@Test
	public void testSetAnnounceSold() {
		
		dao.setAnnounceSold("1");
		
		Annonces check = em.find(Annonces.class, 1);
		
		assertTrue(check.getSold() == 1);
	}

	@Test
	public void testAddToFavoriteList() {
		User user = new User("blabla@gmail.com", "root");
		boolean check = false;
		dao.addToFavoriteList(annonce.getId().toString(), user);
		
		for(User us : annonce.getFavorites()){
			if (us.equals(user)){
				check = true;
			}
		}
		
		assertTrue(check);
	}

	@Test
	public void testRemoveToFavoriteList() {
			User user = new User("blabla@gmail.com", "root");
			boolean check = false;
			dao.removeToFavoriteList(annonce.getId().toString(), user);
			
			for(User us : annonce.getFavorites()){
				if (us.equals(user)){
					check = true;
				}
			}
			
			assertTrue(!check);
	}

	@Test
	public void testResetFavorite() {
		
		dao.resetFavorite(annonce.getId());
		
		assertTrue(annonce.getFavorites() == null);
	}

}
