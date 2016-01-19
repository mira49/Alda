package unit_tests;

import static org.junit.Assert.*;

import java.util.List;

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
		assertTrue("Error in announcement creation",check.equals(annonce));
	}
	
	@Test
	public void testUpdate() {
		annonce.setDescription("modification");
		dao.create(annonce);
		Annonces check = em.find(Annonces.class, annonce.getId());
		assertTrue("Error in announcement update",check.getDescription().equals("modification"));
	}

	@Test
	public void testDelete() {
		Annonces check = em.find(Annonces.class, annonce.getId());
		dao.delete(check.getId());
		
		Annonces check_delete = em.find(Annonces.class, annonce.getId());
		assertTrue("Error in announcement delete",check_delete == null);
	}
	
	@Test
	public void testgetAnnoucement_user(){
		User two = new User("blabla@gmail.com", "1236548");
		boolean check = false;
		for (Annonces announce : dao.getAnnoucement_user(two)){
			if (!(announce.getUser().getEmail().equals(two.getEmail()))){
				check = true;
			}
		}
		
		assertTrue("Error in announcement getAnnoucement_user", !check);
	}
	
	@Test
	public void testfindByFactor(){
		boolean check = false;
		List<Annonces> announcement = dao.findByFactor("SELECT u FROM Annonces u where u.price between 10000 AND 50000" );
	
		for (Annonces announce : announcement){
			if (!(announce.getPrice() < 10000) || !(announce.getPrice() > 50000)){
				check = true;
			}
		}
		
		assertTrue("Error in announcement findByFactor",!check);
	}
	
	@Test
	public void testfindById(){
		Long id = (long) 200;
		annonce.setId(id);
		dao.create(annonce);
		
		assertTrue("Error in announcement findById", dao.findById("200").getId() == 200);
	}
	
	@Test
	public void testfindFavorite(){
		
		User user = new User ("pasenbdd@gmail.com", "testpasenbdd");
		
		assertTrue("Error in announcement findFavorite",dao.findFavorite(user, "200") == false);	
	}
	
	@Test
	public void testfindAllByFavorite(){
		
		
		
		for (Annonces announce : dao.findAllByFavorite(this.user)){
			boolean check = false;
			
			for (User u : announce.getFavorites()){
				if(u.getEmail().equals(this.user.getEmail())){
					check  = true;
				}
			}
			assertTrue("Error in announcement findAllByFavorite" , check);
		}
	}

	@Test
	public void testSetAnnounceSold() {
		
		dao.setAnnounceSold("1");
		
		Annonces check = em.find(Annonces.class, 1);
		
		assertTrue("Error in announcement setAnnounceSold", check.getSold() == 1);
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
		
		assertTrue("Error in announcement AddToFavoriteList", check);
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
			
			assertTrue("Error in announcement RemoveToFavoriteList", !check);
	}

	@Test
	public void testResetFavorite() {
		
		dao.resetFavorite(annonce.getId());
		
		assertTrue("Error in announcement ResetFavorite" , annonce.getFavorites() == null);
	}

}
